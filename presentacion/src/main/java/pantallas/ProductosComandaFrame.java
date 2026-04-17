package pantallas;

import controlador.Coordinadoor;
import controlador.ProductoSeleccionListener;
import dto.ProductoDTO;
import excepciones.NegocioExcepcion;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

/**
 * Ventana para la selección de productos dentro de una comanda.
 * 
 * <p>Permite visualizar una lista de productos disponibles, realizar
 * búsquedas dinámicas y seleccionar un producto mediante doble clic.</p>
 * 
 * <p>Cuando un producto es seleccionado, se notifica a través de un
 * {@link ProductoSeleccionListener}, permitiendo desacoplar la lógica
 * de la interfaz de usuario.</p>
 * 
 * <p>Los datos son obtenidos desde el {@link Coordinadoor}.</p>
 * 
 * @author munos
 */
public class ProductosComandaFrame extends JFrame {

    /** Tabla que muestra los productos */
    private JTable tabla;

    /** Modelo de la tabla */
    private DefaultTableModel modelo;

    /** Listener para manejar la selección de productos */
    private ProductoSeleccionListener listener;

    /** Campo de texto para búsqueda de productos */
    private JTextField txtBuscar;

    /**
     * Constructor que inicializa la interfaz de selección de productos.
     */
    public ProductosComandaFrame() {

        setTitle("Seleccionar Producto");
        setSize(600, 400);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        txtBuscar = new JTextField("Buscar Producto");
        txtBuscar.setPreferredSize(new Dimension(250, 30));

        // Manejo de placeholder en el campo de búsqueda
        txtBuscar.addFocusListener(new FocusAdapter() {
            @Override
            public void focusGained(FocusEvent e) {
                if (txtBuscar.getText().equals("Buscar Producto")) {
                    txtBuscar.setText("");
                }
            }

            @Override
            public void focusLost(FocusEvent e) {
                if (txtBuscar.getText().isEmpty()) {
                    txtBuscar.setText("Buscar Producto");
                }
            }
        });

        add(txtBuscar, BorderLayout.NORTH);

        modelo = new DefaultTableModel(new String[]{
            "ID", "Nombre", "Precio"
        }, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        tabla = new JTable(modelo);
        tabla.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        add(new JScrollPane(tabla), BorderLayout.CENTER);

        cargarInicial();

        // Evento de búsqueda dinámica
        txtBuscar.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {

                String texto = txtBuscar.getText();

                try {
                    List<ProductoDTO> lista = Coordinadoor.getCoordinador()
                            .filtrarProductos(texto, "Todas", "Activo");

                    cargarTabla(lista);

                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(null, "Error al buscar productos");
                }
            }
        });

        // Evento de selección con doble clic
        tabla.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (e.getClickCount() == 2) {
                    int fila = tabla.getSelectedRow();

                    if (fila == -1) {
                        return;
                    }

                    ProductoDTO p = new ProductoDTO();
                    p.setId((Long) modelo.getValueAt(fila, 0));
                    p.setNombre(modelo.getValueAt(fila, 1).toString());
                    p.setPrecio((Double) modelo.getValueAt(fila, 2));

                    if (listener != null) {
                        listener.productoSeleccionado(p);
                    }

                    dispose();
                }
            }
        });
    }

    /**
     * Carga inicial de todos los productos disponibles.
     */
    private void cargarInicial() {
        try {
            List<ProductoDTO> lista
                    = Coordinadoor.getCoordinador().obtenerProductos();

            cargarTabla(lista);

        } catch (NegocioExcepcion e) {
            JOptionPane.showMessageDialog(this, e.getMessage());
        }
    }

    /**
     * Carga una lista de productos en la tabla.
     *
     * @param lista lista de objetos {@link ProductoDTO} a mostrar
     */
    private void cargarTabla(List<ProductoDTO> lista) {
        modelo.setRowCount(0);

        for (ProductoDTO p : lista) {
            modelo.addRow(new Object[]{
                p.getId(),
                p.getNombre(),
                p.getPrecio()
            });
        }
    }

    /**
     * Establece el listener para manejar la selección de un producto.
     *
     * @param l implementación de {@link ProductoSeleccionListener}
     */
    public void setProductoSeleccionListener(ProductoSeleccionListener l) {
        this.listener = l;
    }
}
