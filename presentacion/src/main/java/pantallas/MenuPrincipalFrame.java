/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pantallas;

import Componentes.Sidebar;
import java.awt.BorderLayout;
import Componentes.panelSuperior;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import controlador.controlDeNavegacion; 

public class MenuPrincipalFrame extends JFrame {

    private boolean menuVisible = false;

    public MenuPrincipalFrame(Sidebar sidebar, panelSuperior pa) {
        setTitle("Sistema de Comandas");
        setSize(1200, 650);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        sidebar.setPreferredSize(new Dimension(0, 0));

        add(pa, BorderLayout.NORTH);
        add(sidebar, BorderLayout.WEST);

        JPanel panelCentral = new JPanel();
        panelCentral.setLayout(new GridLayout(2, 3, 30, 30));
        panelCentral.setBorder(BorderFactory.createEmptyBorder(30, 30, 30, 30));
        panelCentral.setBackground(new Color(220, 225, 230));

        JButton btnComandas = crearBoton("Comandas", new Color(63, 63, 160));
        JButton btnProductos = crearBoton("Productos", new Color(46, 139, 87));
        JButton btnIngredientes = crearBoton("Ingredientes", new Color(184, 115, 51));
        JButton btnClientes = crearBoton("Clientes Frecuentes", new Color(150, 70, 150));
        JButton btnReportes = crearBoton("Reportes", new Color(100, 100, 100));
        JButton btnAgregar = crearBoton("Agregar módulo", new Color(220, 50, 50));

        panelCentral.add(btnComandas);
        panelCentral.add(btnProductos);
        panelCentral.add(btnIngredientes);
        panelCentral.add(btnClientes);
        panelCentral.add(btnReportes);
        panelCentral.add(btnAgregar);

        add(panelCentral, BorderLayout.CENTER);

        JPanel panelInferior = new JPanel();
        panelInferior.setBackground(new Color(20, 87, 87));
        panelInferior.setPreferredSize(new Dimension(0, 30));
        add(panelInferior, BorderLayout.SOUTH);

        pa.getBtnMenu().addActionListener(e -> {
            if (menuVisible) {
                sidebar.setPreferredSize(new Dimension(0, 0));
            } else {
                sidebar.setPreferredSize(new Dimension(200, 0));
            }
            menuVisible = !menuVisible;

            sidebar.revalidate();
            sidebar.repaint();
        });

        btnComandas.addActionListener(e -> abrir("Comandas"));
        btnProductos.addActionListener(e -> abrir("Productos"));
        btnIngredientes.addActionListener(e -> abrir("Ingredientes"));
        btnClientes.addActionListener(e -> abrir("Clientes Frecuentes"));
        btnReportes.addActionListener(e -> abrir("Reportes"));
        btnAgregar.addActionListener(e -> abrir("Agregar módulo"));
    }

    private JButton crearBoton(String texto, Color color) {
        JButton btn = new JButton(texto);

        btn.setFocusPainted(false);
        btn.setFont(new Font("Segoe UI", Font.BOLD, 18));
        btn.setForeground(Color.WHITE);
        btn.setBackground(color);
        btn.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        btn.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btn.setBackground(color.darker());
            }

            public void mouseExited(java.awt.event.MouseEvent evt) {
                btn.setBackground(color);
            }
        });

        return btn;
    }

    private void abrir(String modulo) {

        controlDeNavegacion nav = controlDeNavegacion.getcontrolNavegacion();

        switch (modulo) {

            case "Clientes Frecuentes":
                nav.abrirFrameBase();
                break;

            case "Comandas":
                nav.abrirFrameBase();
                break;

            case "Productos":
                nav.abrirFrameBase();
                break;

            case "Ingredientes":
                nav.abrirFrameBase();
                break;

            case "Reportes":
                nav.abrirFrameBase();
                break;

            default:
                nav.abrirMenuPrincipal();
        }
    }
}