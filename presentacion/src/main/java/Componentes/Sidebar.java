/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Componentes;

import controlador.controlDeNavegacion;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 *
 * @author Jorge
 */
public class Sidebar extends JPanel {

    public Sidebar() {
        setBackground(new Color(20, 87, 87));
        setPreferredSize(new Dimension(200, 0));
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        add(Box.createVerticalStrut(30));

        JLabel titulo = new JLabel("Menu Rapido");
        titulo.setForeground(Color.WHITE);
        titulo.setFont(new Font("Segoe UI", Font.BOLD, 18));
        titulo.setAlignmentX(Component.CENTER_ALIGNMENT);

        add(titulo);
        add(Box.createVerticalStrut(20));

        add(crearBoton("Menu Principal", new Color(220, 50, 50)));
        add(crearBoton("Comandas", new Color(63, 63, 160)));
        add(crearBoton("Productos", new Color(46, 139, 87)));
        add(crearBoton("Ingredientes", new Color(184, 115, 51)));
        add(crearBoton("Clientes Frecuentes", new Color(150, 70, 150)));
        add(crearBoton("Reportes", new Color(100, 100, 100)));
    }

    private JButton crearBoton(String texto, Color color) {
        JButton btn = new JButton(texto);
        btn.setMaximumSize(new Dimension(Integer.MAX_VALUE, 50));
        btn.setAlignmentX(Component.CENTER_ALIGNMENT);
        btn.setFocusPainted(false);
        btn.setFont(new Font("Segoe UI", Font.BOLD, 14));
        btn.setForeground(Color.WHITE);
        btn.setBackground(color);
        btn.setBorder(BorderFactory.createEmptyBorder(10, 15, 10, 15));
        btn.addMouseListener(new MouseAdapter() {
            public void mouseEntered(MouseEvent e) {
                btn.setBackground(color.darker());
            }

            public void mouseExited(MouseEvent e) {
                btn.setBackground(color);
            }
        });

        btn.addActionListener(e -> abrir(texto));
        add(Box.createVerticalStrut(10));
        return btn;
    }

    private void abrir(String modulo) {
        controlDeNavegacion nav = controlDeNavegacion.getcontrolNavegacion();

        switch (modulo) {
            case "Menu Principal":
                nav.abrirMenuPrincipal();
                break;
            case "Comandas":
                nav.abrirComandas();
                break;
            case "Productos":
                nav.abrirFrameBase();
                break;
            case "Ingredientes":
                nav.abrirFrameBase();
                break;
            case "Clientes Frecuentes":
                nav.abrirFrameBase();
                break;
            case "Reportes":
                nav.abrirFrameBase();
                break;
        }
    }
}
