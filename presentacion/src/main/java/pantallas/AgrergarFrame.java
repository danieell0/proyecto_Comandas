/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pantallas;

import Enums.TipoAgregar;
import controlador.Coordinadoor;
import dto.IngredienteDTO;
import dto.IngredienteSeleccionadoDTO;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.List;
import javax.swing.BorderFactory;
import javax.swing.DefaultCellEditor;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;

/**
 *
 * @author Jorge
 */
public class AgrergarFrame extends JFrame {

    private TipoAgregar tipo;
    private DefaultTableModel modelo;
    private JTextField txtBuscar;

    public AgrergarFrame(String nombre, TipoAgregar tipo) {
        this.tipo = tipo;
        setTitle("Agregar " + nombre);
        setSize(600, 500);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        JPanel header = new JPanel();
        header.setBackground(new Color(20, 87, 87));
        header.setPreferredSize(new Dimension(600, 60));
        header.setLayout(new FlowLayout(FlowLayout.CENTER));

        JLabel titulo = new JLabel("Agregar " + nombre);
        titulo.setForeground(Color.WHITE);
        titulo.setFont(new Font("Segoe UI", Font.BOLD, 18));

        header.add(titulo);
        add(header, BorderLayout.NORTH);

        JPanel panelCentro = new JPanel(new BorderLayout());
        panelCentro.setBackground(new Color(240, 240, 240));
        panelCentro.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        txtBuscar = new JTextField("Buscar...");
        txtBuscar.setPreferredSize(new Dimension(200, 35));
        txtBuscar.setForeground(Color.GRAY);
        panelCentro.add(txtBuscar, BorderLayout.NORTH);

        txtBuscar.addFocusListener(new java.awt.event.FocusAdapter() {
            @Override
            public void focusGained(java.awt.event.FocusEvent e) {
                if (txtBuscar.getText().equals("Buscar...")) {
                    txtBuscar.setText("");
                    txtBuscar.setForeground(Color.BLACK);
                }
            }

            @Override
            public void focusLost(java.awt.event.FocusEvent e) {
                if (txtBuscar.getText().isEmpty()) {
                    txtBuscar.setText("Buscar...");
                    txtBuscar.setForeground(Color.GRAY);
                }
            }
        });

        String[] columnas = {"ID", "Seleccionar", nombre, "Cantidad"};

        modelo = new DefaultTableModel(null, columnas) {
            @Override
            public Class<?> getColumnClass(int columnIndex) {
                if (columnIndex == 1) {
                    return Boolean.class;
                }
                return Object.class;
            }

            @Override
            public boolean isCellEditable(int row, int column) {
                return column == 1 || column == 3;
            }
        };

        JTable tabla = new JTable(modelo);
        tabla.setRowHeight(30);

        TableColumn columnaId = tabla.getColumnModel().getColumn(0);
        tabla.getColumnModel().removeColumn(columnaId);

        tabla.getColumnModel().getColumn(2).setCellEditor(
                new DefaultCellEditor(new JTextField())
        );

        JScrollPane scroll = new JScrollPane(tabla);
        panelCentro.add(scroll, BorderLayout.CENTER);

        add(panelCentro, BorderLayout.CENTER);

        JPanel footer = new JPanel();
        footer.setBackground(new Color(20, 87, 87));
        footer.setPreferredSize(new Dimension(600, 60));

        JButton btnCancelar = new JButton("Cancelar");
        JButton btnAgregar = new JButton("Agregar");

        btnAgregar.setBackground(new Color(46, 125, 90));
        btnAgregar.setForeground(Color.WHITE);

        footer.add(btnCancelar);
        footer.add(btnAgregar);

        add(footer, BorderLayout.SOUTH);

        btnAgregar.addActionListener(e -> {
            if (tipo == TipoAgregar.INGREDIENTE) {

                List<IngredienteSeleccionadoDTO> lista = new ArrayList<>();

                for (int i = 0; i < modelo.getRowCount(); i++) {

                    Boolean seleccionado = (Boolean) modelo.getValueAt(i, 1);
                    if (seleccionado != null && seleccionado) {
                        Long id = (Long) modelo.getValueAt(i, 0);
                        String Nombre = modelo.getValueAt(i, 2).toString();
                        Object valor = modelo.getValueAt(i, 3);
                        if (valor != null && !valor.toString().isEmpty()) {
                            Double cantidad=Double.valueOf(valor.toString());
                            if (cantidad > 0) {
                                lista.add(new IngredienteSeleccionadoDTO(id, Nombre, cantidad));
                            }
                        }
                    }
                }
                Coordinadoor.getCoordinador().setIngredientesSeleccionados(lista);
                dispose();

            } else {
                System.out.println("Logica de productos");
            }
        });

        txtBuscar.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                filtrarIngredientes();
            }
        });

        if (tipo == TipoAgregar.INGREDIENTE) {
            cargarIngredientes();
        } else {

        }

    }

    private void filtrarIngredientes() {
        String nombre = txtBuscar.getText();
        if ("Busacar...".equals(nombre)) {
            nombre = "";
        }
        List<IngredienteDTO> lista = Coordinadoor.getCoordinador().buscarIngredientes(nombre);
        modelo.setRowCount(0);

        for (IngredienteDTO x : lista) {
            modelo.addRow(new Object[]{
                x.getId(),
                false,
                x.getNombre(),
                0.0
            });
        }
    }

    private void cargarIngredientes() {
        try {
            List<IngredienteDTO> lista = Coordinadoor.getCoordinador().obtenerListaIngredientes();
            modelo.setRowCount(0);
            for (IngredienteDTO x : lista) {
                modelo.addRow(new Object[]{
                    x.getId(), false, x.getNombre(), 0
                });
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
