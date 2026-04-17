/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pantallas;

import Enums.TipoAgregar;
import Enums.TipoProducto;
import controlador.Coordinadoor;
import controlador.controlDeNavegacion;
import dto.IngredienteSeleccionadoDTO;
import dto.ProductoDTO;
import excepciones.NegocioExcepcion;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import javax.swing.JButton;
import javax.swing.JComboBox;
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
public class ActualizarProductoFrame extends JFrame {

    private JTextField txtNombre;
    private JTextArea txtDescripcion;
    private JTextField txtPrecio;
    private JComboBox<TipoProducto> cbCategoria;

    private JTable tablaIngredientes;
    private DefaultTableModel modeloIngredientes;

    private ProductoDTO producto;

    public ActualizarProductoFrame(ProductoDTO producto) {
        this.producto = producto;

        setTitle("Actualizar Producto");
        setSize(500, 600);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        JPanel header = new JPanel();
        header.setBackground(new Color(20, 87, 87));
        header.setPreferredSize(new Dimension(500, 60));
        JLabel titulo = new JLabel("Actualizar Producto");
        titulo.setForeground(Color.WHITE);
        titulo.setFont(new Font("Segoe UI", Font.BOLD, 18));

        header.add(titulo);
        add(header, BorderLayout.NORTH);

        JPanel panelCentro = new JPanel(new GridBagLayout());
        panelCentro.setBackground(new Color(240, 240, 240));

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        gbc.gridx = 0;
        gbc.gridy = 0;
        panelCentro.add(new JLabel("Nombre:"), gbc);

        gbc.gridx = 1;
        txtNombre = new JTextField();
        txtNombre.setEnabled(false);
        panelCentro.add(txtNombre, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        panelCentro.add(new JLabel("Categoría:"), gbc);

        gbc.gridx = 1;
        cbCategoria = new JComboBox<>(TipoProducto.values());
        cbCategoria.setEnabled(false);
        panelCentro.add(cbCategoria, gbc);

        gbc.gridx = 0;
        gbc.gridy = 2;
        panelCentro.add(new JLabel("Descripción:"), gbc);

        gbc.gridx = 1;
        txtDescripcion = new JTextArea(3, 20);
        txtDescripcion.setLineWrap(true);
        txtDescripcion.setWrapStyleWord(true);
        panelCentro.add(new JScrollPane(txtDescripcion), gbc);

        gbc.gridx = 0;
        gbc.gridy = 3;
        panelCentro.add(new JLabel("Precio:"), gbc);

        gbc.gridx = 1;
        txtPrecio = new JTextField();
        panelCentro.add(txtPrecio, gbc);

        gbc.gridx = 0;
        gbc.gridy = 4;
        gbc.gridwidth = 2;
        panelCentro.add(new JLabel("Ingredientes:"), gbc);

        modeloIngredientes = new DefaultTableModel(
                new String[]{"ID", "Nombre", "Cantidad"}, 0
        ) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return column == 2;
            }
        };

        tablaIngredientes = new JTable(modeloIngredientes);
        gbc.gridy = 5;
        panelCentro.add(new JScrollPane(tablaIngredientes), gbc);
        JButton btnAgregarIngredientes = new JButton("Agregar Ingredientes");
        btnAgregarIngredientes.setBackground(new Color(46, 125, 90));
        btnAgregarIngredientes.setForeground(Color.WHITE);
        gbc.gridy = 6;
        panelCentro.add(btnAgregarIngredientes, gbc);

        add(panelCentro, BorderLayout.CENTER);

        JPanel footer = new JPanel();
        footer.setBackground(new Color(20, 87, 87));
        footer.setPreferredSize(new Dimension(500, 60));
        JButton btnCancelar = new JButton("Cancelar");
        JButton btnGuardar = new JButton("Guardar Cambios");
        btnGuardar.setBackground(new Color(46, 125, 90));
        btnGuardar.setForeground(Color.WHITE);
        footer.add(btnCancelar);
        footer.add(btnGuardar);

        add(footer, BorderLayout.SOUTH);

        cargarDatos();

        btnAgregarIngredientes.addActionListener(e -> {
            controlDeNavegacion.getcontrolNavegacion().abrirAgregarGenerico("Ingredientes", TipoAgregar.INGREDIENTE);
        });
        
        btnGuardar.addActionListener(e->{
            guardar();
        });
        
        btnCancelar.addActionListener(e->{
            dispose();
        });
        
    }

    private void cargarDatos() {
        txtNombre.setText(producto.getNombre());
        txtDescripcion.setText(producto.getDescripcion());
        txtPrecio.setText(String.valueOf(producto.getPrecio()));
        cbCategoria.setSelectedItem(producto.getTipo());

        modeloIngredientes.setRowCount(0);

        if (producto.getReceta() != null) {
            for (IngredienteSeleccionadoDTO x : producto.getReceta()) {
                modeloIngredientes.addRow(new Object[]{
                    x.getId(),
                    x.getNombre(),
                    x.getCantidad()
                });
            }
        }
        Coordinadoor.getCoordinador().setIngredientesSeleccionados(producto.getReceta());
    }

    private void guardar() {
        try {
            double precio = Double.parseDouble(txtPrecio.getText());

            if (precio <= 0) {
                JOptionPane.showMessageDialog(this, "Precio invalido");
                return;
            }

            producto.setPrecio(precio);
            producto.setDescripcion(txtDescripcion.getText());
            producto.setReceta(Coordinadoor.getCoordinador().getIngredientesSeleccionados());
            Coordinadoor.getCoordinador().actualizarProducto(producto);
            controlDeNavegacion.getcontrolNavegacion().abrirProductos();
            JOptionPane.showMessageDialog(this, "Producto actualizado");
            dispose();
        } catch (NegocioExcepcion e) {
            JOptionPane.showMessageDialog(this, e.getMessage());
        }
    }

}
