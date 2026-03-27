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
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JSplitPane;

/**
 *
 * @author Jorge
 */
public class frameBase extends JFrame {

    private boolean menuVisible = false;

    public frameBase(Sidebar sliede, formClienteFrecuente form, panelSuperior pa, tablaClientes tabla, barraBusqueda barrab) {
        //tamaño del menu 
        sliede.setPreferredSize(new Dimension(0, 0));

        //titulo del panel 
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

        //el action listener del boton de guardar 
        form.getBtnGuardar().addActionListener(e -> {
            tabla.getModelo().addRow(new Object[]{
                form.getTxtNombres().getText(),
                form.getTxtApellidoPaterno().getText(),
                form.getTxtApellidoMatero().getText(),
                form.getTxtTelefono().getText(),
                form.getTxtTelefono().getText(),
                0, 0, "Eliminar"
            });
            form.getTxtNombres().setText("");
            form.getTxtApellidoPaterno().setText("");
            form.getTxtApellidoMatero().setText("");
            form.getTxtTelefono().setText("");
            form.getTxtCorreo().setText("");
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

        tabla.getTabla().addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                //obtenemos la fila seleccionada 
                int fila = tabla.getTabla().getSelectedRow();
                //obtenemos la columna seleccionada
                int columna = tabla.getTabla().getSelectedColumn();
                //si la columna es identica a 7 quiere decir que quiere eliminar
                if (columna == 7) {
                    //preguntamos que si lo desea eliminar
                    int opcion = JOptionPane.showConfirmDialog(null,"¿Eliminar cliente?","Confirmar",JOptionPane.YES_NO_OPTION);
                    //si la opcion es si elimina la fila o el registro
                    if (opcion == JOptionPane.YES_OPTION) {
                        tabla.getModelo().removeRow(fila);
                    }
                }
            }
        });
    }
}
