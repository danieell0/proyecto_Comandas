/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pantallas;

import Componentes.Sidebar;
import Componentes.panelSuperior;
import DAO.ProductoDAO;
import Enums.EstadoProducto;
import controlador.Coordinadoor;
import controlador.controlDeNavegacion;
import dto.ProductoDTO;
import excepciones.NegocioExcepcion;
import interfaces.IProductoBO;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.List;
import javax.swing.BorderFactory;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
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
import objetosNegocio.ProductoBO;
import org.eclipse.persistence.internal.jpa.parsing.DivideNode;

/**
 *
 * @author Jorge
 */
public class ProductosFrames extends JFrame {

    private boolean menuVisible = false;
    private DefaultTableModel modelo;
    private JTable tabla;
    private JTextField txtbuscar;
    private JComboBox<String> cbCategorias;
    private JComboBox<String> cbEstados;

    public ProductosFrames(panelSuperior pa, Sidebar sliede) {

        //agregamos el titulo 
        setTitle("Productos");
        //le asignamos un tamaño
        setSize(1200, 600);
        //cierra el frame al dale a la cuz 
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        //agregamos un layout 
        setLayout(new BorderLayout());

        //creamos el slide con el espacio 0,0 para que no aparesca al iniciar
        sliede.setPreferredSize(new Dimension(0, 0));
        //agregamos componentes
        add(pa, BorderLayout.NORTH);
        add(sliede, BorderLayout.WEST);

        //creamos el panel del centro 
        JPanel panelCentro = new JPanel(new BorderLayout());
        //le ponemos color al panel del centro 
        panelCentro.setBackground(new Color(220, 220, 220));
        //agregamos 
        add(panelCentro, BorderLayout.CENTER);

        //agregamos un panel que almacenara el textfiel y los combo box 
        JPanel panelSuperior = new JPanel(new FlowLayout(FlowLayout.LEFT, 15, 10));

        //creamos la barra buscadora
        txtbuscar = new JTextField("Buscar Producto");
        //le damos su dimencion a la barra buscadora 
        txtbuscar.setPreferredSize(new Dimension(250, 30));

        txtbuscar.addFocusListener(new FocusAdapter() {
            //este metodo se ejecuta cuando hago click en el campo 
            @Override
            public void focusGained(FocusEvent e) {
                //ve si el texto que esta en el textfiel es ese, si es lo elimina
                if (txtbuscar.getText().equals("Buscar Producto")) {
                    txtbuscar.setText("");
                }
            }

            //este se ejecuta si no modifico nada
            @Override
            public void focusLost(FocusEvent e) {
                //ve si esta vacio, si lo esta regresa el texto
                if (txtbuscar.getText().isEmpty()) {
                    txtbuscar.setText("Buscar Producto");
                }
            }
        });

        //creamos los combobox de los filtros 
        cbCategorias = new JComboBox<>(new String[]{"Todas", "Platillo", "Bebida", "Postre"});
        cbEstados = new JComboBox<>(new String[]{"Todos", "Activo", "Inactivo"});

        //agregamos 
        panelSuperior.add(txtbuscar);
        panelSuperior.add(new JLabel("Categoria: "));
        panelSuperior.add(cbCategorias);
        panelSuperior.add(new JLabel("Estado: "));
        panelSuperior.add(cbEstados);
        panelCentro.add(panelSuperior, BorderLayout.NORTH);

        //creamos una lista con los encabezados 
        String columnas[] = {"ID", "Imagen Producto", "Nombre", "Precio", "Tipo", "Estado"};

        modelo = new DefaultTableModel() {
            @Override
            public Class<?> getColumnClass(int column) {
                if (column == 1) {
                    return Icon.class;
                }
                return Object.class;
            }

            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        //agregamos al modelo los encabezados 
        modelo.setColumnIdentifiers(columnas);

        //creamos la tabla y le agregamos el modelo 
        tabla = new JTable(modelo);
        //le damos un tamaño a la columna
        tabla.setRowHeight(60);
        //le asignamos un formato a la letra 
        tabla.setFont(new Font("Segoe UI", Font.PLAIN, 14));

        tabla.getColumnModel().getColumn(0).setMinWidth(0);
        tabla.getColumnModel().getColumn(0).setMaxWidth(0);
        tabla.getColumnModel().getColumn(0).setWidth(0);

        //creamos un scroll y le agregamos la tabla 
        JScrollPane scroll = new JScrollPane(tabla);
        //agregamos el scroll al panel centro y ese scroll contiene la tabla 
        panelCentro.add(scroll, BorderLayout.CENTER);

        //creamos una panel para los botones 
        JPanel panelBoton = new JPanel(new FlowLayout(FlowLayout.CENTER));
        //le damos el margen a los botones 
        panelBoton.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 20));

        //creamos los botones
        JButton btnAgregar = new JButton("Agregar producto");
        JButton btnEditar = new JButton("Editar");
        JButton btnEstado = new JButton("Activar / Desactivar");
        JButton btnEliminar = new JButton("Eliminar");

        //quitamos el efecto de que se marcan unas lineas del boton
        btnAgregar.setFocusPainted(false);
        btnEditar.setFocusPainted(false);
        btnEstado.setFocusPainted(false);
        btnEliminar.setFocusPainted(false);
        //le damos un color al texto de los botones 
        btnAgregar.setForeground(Color.WHITE);
        btnEditar.setForeground(Color.WHITE);
        btnEstado.setForeground(Color.WHITE);
        btnEliminar.setForeground(Color.WHITE);
        //les asignamos un color 
        btnAgregar.setBackground(new Color(0, 120, 120));
        btnEditar.setBackground(new Color(0, 120, 120));
        btnEstado.setBackground(new Color(0, 120, 120));
        btnEliminar.setBackground(new Color(0, 120, 120));
        //les asignamos una tamaño 
        btnAgregar.setPreferredSize(new Dimension(200, 40));
        btnEditar.setPreferredSize(new Dimension(100, 40));
        btnEstado.setPreferredSize(new Dimension(200, 40));
        btnEliminar.setPreferredSize(new Dimension(100, 40));

        //agregamos 
        panelBoton.add(btnAgregar);
        panelBoton.add(btnEditar);
        panelBoton.add(btnEliminar);
        panelBoton.add(btnEstado);

        //le agregamos el acction listener al boton de agregar 
        btnEstado.addActionListener(e -> {
            //obtiene el numero de la fila seleccionada
            int fila = tabla.getSelectedRow();
            // si es -1 es que no seleccionaste nada y muestra el alerta y si no elimina la fila seleccionada
            if (fila != -1) {
                try {
                    Long id = (Long) modelo.getValueAt(fila, 0);

                    int ok = JOptionPane.showConfirmDialog(this, "¿Deseas cambiar el estado del producto?",
                            "Confirmar",
                            JOptionPane.YES_NO_OPTION);

                    if (ok == JOptionPane.YES_OPTION) {
                        Coordinadoor.getCoordinador().cambiarEstadoProducto(id);
                        cargarProductos();
                    }
                } catch (NegocioExcepcion q) {
                    JOptionPane.showMessageDialog(this, q.getMessage());
                }
            } else {
                JOptionPane.showMessageDialog(this, "Selecciona una fila");
            }
        });

        btnEditar.addActionListener(e -> {
            //obtiene la fila seleccionada
            int fila = tabla.getSelectedRow();
            //si es -1 es que no seleccionaste nd y si no deberia de mostrar la fila seleccionada
            if (fila != -1) {
                try {
                    Long id = (Long) modelo.getValueAt(fila, 0);
                    ProductoDTO productoAc= Coordinadoor.getCoordinador().obtenerProductoPorId(id);
                    controlDeNavegacion.getcontrolNavegacion().abrirActualizarProducto(productoAc);
                    dispose();
                } catch (NegocioExcepcion ex) {
                   JOptionPane.showMessageDialog(null, ex.getMessage());
                }

            } else {
                JOptionPane.showMessageDialog(this, "Selecciona una fila");
            }
        });

        btnEliminar.addActionListener(e -> {
            int fila = tabla.getSelectedRow();

            if (fila != -1) {
                try {
                    int ok = JOptionPane.showConfirmDialog(this, "¿Deseas eliminar el producto?",
                            "Confirmar",
                            JOptionPane.YES_NO_OPTION);
                    if (ok == JOptionPane.YES_OPTION) {
                        Long id = (Long) modelo.getValueAt(fila, 0);
                        Coordinadoor.getCoordinador().eliminarProducto(id);
                        cargarProductos();
                    }
                } catch (NegocioExcepcion q) {
                    JOptionPane.showMessageDialog(this, q.getMessage());
                }
            } else {
                JOptionPane.showMessageDialog(this, "Selecciona una fila.");
            }
        });

        btnAgregar.addActionListener(e -> {
            controlDeNavegacion.getcontrolNavegacion().abrirAgregarProductos();
            dispose();
            cargarProductos();
        });

        //creamos el panel inferior 
        JPanel panelInferior = new JPanel();
        //le damos color 
        panelInferior.setBackground(new Color(20, 87, 87));
        //le damos tamaño 
        panelInferior.setPreferredSize(new Dimension(0, 65));
        //creamos un panel sur con un border layout 
        JPanel contenedorSur = new JPanel(new BorderLayout());
        //agregamos componentes
        contenedorSur.add(panelBoton, BorderLayout.NORTH);
        contenedorSur.add(panelInferior, BorderLayout.SOUTH);
        add(contenedorSur, BorderLayout.SOUTH);

        //hacemos que llamen al metodo de aplicar filtros cada ves que se ineractua con uno de los 2
        cbCategorias.addActionListener(e -> aplicarFiltros());
        cbEstados.addActionListener(e -> aplicarFiltros());

        txtbuscar.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                aplicarFiltros();
            }
        });

        //esto es del slide bar 
        pa.getBtnMenu().addActionListener(e -> {
            if (menuVisible) {
                sliede.setPreferredSize(new Dimension(0, 0));
            } else {
                sliede.setPreferredSize(new Dimension(200, 0));
            }
            menuVisible = !menuVisible;
            sliede.revalidate();
            sliede.repaint();
        });

        aplicarFiltros();
    }

    private void cargarProductos() {
        //con esto limpiamos la tabla
        modelo.setRowCount(0);
        try {
            //obtenemos la lista de productos
            List<ProductoDTO> lista = Coordinadoor.getCoordinador().obtenerProductos();
            //recorremos la lista 
            for (ProductoDTO x : lista) {
                //agregamos el producto a la tabla 
                modelo.addRow(new Object[]{
                    x.getId(),
                    cargarImagen(x.getRutaImagen()),
                    x.getNombre(),
                    "$" + x.getPrecio(),
                    x.getTipo(),
                    x.getEstado()
                });
            }
        } catch (NegocioExcepcion ex) {
            JOptionPane.showMessageDialog(this, ex);
        }
    }

    private void aplicarFiltros() {
        try {
            //obtiene el nombre que esta en el text field
            String nombre = txtbuscar.getText();
            //si es el de buscar producto lo elimina porque rompe el filtro 
            if (nombre.equals("Buscar Producto")) {
                nombre = "";
            }
            //obtenemos la categoria y el estadi del combobox
            String categoria = cbCategorias.getSelectedItem().toString();
            String estado = cbEstados.getSelectedItem().toString();

            //obtenemos la lista filtrada de la bo
            List<ProductoDTO> lista = Coordinadoor.getCoordinador().filtrarProductos(nombre, categoria, estado);
            //eliminamos los registros que hay en la tabla 
            modelo.setRowCount(0);

            //practicamente sobrescribimos la tabla 
            for (ProductoDTO x : lista) {
                String estadoTexto;
                if (x.getEstado() == EstadoProducto.ACTIVO) {
                    estadoTexto = "ACTIVO";
                } else {
                    estadoTexto = "INACTIVO";
                }
                modelo.addRow(new Object[]{
                    x.getId(),
                    cargarImagen(x.getRutaImagen()),
                    x.getNombre(),
                    "$" + x.getPrecio(),
                    x.getTipo(),
                    estadoTexto
                });
            }
        } catch (NegocioExcepcion e) {
            JOptionPane.showMessageDialog(this, e.getMessage());
        }
    }

    //metodo para las imagenes 
    private ImageIcon cargarImagen(String ruta) {
        try {
            ImageIcon icon = new ImageIcon(ruta);
            Image img = icon.getImage().getScaledInstance(50, 50, Image.SCALE_SMOOTH);
            return new ImageIcon(img);
        } catch (Exception e) {
            return null;
        }
    }

}
