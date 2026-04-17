/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pantallas;

import Componentes.Sidebar;
import Componentes.panelSuperior;
import Enums.EstadoProducto;
import controlador.controlDeNavegacion;
import Enums.TipoAgregar;
import Enums.TipoProducto;
import controlador.Coordinadoor;
import dto.IngredienteSeleccionadoDTO;
import dto.ProductoDTO;
import excepciones.NegocioExcepcion;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.Insets;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.util.List;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Jorge
 */
public class AgregarProductoFrame extends JFrame {

    private String rutaImagenSeleccionada;
    private JTextArea txtDescripcion;

    public AgregarProductoFrame(panelSuperior pa, Sidebar sliede) {
        //agrego el titulo en frame 
        setTitle("Agregar Producto");
        //agrego el tamaño del frame
        setSize(400, 500);
        //hacemos que aparesca el frame en el centro
        setLocationRelativeTo(null);
        //hacemos que se cierre el frame al darle close
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        //le agrego un borderlayout 
        setLayout(new BorderLayout());

        JPanel panelSuperior = new JPanel();
        panelSuperior.setBackground(new Color(20, 87, 87));
        panelSuperior.setPreferredSize(new Dimension(600, 60));
        panelSuperior.setLayout(new FlowLayout(FlowLayout.CENTER));

        JLabel titulo = new JLabel("Agregar producto");
        titulo.setForeground(Color.WHITE);
        titulo.setFont(new Font("Segoe UI", Font.BOLD, 18));
        panelSuperior.add(titulo);
        add(panelSuperior, BorderLayout.NORTH);

        JPanel panelCentro = new JPanel(new GridBagLayout());
        panelCentro.setBackground(new Color(240, 240, 240));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        gbc.gridx = 0;
        gbc.gridy = 0;
        panelCentro.add(new JLabel("Nombre:"), gbc);

        gbc.gridx = 1;
        gbc.weightx = 1;
        JTextField txtNombre = new JTextField();
        panelCentro.add(txtNombre, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.weightx = 0;
        panelCentro.add(new JLabel("Descripción:"), gbc);

        gbc.gridx = 1;
        gbc.gridy = 1;
        gbc.weightx = 1;
        gbc.weighty = 0.2;
        gbc.fill = GridBagConstraints.BOTH;
        txtDescripcion = new JTextArea();
        txtDescripcion.setLineWrap(true);
        txtDescripcion.setWrapStyleWord(true);
        JScrollPane scrollDescripcion = new JScrollPane(txtDescripcion);
        panelCentro.add(scrollDescripcion, gbc);

        gbc.weighty = 0;
        gbc.fill = GridBagConstraints.HORIZONTAL;

        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.weightx = 0;
        panelCentro.add(new JLabel("Precio:"), gbc);

        gbc.gridx = 1;
        gbc.weightx = 1;
        JTextField txtPrecio = new JTextField();
        panelCentro.add(txtPrecio, gbc);

        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.weightx = 0;
        panelCentro.add(new JLabel("Categoría:"), gbc);

        gbc.gridx = 1;
        gbc.weightx = 1;
        JComboBox<TipoProducto> cbCategoria = new JComboBox<>();
        cbCategoria.addItem(null);
        for (TipoProducto tipo : TipoProducto.values()) {
            cbCategoria.addItem(tipo);
        }
        panelCentro.add(cbCategoria, gbc);

        gbc.gridx = 0;
        gbc.gridy = 4;
        gbc.weightx = 0;
        panelCentro.add(new JLabel("Imagen:"), gbc);

        gbc.gridx = 1;
        gbc.weightx = 1;
        JButton btnImagen = new JButton("Subir Imagen");
        btnImagen.addActionListener(e -> {
            JFileChooser filechoser = new JFileChooser();
            filechoser.setDialogTitle("Seleccionar imagen");
            int resultado = filechoser.showOpenDialog(this);
            if (resultado == JFileChooser.APPROVE_OPTION) {
                File archivo = filechoser.getSelectedFile();

                rutaImagenSeleccionada = guardarImagen(archivo);
                System.out.println("Ruta seleccionada" + rutaImagenSeleccionada);
            }
        });
        panelCentro.add(btnImagen, gbc);

        gbc.gridx = 0;
        gbc.gridy = 5;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER;

        JButton btnIngredientes = new JButton("Agregar Ingredientes");
        btnIngredientes.setBackground(new Color(46, 125, 90));
        btnIngredientes.setForeground(Color.WHITE);
        btnIngredientes.setFocusPainted(false);
        btnIngredientes.setFont(new Font("Segoe UI", Font.BOLD, 14));

        panelCentro.add(btnIngredientes, gbc);
        add(panelCentro, BorderLayout.CENTER);

        JPanel footer = new JPanel();
        footer.setBackground(new Color(20, 94, 88));
        footer.setPreferredSize(new Dimension(600, 60));

        JButton btnCancelar = new JButton("Cancelar");
        JButton btnGuardar = new JButton("Guardar");

        btnGuardar.setBackground(new Color(46, 125, 90));
        btnGuardar.setForeground(Color.WHITE);

        footer.add(btnCancelar);
        footer.add(btnGuardar);
        add(footer, BorderLayout.SOUTH);

        btnIngredientes.addActionListener(e -> {
            controlDeNavegacion.getcontrolNavegacion().abrirAgregarGenerico("Ingredientes", TipoAgregar.INGREDIENTE);
        });

        btnGuardar.addActionListener(e -> {
            ProductoDTO producto = new ProductoDTO();
            producto.setNombre(txtNombre.getText());
            producto.setDescripcion(txtDescripcion.getText());
            producto.setPrecio(Double.valueOf(txtPrecio.getText()));
            TipoProducto tipoSeleccionado = (TipoProducto) cbCategoria.getSelectedItem();
            if (tipoSeleccionado == null) {
                JOptionPane.showMessageDialog(this, "Selecciona una categoría");
                return;
            }
            producto.setTipo(tipoSeleccionado);
            producto.setEstado(EstadoProducto.ACTIVO);
            producto.setRutaImagen(rutaImagenSeleccionada);
            producto.setReceta(Coordinadoor.getCoordinador().getIngredientesSeleccionados());

            try {
                Coordinadoor.getCoordinador().guardarProducto(producto);
                JOptionPane.showMessageDialog(this, "Producto guardado correctamente");
                controlDeNavegacion.getcontrolNavegacion().abrirProductos();
            } catch (NegocioExcepcion ex) {
                JOptionPane.showMessageDialog(this, "Error al guardar el producto" + ex.getMessage());
                return;
            }

        });

    }

    private String guardarImagen(File archivoOrigen) {
        try {
            String carpeta = "imagenes/productos/";
            File dicerctorio = new File(carpeta);
            if (!dicerctorio.exists()) {
                dicerctorio.mkdirs();
            }
            String nombreArchivo = archivoOrigen.getName();
            File destino = new File(carpeta + nombreArchivo);
            Files.copy(archivoOrigen.toPath(), destino.toPath(), StandardCopyOption.REPLACE_EXISTING);
            return destino.getPath();
        } catch (IOException e) {
            JOptionPane.showMessageDialog(this, "Error al guardar la imagen" + e.getMessage());
            return null;
        }
    }

}
