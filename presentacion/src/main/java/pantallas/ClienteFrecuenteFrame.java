/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pantallas;

import Componentes.Sidebar;
import Componentes.barraBusqueda;
import Componentes.formClienteFrecuente;
import Componentes.panelSuperior;
import Componentes.tablaClientes;
import controlador.Coordinadoor;
import dto.ClienteDTO;
import excepciones.NegocioExcepcion;
import java.awt.BorderLayout;
import java.awt.Color;
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
import javax.swing.JPanel;
import javax.swing.JSplitPane;
import javax.swing.table.DefaultTableModel;

/**
 * Ventana principal (Frame) para la gestión del módulo de Clientes Frecuentes.
 * Actúa como el Orquestador de la Vista: ensambla todos los componentes gráficos aislados 
 * (paneles, tablas, formularios) y captura sus eventos para delegarlos al Coordinador.
 * @author Jorge
 */
public class ClienteFrecuenteFrame extends JFrame {

    private boolean menuVisible = false;
    private tablaClientes tabla;

    /**
     * Constructor principal del Frame. 
     * Recibe los componentes visuales mediante Inyección de Dependencias, los acomoda 
     * usando Layouts (BorderLayout y JSplitPane) y establece los "Listeners" (escuchadores 
     * de eventos) para los botones, la tabla y la barra de búsqueda.
     * * @param sliede Menú lateral colapsable.
     * @param form Formulario para el registro/edición de clientes.
     * @param pa Panel superior con el botón de menú.
     * @param tabla Componente que contiene el JTable para mostrar los datos.
     * @param barrab Componente con el campo de texto para buscar clientes.
     */
    public ClienteFrecuenteFrame(Sidebar sliede, formClienteFrecuente form, panelSuperior pa, tablaClientes tabla, barraBusqueda barrab) {
        this.tabla = tabla;
        //tamaño del menu 
        sliede.setPreferredSize(new Dimension(0, 0));

        //titulo del panell
        setTitle("Sistema de Comandas");
        //tamaño
        setSize(1200, 600);
        //cerramos el frame 
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        //le agregamos el border layout
        setLayout(new BorderLayout());

        //agregamos elementos 
        add(pa, BorderLayout.NORTH);
        add(sliede, BorderLayout.WEST);

        //creamos un panel donde estara la barra de busqueda y la tabla
        JPanel panelTabla = new JPanel(new BorderLayout());
        //agregamos los elementos 
        panelTabla.add(barrab, BorderLayout.NORTH);
        panelTabla.add(tabla, BorderLayout.CENTER);
        tabla.getTabla().getColumnModel().getColumn(0).setMinWidth(0);
        tabla.getTabla().getColumnModel().getColumn(0).setMaxWidth(0);
        tabla.getTabla().getColumnModel().getColumn(0).setWidth(0);
        //utilizamos el JSplitPane para dividir la pantalla en 2
        JSplitPane split = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, form, panelTabla);
        //este es el tamaño del lado izquierdo 
        split.setDividerLocation(350);
        //este es el grosor de la linea divisora 
        split.setDividerSize(5);
        //define quien crece cuando cambio el tamaño de la ventana
        split.setResizeWeight(0);
        //agrego el split al frame 
        add(split, BorderLayout.CENTER);

        //modifico el panel inferior 
        JPanel panelInferior = new JPanel();
        panelInferior.setBackground(new Color(20, 87, 87));
        panelInferior.setPreferredSize(new Dimension(0, 30));
        add(panelInferior, BorderLayout.SOUTH);

        //agrego el action listener del boton del menu 
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

        //action listener de la tabla
        tabla.getTabla().addMouseListener(new MouseAdapter() {
            public void mouseClicket(MouseEvent e) {
                if (e.getClickCount() == 2) {
                    int fila = tabla.getTabla().getSelectedRow();
                    int col = tabla.getTabla().getSelectedColumn();

                    String valor = tabla.getModelo().getValueAt(fila, col).toString();

                    String nuevo = JOptionPane.showInputDialog("Editar", valor);

                    if (valor != null) {
                        tabla.getModelo().setValueAt(nuevo, fila, col);
                    }
                }
            }
        });
        //este es action listener de la barra navegadora  
        barrab.getTxtBuscar().addFocusListener(new FocusAdapter() {
            @Override
            public void focusGained(FocusEvent e) {
                if (barrab.getTxtBuscar().getText().equals("Search...")) {
                    barrab.getTxtBuscar().setText("");
                    barrab.getTxtBuscar().setForeground(Color.BLACK);
                }
            }

            @Override
            public void focusLost(FocusEvent e) {
                if (barrab.getTxtBuscar().getText().isEmpty()) {
                    barrab.getTxtBuscar().setText("Search...");
                    barrab.getTxtBuscar().setText("");
                    barrab.getTxtBuscar().setForeground(Color.GRAY);
                }
            }
        });
        
        barrab.getTxtBuscar().addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                accionBuscar();
            }

            private void accionBuscar() {
                String texto = barrab.getTxtBuscar().getText();
                List<ClienteDTO> filtrados = Coordinadoor.getCoordinador().buscarClientes(texto);
                actualizarTabla(filtrados);
            }

            private void actualizarTabla(List<ClienteDTO> lista) {
                tabla.getModelo().setRowCount(0); // Limpiar filas existentes
                if (lista != null) {
                    for (ClienteDTO c : lista) {
                        Object[] fila = {
                            c.getId(),
                            c.getNombre(),
                            c.getApellidoPaterno(),
                            c.getApellidoMaterno(),
                            c.getCorreoElectronico(),
                            c.getTelefono(),
                            c.getNumeroVisitas(),
                            c.getPuntosFidelidad()
                        };
                        tabla.getModelo().addRow(fila);
                    }
                }
            }

        });
        tabla.getTabla().addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                
                    int fila = tabla.getTabla().getSelectedRow();
                    int columna = tabla.getTabla().getSelectedColumn();

                    if (e.getClickCount() == 1 && columna == 8) {
                        int opcion = JOptionPane.showConfirmDialog(null, "¿Desea eliminar el cliente?", "Confirmar", JOptionPane.YES_NO_OPTION);
                        try {
                            Long id = (Long) tabla.getModelo().getValueAt(fila, 0);
                            Coordinadoor.getCoordinador().eliminarClientes(id);
                            cargarTabla();
                        } catch (Exception ex) {
                            JOptionPane.showMessageDialog(null, "Error al eliminar el cliente");
                        }
                    }

                    if (e.getClickCount() == 2 && columna != 8) {
                        String valorActual = tabla.getModelo().getValueAt(fila, columna).toString();
                        String valorNuevo = JOptionPane.showInputDialog("Editar", valorActual);
                        if (valorNuevo != null) {
                            tabla.getModelo().setValueAt(valorNuevo, fila, columna);
                        }

                        try {
                            ClienteDTO cliente = new ClienteDTO();

                            cliente.setId((Long) tabla.getModelo().getValueAt(fila, 0));
                            cliente.setNombre(tabla.getModelo().getValueAt(fila, 1).toString());
                            cliente.setApellidoPaterno(tabla.getModelo().getValueAt(fila, 2).toString());
                            cliente.setApellidoMaterno(tabla.getModelo().getValueAt(fila, 3).toString());
                            cliente.setTelefono(tabla.getModelo().getValueAt(fila, 4).toString());
                            cliente.setCorreoElectronico(tabla.getModelo().getValueAt(fila, 5).toString());
                            cliente.setPuntosFidelidad(Double.parseDouble(tabla.getModelo().getValueAt(fila, 6).toString()));
                            cliente.setNumeroVisitas(Integer.parseInt(tabla.getModelo().getValueAt(fila, 7).toString()));
                            Coordinadoor.getCoordinador().guardarCliente(cliente);

                        } catch (Exception ex) {
                            JOptionPane.showMessageDialog(null, "Error al actualizar el cliente");
                        }
                    }
                }
        });

        //el action listener del boton de guardar 
        form.getBtnGuardar().addActionListener(e -> {
            try {
                ClienteDTO cliente = new ClienteDTO();
                cliente.setNombre(form.getTxtNombres().getText());
                cliente.setApellidoPaterno(form.getTxtApellidoPaterno().getText());
                cliente.setApellidoMaterno(form.getTxtApellidoMatero().getText());
                cliente.setTelefono(form.getTxtTelefono().getText());
                cliente.setCorreoElectronico(form.getTxtCorreo().getText());
                Coordinadoor.getCoordinador().guardarCliente(cliente);
                cargarTabla();

                form.getTxtNombres().setText("");
                form.getTxtApellidoPaterno().setText("");
                form.getTxtApellidoMatero().setText("");
                form.getTxtTelefono().setText("");
                form.getTxtCorreo().setText("");

            } catch (Exception ex) {
                JOptionPane.showMessageDialog(null, "Error al obtener datos del formulario");
            }
        });

    }

    /**
     * Método auxiliar para extraer todos los clientes de la base de datos a través 
     * del coordinador y dibujarlos en el JTable.
     */
    private void cargarTabla() {
        try {
            DefaultTableModel modelo = tabla.getModelo();
            modelo.setRowCount(0);

            Coordinadoor.getCoordinador().obtenerClientes().forEach(cliente -> modelo.addRow(new Object[]{
                cliente.getId(),
                cliente.getNombre(),
                cliente.getApellidoPaterno(),
                cliente.getApellidoMaterno(),
                cliente.getTelefono(),
                cliente.getCorreoElectronico(),
                cliente.getPuntosFidelidad(),
                cliente.getNumeroVisitas(),
                "Eliminar"
            }));
        } catch (NegocioExcepcion e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }

    }

}
