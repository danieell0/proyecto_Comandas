/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pantallas;

import Componentes.Sidebar;
import Componentes.formClienteFrecuente;
import Componentes.panelSuperior;
import Componentes.tablaClientes;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
/**
 *
 * @author Jorge
 */
public class frameBase extends JFrame {

    private boolean menuVisible = false;

    public frameBase(Sidebar sliede, formClienteFrecuente form, panelSuperior pa, tablaClientes tabla) {
        sliede.setPreferredSize(new Dimension(0, 0));

        setTitle("Sistema de Comandas");
        setSize(1100, 600);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        add(pa, BorderLayout.NORTH);
        add(sliede, BorderLayout.WEST);

        JPanel panelCentro = new JPanel(new GridLayout(1, 2, 20, 0));
        panelCentro.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        panelCentro.setBackground(new Color(245, 247, 250));

        panelCentro.add(form);
        panelCentro.add(tabla);

        add(panelCentro, BorderLayout.CENTER);

        JPanel panelInferior = new JPanel();
        panelInferior.setBackground(new Color(20, 87, 87));
        panelInferior.setPreferredSize(new Dimension(0, 30));
        add(panelInferior, BorderLayout.SOUTH);

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

        form.getBtnGuardar().addActionListener(e -> {
            tabla.getModelo().addRow(new Object[]{
                form.getTxtNombre().getText(),
                form.getTxtTelefono().getText(),
                form.getTxtTelefono().getText(),
                0, 0
            });
            form.getTxtNombre().setText("");
            form.getTxtTelefono().setText("");
            form.getTxtCorreo().setText("");
        });

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

    }
}
