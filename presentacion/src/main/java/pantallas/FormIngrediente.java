/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pantallas;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.Insets;
import java.io.File;
import java.nio.file.Files;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.filechooser.FileNameExtensionFilter;

/**
 *
 * @author Benjamin
 */
public class FormIngrediente extends JDialog{
    private JTextField txtNombre;
    private JComboBox<String> cmbUnidad;
    private JTextField txtStock;
    private JLabel lblPreview;
    //private byte[] imagenBytes = null; // Aquí guardamos la foto para la BD
    private String rutaImagen = null;
    
    private JButton btnGuardar;
    private JButton btnCancelar;
    private JButton btnSubirImagen;

    public FormIngrediente(JFrame parent) {
        super(parent, "Nuevo Ingrediente", true); // true para que sea modal
        setResizable(false);
        initComponents();
        pack(); // Ajusta el tamaño al contenido
        setLocationRelativeTo(parent);
    }

    private void initComponents() {
        // Usaremos un GridBagLayout para que se vea ordenado
        JPanel panel = new JPanel(new GridBagLayout());
        panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        // --- COLUMNA 1: Datos ---
        gbc.gridx = 0; gbc.gridy = 0;
        panel.add(new JLabel("Nombre del Ingrediente:"), gbc);
        
        gbc.gridy = 1;
        txtNombre = new JTextField(20);
        panel.add(txtNombre, gbc);

        gbc.gridy = 2;
        panel.add(new JLabel("Unidad de Medida:"), gbc);
        
        gbc.gridy = 3;
        cmbUnidad = new JComboBox<>(new String[]{"Piezas", "Gramos", "Mililitros"});
        panel.add(cmbUnidad, gbc);

        gbc.gridy = 4;
        panel.add(new JLabel("Stock Inicial:"), gbc);
        
        gbc.gridy = 5;
        txtStock = new JTextField("0", 20);
        panel.add(txtStock, gbc);

        // --- COLUMNA 2: Imagen (A la derecha) ---
        gbc.gridx = 1; gbc.gridy = 0;
        gbc.gridheight = 1;
        panel.add(new JLabel("Imagen (Opcional):"), gbc);

        gbc.gridy = 1;
        gbc.gridheight = 4; // Que ocupe varios espacios hacia abajo
        lblPreview = new JLabel();
        lblPreview.setPreferredSize(new Dimension(150, 150));
        lblPreview.setBorder(BorderFactory.createLineBorder(Color.GRAY));
        lblPreview.setHorizontalAlignment(SwingConstants.CENTER);
        lblPreview.setText("Sin imagen");
        panel.add(lblPreview, gbc);

        gbc.gridy = 5;
        gbc.gridheight = 1;
        btnSubirImagen = new JButton("Seleccionar Imagen");
        panel.add(btnSubirImagen, gbc);

        // --- FILA FINAL: Botones de Acción ---
        JPanel panelAcciones = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        btnCancelar = new JButton("Cancelar");
        btnGuardar = new JButton("Guardar Ingrediente");
        
        btnGuardar.setBackground(new Color(20, 87, 87));
        btnGuardar.setForeground(Color.WHITE);
        
        panelAcciones.add(btnCancelar);
        panelAcciones.add(btnGuardar);

        gbc.gridx = 0; gbc.gridy = 6;
        gbc.gridwidth = 2;
        panel.add(panelAcciones, gbc);

        add(panel);

        // --- EVENTOS ---

        // Evento para subir imagen
        btnSubirImagen.addActionListener(e -> {
            JFileChooser chooser = new JFileChooser();
            chooser.setFileFilter(new FileNameExtensionFilter("Imágenes", "jpg", "png", "jpeg"));
            
            if (chooser.showOpenDialog(this) == JFileChooser.APPROVE_OPTION) {
                try {
                    
                    File archivoElegido = chooser.getSelectedFile();
                    
                    rutaImagen = archivoElegido.getAbsolutePath();
                    
                    //File f = chooser.getSelectedFile();
                    //imagenBytes = Files.readAllBytes(f.toPath());
                    
                    // Mostrar preview
                    ImageIcon icon = new ImageIcon(rutaImagen);
                    Image scaled = icon.getImage().getScaledInstance(150, 150, Image.SCALE_SMOOTH);
                    lblPreview.setIcon(new ImageIcon(scaled));
                    lblPreview.setText("");
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(this, "Error al cargar imagen");
                }
            }
        });

        btnCancelar.addActionListener(e -> dispose());
        
        btnGuardar.addActionListener(e -> {
            // Aquí validaremos que nombre no esté vacío y stock sea número
            // Luego llamaremos al coordinador
            validarYGuardar();
        });
    }

    private void validarYGuardar() {
        String nombre = txtNombre.getText().trim();
        
        if(nombre.isEmpty()){
            JOptionPane.showMessageDialog(this, "El nombre es obligatorio.", "Aviso", JOptionPane.WARNING_MESSAGE);
            return;
        }
        
        try{
            double stock = Double.parseDouble(txtStock.getText());
            String unidad = cmbUnidad.getSelectedItem().toString();
            
            // Si llega hasta aquí, todo está correcto. 
            // Aquí mandarías a llamar a tu Coordinador.
            // Nota que rutaImagen puede ser null si el usuario no seleccionó foto, ¡lo cual es correcto!
            
            // 1. Empaquetamos los datos en el mensajero (DTO)
            // El ID va como null porque la base de datos lo genera automáticamente (AUTO_INCREMENT)
            dto.IngredienteDTO nuevoIngrediente = new dto.IngredienteDTO(null, nombre, unidad, stock, rutaImagen);
            
            // 2. Instanciamos nuestra interfaz de Negocio (El puente)
            interfaces.IIngredienteBO ingredienteBO = new objetosNegocio.IngredienteBO();
            
            // 3. ¡Lo enviamos a guardar!
            ingredienteBO.agregarIngrediente(nuevoIngrediente);
            
            // 4. Si no hubo errores, avisamos y cerramos
            JOptionPane.showMessageDialog(this, "Ingrediente guardado exitosamente en la Base de Datos.");
            dispose();
            
            
        } catch (NumberFormatException e){
            JOptionPane.showMessageDialog(this, "El stock debe ser un numero valido", "Error", JOptionPane.ERROR_MESSAGE);
        } catch (excepciones.NegocioExcepcion ex){
            JOptionPane.showMessageDialog(this, ex.getMessage(), "Error al guardar", JOptionPane.ERROR_MESSAGE);
        }
        
    }
}
