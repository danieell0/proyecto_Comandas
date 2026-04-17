/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pantallas;

import Componentes.Sidebar;
import Componentes.barraBusqueda;
import Componentes.panelSuperior;
import Componentes.tablaClientes;
import Enums.EstadoComandas;
import Enums.EstadoMesa;
import controlador.Coordinadoor;
import dto.ComandaDTO;
import dto.MesaDTO;
import dto.ClienteDTO;
import dto.DetalleProductoDTO;
import dto.ProductoDTO;
import interfaces.IMesaBO;
import interfaces.IComandaBO;
import objetosNegocio.MesaBO;
import objetosNegocio.ComandaBO;
import excepciones.NegocioExcepcion;
import java.awt.*;
import java.awt.event.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class ComandasFrame extends JFrame {

    private Coordinadoor coordinador = Coordinadoor.getCoordinador();

    private MesaDTO mesaSeleccionada;
    private ClienteDTO clienteSeleccionado = null;
    private List<DetalleProductoDTO> detalles = new ArrayList<>();

    private JComboBox<String> comboCliente;
    private JButton btnBuscarCliente;
    private JTable tabla;
    private DefaultTableModel modelo;

    private JTextField txtFolio;
    private JTextField txtFecha;
    private JTextField txtEstado;
    private JLabel lblTotal;
    private JLabel lblMesa;

    private boolean modoEdicion = false;
    private ComandaDTO comandaActual = null;

    public ComandasFrame(Sidebar sidebar, panelSuperior pa) {

        setTitle("Comanda");
        setSize(1000, 600);
        setLayout(new BorderLayout());

        add(pa, BorderLayout.NORTH);
        add(sidebar, BorderLayout.WEST);

        JPanel panelPrincipal = new JPanel(new BorderLayout(20, 20));
        panelPrincipal.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        JPanel panelTop = new JPanel(new GridLayout(3, 4, 10, 10));

        panelTop.add(new JLabel("Folio:"));
        txtFolio = new JTextField(generarFolio());
        txtFolio.setEditable(false);
        panelTop.add(txtFolio);

        panelTop.add(new JLabel("Fecha:"));
        txtFecha = new JTextField(LocalDateTime.now()
                .format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm")));
        txtFecha.setEditable(false);
        panelTop.add(txtFecha);

        panelTop.add(new JLabel("Mesa:"));
        lblMesa = new JLabel();
        panelTop.add(lblMesa);

        panelTop.add(new JLabel("Cliente:"));

        comboCliente = new JComboBox<>(new String[]{
            "Cliente General",
            "Cliente Frecuente"
        });
        panelTop.add(comboCliente);

        panelTop.add(new JLabel("Estado:"));
        txtEstado = new JTextField("Abierta");
        txtEstado.setEditable(false);
        txtEstado.setForeground(Color.GREEN);
        panelTop.add(txtEstado);

        panelPrincipal.add(panelTop, BorderLayout.NORTH);

        modelo = new DefaultTableModel(
                new String[]{"Cantidad", "Producto", "Comentarios"}, 0
        ) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return column == 0;
            }
        };

        tabla = new JTable(modelo);

        tabla.getColumnModel().getColumn(0).setCellEditor(new DefaultCellEditor(new JTextField()) {
            @Override
            public boolean stopCellEditing() {
                String valor = (String) getCellEditorValue();
                try {
                    int num = Integer.parseInt(valor);
                    if (num <= 0) {
                        JOptionPane.showMessageDialog(null, "Cantidad > 0");
                        return false;
                    }
                } catch (Exception e) {
                    JOptionPane.showMessageDialog(null, "Solo números");
                    return false;
                }
                return super.stopCellEditing();
            }
        });

        modelo.addTableModelListener(e -> {
            if (e.getColumn() == 0) {
                int fila = e.getFirstRow();
                try {
                    int nuevaCantidad = Integer.parseInt(
                            modelo.getValueAt(fila, 0).toString()
                    );

                    DetalleProductoDTO d = detalles.get(fila);
                    d.setCantidad(nuevaCantidad);
                    d.setSubtotal(d.getPrecio() * nuevaCantidad);

                    recalcularTotal();

                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(this, "Cantidad inválida");
                }
            }
        });

        panelPrincipal.add(new JScrollPane(tabla), BorderLayout.CENTER);

        JButton btnAgregar = new JButton("Agregar producto");
        JButton btnGuardar = new JButton("Guardar");
        JButton btnCancelar = new JButton("Cancelar");
        JButton btnEntregar = new JButton("Entregar");
        JButton btnEliminar = new JButton("Eliminar producto");

        JPanel acciones = new JPanel();
        acciones.add(btnAgregar);
        acciones.add(btnEliminar);
        acciones.add(btnGuardar);
        acciones.add(btnCancelar);
        acciones.add(btnEntregar);

        lblTotal = new JLabel("Total: $0");
        JPanel panelBottom = new JPanel(new BorderLayout());
        panelBottom.add(lblTotal, BorderLayout.NORTH);
        panelBottom.add(acciones, BorderLayout.SOUTH);

        panelPrincipal.add(panelBottom, BorderLayout.SOUTH);

        btnBuscarCliente = new JButton("Buscar cliente");
        btnBuscarCliente.setVisible(false);
        panelPrincipal.add(btnBuscarCliente, BorderLayout.WEST);

        add(panelPrincipal);

        comboCliente.addActionListener(e
                -> btnBuscarCliente.setVisible(
                        comboCliente.getSelectedItem().equals("Cliente Frecuente"))
        );

        btnBuscarCliente.addActionListener(e -> {
            ClientesComandasFrame frame
                    = new ClientesComandasFrame(new tablaClientes(), new barraBusqueda());

            frame.setClienteSeleccionListener(cliente -> {
                clienteSeleccionado = cliente;
                JOptionPane.showMessageDialog(this,
                        "Cliente: " + cliente.getNombre());
            });

            frame.setVisible(true);
        });

        btnAgregar.addActionListener(e -> agregarProducto());
        btnGuardar.addActionListener(e -> guardarComanda());
        btnEliminar.addActionListener(e -> eliminarProducto());
        btnCancelar.addActionListener(e -> cerrar(EstadoComandas.CANCELADA));
        btnEntregar.addActionListener(e -> cerrar(EstadoComandas.ENTREGADA));
    }

    public void setMesaSeleccionada(MesaDTO mesa) {
        this.mesaSeleccionada = mesa;
        try {
            ComandaDTO existente
                    = coordinador.obtenerComandaPorMesa(mesa.getId());
            if (existente != null) {
                modoEdicion = true;
                comandaActual = existente;
                cargarComanda(existente); 
            } else {
                modoEdicion = false;
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error al cargar comanda");
        }
    }

    private void agregarProducto() {
        ProductosComandaFrame frame = new ProductosComandaFrame();

        frame.setProductoSeleccionListener(producto -> {

            for (DetalleProductoDTO det : detalles) {
                if (det.getIdProducto().equals(producto.getId())) {

                    det.setCantidad(det.getCantidad() + 1);
                    det.setSubtotal(det.getCantidad() * det.getPrecio());

                    int i = detalles.indexOf(det);
                    modelo.setValueAt(det.getCantidad(), i, 0);

                    recalcularTotal();
                    return;
                }
            }

            DetalleProductoDTO d = new DetalleProductoDTO();
            d.setIdProducto(producto.getId());
            d.setNombreProducto(producto.getNombre());
            d.setPrecio(producto.getPrecio());
            d.setCantidad(1);
            d.setSubtotal(d.getPrecio());

            String comentario = JOptionPane.showInputDialog("Comentario:");
            d.setComentario(comentario != null ? comentario : "");

            detalles.add(d);

            modelo.addRow(new Object[]{
                d.getCantidad(),
                d.getNombreProducto(),
                d.getComentario()
            });

            recalcularTotal();
        });

        frame.setVisible(true);
    }

    private void guardarComanda() {
        if (mesaSeleccionada == null) {
            JOptionPane.showMessageDialog(this, "Mesa no asignada");
            return;
        }
        if (detalles.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Agrega productos");
            return;
        }
        ComandaDTO dto = new ComandaDTO();
        dto.setIdMesa(mesaSeleccionada.getId());
        dto.setIdMesero(1L);

        if (clienteSeleccionado != null) {
            dto.setIdCliente(clienteSeleccionado.getId());
        }
        dto.setDetalles(detalles);
        try {
            coordinador.crearComanda(dto);
            JOptionPane.showMessageDialog(this, "Comanda guardada");
            
        } catch (NegocioExcepcion e) {
            JOptionPane.showMessageDialog(this, e.getMessage());
        }
    }

    private void recalcularTotal() {
        double total = 0;
        for (DetalleProductoDTO d : detalles) {
            total += d.getSubtotal();
        }
        lblTotal.setText("Total: $" + total);
    }
    
    private void cerrar(EstadoComandas estado) {
        if (!modoEdicion) {
            JOptionPane.showMessageDialog(this, "No hay comanda activa");
            return;
        }
        coordinador.cerrarComanda(comandaActual.getId(), estado);
        JOptionPane.showMessageDialog(this, "Comanda cerrada");
        dispose();
    }

    private String generarFolio() {
        String fecha = java.time.LocalDate.now()
                .format(java.time.format.DateTimeFormatter.ofPattern("yyyyMMdd"));
        int consecutivo = (int) (Math.random() * 1000);
        return String.format("OB-%s-%03d", fecha, consecutivo);
    }

    private void eliminarProducto() {
        int fila = tabla.getSelectedRow();

        if (fila < 0) {
            JOptionPane.showMessageDialog(this, "Selecciona un producto");
            return;
        }

        detalles.remove(fila);
        modelo.removeRow(fila);

        recalcularTotal();
    }
    private void cargarComanda(ComandaDTO c) {

    detalles.clear();
    modelo.setRowCount(0);

    for (DetalleProductoDTO d : c.getDetalles()) {

        detalles.add(d);

        modelo.addRow(new Object[]{
            d.getCantidad(),
            d.getNombreProducto(),
            d.getComentario()
        });
    }

    recalcularTotal();
}
}

