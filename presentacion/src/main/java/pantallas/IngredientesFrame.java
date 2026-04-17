/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pantallas;

import javax.swing.JFrame;
import Componentes.Sidebar;
import Componentes.panelSuperior;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import static javax.swing.WindowConstants.EXIT_ON_CLOSE;
import javax.swing.table.DefaultTableModel;

/**
 * Pantalla principal para el modulo de ingredientes 
 * Muestra el listado de ingredientes y permite buscar o acceder a la creacion/edicion de estos
 * @author Benjamin
 */
public class IngredientesFrame extends JFrame {
    
    private boolean menuVisible = false;
    
    // Variables globales para que el Coordinador pueda interactuar con ellas después
    private JButton btnAgregar;
    private JTextField txtBusqueda;
    private JComboBox<String> cmbMedida;
    private JButton btnBuscar;
    private DefaultTableModel modelo;
    private JTable tablaIngredientes;
    
    private java.util.List<Long> listaIdsIngredientes = new java.util.ArrayList<>();

    public IngredientesFrame(panelSuperior pa, Sidebar sliede) {
        
        // Cambiar textos globales de los JOptionPane ---
        javax.swing.UIManager.put("OptionPane.okButtonText", "Aceptar");
        javax.swing.UIManager.put("OptionPane.cancelButtonText", "Cancelar");

        // podemos cambiar los botones del mensaje de "Eliminar"
        javax.swing.UIManager.put("OptionPane.yesButtonText", "Aceptar");
        javax.swing.UIManager.put("OptionPane.noButtonText", "Cancelar");
        // ----------------------------------------------------------

        sliede.setPreferredSize(new Dimension(0, 0));
        setTitle("Gestión de Ingredientes");
        
        // Configuración básica del Frame (Consistencia con el resto del sistema)
        sliede.setPreferredSize(new Dimension(0, 0));
        setTitle("Gestión de Ingredientes");
        setSize(1200, 650);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        add(pa, BorderLayout.NORTH);
        add(sliede, BorderLayout.WEST);

        // --- PANEL CENTRO ---
        JPanel panelCentro = new JPanel();
        panelCentro.setLayout(new BorderLayout(20, 20));
        panelCentro.setBackground(new Color(235, 240, 245)); // Un gris muy claro/azulado
        panelCentro.setBorder(BorderFactory.createEmptyBorder(20, 30, 30, 30)); // Márgenes internos

        // --- 1. SECCIÓN SUPERIOR: Controles (Agregar y Buscar) ---
        JPanel panelControles = new JPanel(new BorderLayout());
        panelControles.setOpaque(false);

        // Izquierda: Botón Agregar
        JPanel panelAdd = new JPanel(new FlowLayout(FlowLayout.LEFT, 0, 0));
        panelAdd.setOpaque(false);
        btnAgregar = new JButton("Agregar ingrediente");
        btnAgregar.setBackground(new Color(20, 87, 87));
        btnAgregar.setForeground(Color.WHITE);
        btnAgregar.setFont(new Font("Segoe UI", Font.BOLD, 14));
        panelAdd.add(btnAgregar);

        // Derecha: Filtros de Búsqueda
        JPanel panelBusqueda = new JPanel(new FlowLayout(FlowLayout.RIGHT, 10, 0));
        panelBusqueda.setOpaque(false);
        
        cmbMedida = new JComboBox<>(new String[]{"Todas las medidas", "Gramos", "Mililitros", "Piezas"});
        txtBusqueda = new JTextField(15);
        txtBusqueda.setToolTipText("Buscar por nombre...");
        btnBuscar = new JButton("Buscar");
        btnBuscar.setBackground(new Color(105, 105, 105));
        btnBuscar.setForeground(Color.WHITE);

        panelBusqueda.add(cmbMedida);
        panelBusqueda.add(txtBusqueda);
        panelBusqueda.add(btnBuscar);

        // Ensamblamos la barra de controles
        panelControles.add(panelAdd, BorderLayout.WEST);
        panelControles.add(panelBusqueda, BorderLayout.EAST);
        panelCentro.add(panelControles, BorderLayout.NORTH);

        // --- 2. SECCIÓN CENTRAL: La Tabla ---
        // Columnas basadas en tu storyboard
        String[] columnas = {"Imagen", "Nombre", "Unidad de medida", "Stock", "Administrar", "ID"};
        modelo = new DefaultTableModel(columnas, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false; // Evita que editen el texto dando doble clic
            }
            
            @Override
            public Class<?> getColumnClass(int columnIndex){
                //Columna para las imagenes 
                if (columnIndex == 0) return javax.swing.Icon.class;
                return super.getColumnClass(columnIndex);
            }
        };

        tablaIngredientes = new JTable(modelo);
        
        //Ocultar la columna del ID ---
        // La columna existe en el modelo (para programar), pero es invisible para el usuario
        tablaIngredientes.getColumnModel().getColumn(5).setMinWidth(0);
        tablaIngredientes.getColumnModel().getColumn(5).setMaxWidth(0);
        tablaIngredientes.getColumnModel().getColumn(5).setWidth(0);
        
        tablaIngredientes.setFillsViewportHeight(true);
        tablaIngredientes.setRowHeight(60); // Filas más altas para que se vea más limpio
        
        // Diseño del encabezado
        tablaIngredientes.getTableHeader().setBackground(new Color(20, 87, 87));
        tablaIngredientes.getTableHeader().setForeground(Color.WHITE);
        tablaIngredientes.getTableHeader().setFont(new Font("Segoe UI", Font.BOLD, 14));

        JScrollPane scroll = new JScrollPane(tablaIngredientes);
        panelCentro.add(scroll, BorderLayout.CENTER);

        add(panelCentro, BorderLayout.CENTER);

        
        
        
        //CARGA LOS DATOS DE LA TABLA
        cargarDatosTabla("", "Todas las medidas");
        
        // --- EVENTOS BÁSICOS ---
        
        // 1. EVENTO PARA EL MENÚ LATERAL (SIDEBAR)
        pa.getBtnMenu().addActionListener(e -> {
            if (menuVisible) {
                sliede.setPreferredSize(new Dimension(0, 0));
            } else {
                sliede.setPreferredSize(new Dimension(200, 0)); // Se abre a 200px
            }
            menuVisible = !menuVisible;
            sliede.revalidate();
            sliede.repaint();
        });
        
        // Aquí conectaremos el botón "Agregar" para que abra un formulario más adelante
        btnAgregar.addActionListener(e -> {
            // TODO: Llamar al Coordinador para abrir formIngrediente
            System.out.println("Abriendo formulario de nuevo ingrediente...");
        });
        
        // Boton de abrir el form de registrar un nuevo ingrediente
        btnAgregar.addActionListener(e -> {
            FormIngrediente form = new FormIngrediente(this);
            form.setVisible(true);
            // Al cerrarse el form, podrías refrescar la tabla aquí
            cargarDatosTabla("", "Todas las medidas");
        });
        
        // --- EVENTO PARA BORRAR (Clic en la tabla) ---
        tablaIngredientes.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseClicked(java.awt.event.MouseEvent e) {
                int fila = tablaIngredientes.getSelectedRow();
                int columna = tablaIngredientes.getSelectedColumn();
                
                if (fila == -1) return;

                // --- OPCIÓN A: BORRAR (Columna 4) ---
                if (columna == 4) {
                    ejecutarBorrado(fila); // Puedes mover tu lógica de borrado a un método aparte para limpiar el código
                }

                // --- OPCIÓN B: ACTUALIZAR STOCK (Columna 3 + Doble Clic) ---
                if (columna == 3 && e.getClickCount() == 2) {
                    String nombre = (String) modelo.getValueAt(fila, 1);
                    Double stockActual = (Double) modelo.getValueAt(fila, 3);
                    Long id = (Long) modelo.getValueAt(fila, 5);

                    // Mostramos un input pequeño y rápido
                    String lectura = javax.swing.JOptionPane.showInputDialog(null, 
                            "Ajuste de Inventario para: " + nombre + "\nCantidad actual: " + stockActual, 
                            "Actualizar Stock", 
                            javax.swing.JOptionPane.QUESTION_MESSAGE);

                    if (lectura != null && !lectura.trim().isEmpty()) {
                        try {
                            Double nuevoStock = Double.parseDouble(lectura);
                            
                            interfaces.IIngredienteBO bo = new objetosNegocio.IngredienteBO();
                            bo.actualizarStock(id, nuevoStock);
                            
                            cargarDatosTabla("", "Todas las medidas"); // Refrescamos para ver el cambio
                            
                        } catch (NumberFormatException ex) {
                            javax.swing.JOptionPane.showMessageDialog(null, "Por favor, introduce un número válido.");
                        } catch (Exception ex) {
                            javax.swing.JOptionPane.showMessageDialog(null, ex.getMessage());
                        }
                    }
                }
            }
        });
        
        // Evento para el botón de búsqueda
        btnBuscar.addActionListener(e -> {
            String texto = txtBusqueda.getText();
            String unidad = cmbMedida.getSelectedItem().toString();
            
            // Llamamos al método con lo que haya escrito el usuario
            cargarDatosTabla(texto, unidad);
        });
    }

    public void cargarDatosTabla(String textoBusqueda, String filtroUnidad) {
        try {
            modelo.setRowCount(0);
            
            interfaces.IIngredienteBO bo = new objetosNegocio.IngredienteBO();
            // ¡Llamamos a nuestro nuevo método de búsqueda!
            java.util.List<dto.IngredienteDTO> lista = bo.buscarIngredientes(textoBusqueda, filtroUnidad);
            
            for (dto.IngredienteDTO ing : lista) {
                Object[] fila = new Object[6];
                
                // ... (AQUÍ VA TODO TU CÓDIGO INTACTO DE LA IMAGEN Y LA FILA) ...
                if (ing.getRutaImagen() != null && !ing.getRutaImagen().isEmpty()) {
                    javax.swing.ImageIcon icon = new javax.swing.ImageIcon(ing.getRutaImagen());
                    java.awt.Image img = icon.getImage().getScaledInstance(50, 50, java.awt.Image.SCALE_SMOOTH);
                    fila[0] = new javax.swing.ImageIcon(img);
                } else {
                    fila[0] = null;
                }
                
                fila[1] = ing.getNombre();
                fila[2] = ing.getUnidadMedida();
                fila[3] = ing.getCantidadActual();
                fila[4] = "🗑️"; 
                fila[5] = ing.getId(); 
                
                modelo.addRow(fila);
            }
        } catch (Exception ex) {
            javax.swing.JOptionPane.showMessageDialog(this, "Error al cargar tabla: " + ex.getMessage());
        }
    }
    /**
     * Extrae los datos de la fila seleccionada y ejecuta el proceso de borrado.
     * @param fila El índice de la fila que el usuario seleccionó en la tabla.
     */
    private void ejecutarBorrado(int fila) {
        // 1. Extraemos el nombre (columna 1) para preguntarle al usuario
        String nombre = (String) modelo.getValueAt(fila, 1);
        
        // 2. Extraemos el ID (columna 5 oculta) para decírselo a la base de datos
        Long idEliminar = (Long) modelo.getValueAt(fila, 5); 

        // 3. Mostramos la alerta de confirmación
        int respuesta = javax.swing.JOptionPane.showConfirmDialog(this, 
            "¿Estás seguro de eliminar el ingrediente: " + nombre + "?", 
            "Confirmar eliminación", 
            javax.swing.JOptionPane.YES_NO_OPTION);

        // 4. Si dice que sí, procedemos a borrar
        if (respuesta == javax.swing.JOptionPane.YES_OPTION) {
            try {
                interfaces.IIngredienteBO bo = new objetosNegocio.IngredienteBO();
                bo.eliminarIngrediente(idEliminar);
                
                javax.swing.JOptionPane.showMessageDialog(this, "Ingrediente eliminado exitosamente.");
                
                // 5. Refrescamos la tabla para que el ingrediente desaparezca visualmente
                cargarDatosTabla("", "Todas las medidas"); 
                
            } catch (Exception ex) {
                javax.swing.JOptionPane.showMessageDialog(this, 
                    ex.getMessage(), 
                    "Error al eliminar", 
                    javax.swing.JOptionPane.ERROR_MESSAGE);
            }
        }
    }
    
    // Getters para que el Coordinador pueda manipular la vista
    public JButton getBtnAgregar() { return btnAgregar; }
    public JTextField getTxtBusqueda() { return txtBusqueda; }
    public JComboBox<String> getCmbMedida() { return cmbMedida; }
    public JButton getBtnBuscar() { return btnBuscar; }
    public DefaultTableModel getModelo() { return modelo; }
    public JTable getTablaIngredientes() { return tablaIngredientes; }
}
