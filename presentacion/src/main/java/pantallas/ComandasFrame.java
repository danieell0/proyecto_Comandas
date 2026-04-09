/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pantallas;

import Componentes.Sidebar;
import Componentes.panelSuperior;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

/**
 *
 * 
 * @author munos
 */
public class ComandasFrame extends JFrame {

    private boolean menuVisible = false;

    public ComandasFrame(Sidebar sidebar, panelSuperior pa) {

        setTitle("Sistema de Comandas");
        setSize(1200, 650);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        sidebar.setPreferredSize(new Dimension(0, 0));

        add(pa, BorderLayout.NORTH);
        add(sidebar, BorderLayout.WEST);

        JPanel panelPrincipal = new JPanel(new BorderLayout(20, 20));
        panelPrincipal.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        panelPrincipal.setBackground(new Color(220, 225, 230));

        JPanel panelTop = new JPanel(new GridLayout(3, 4, 15, 10));
        panelTop.setBackground(panelPrincipal.getBackground());

        panelTop.add(new JLabel("Folio:"));
        panelTop.add(new JTextField("OB-20260321-001"));

        panelTop.add(new JLabel("Fecha:"));
        panelTop.add(new JTextField("21/03/2026 10:30"));

        panelTop.add(new JLabel("Mesa disponible:"));
        panelTop.add(new JComboBox<>(new String[]{"Mesa 1", "Mesa 2", "Mesa 3"}));

        panelTop.add(new JLabel("Cliente frecuente:"));
        panelTop.add(new JTextField("Cliente General"));

        panelTop.add(new JLabel("Estado:"));
        JTextField estado = new JTextField("Abierta");
        estado.setForeground(new Color(0, 150, 0));
        panelTop.add(estado);

        panelPrincipal.add(panelTop, BorderLayout.NORTH);

        String[] columnas = {"Cantidad", "Producto", "Comentarios"};
        DefaultTableModel modelo = new DefaultTableModel(columnas, 0);

        JTable tabla = new JTable(modelo);
        tabla.setRowHeight(25);

        JScrollPane scroll = new JScrollPane(tabla);
        scroll.setBorder(BorderFactory.createTitledBorder("Detalles comanda"));

        panelPrincipal.add(scroll, BorderLayout.CENTER);

        JPanel panelAgregar = new JPanel();
        panelAgregar.setBackground(panelPrincipal.getBackground());

        JButton btnAgregar = new JButton("Agregar producto");
        btnAgregar.setBackground(new Color(20, 120, 120));
        btnAgregar.setForeground(Color.WHITE);

        panelAgregar.add(btnAgregar);

        panelPrincipal.add(panelAgregar, BorderLayout.AFTER_LAST_LINE);

        JPanel panelBottom = new JPanel(new BorderLayout());
        panelBottom.setBackground(panelPrincipal.getBackground());

        JPanel panelTotales = new JPanel(new GridLayout(2, 2));
        panelTotales.setBackground(panelPrincipal.getBackground());

        panelTotales.add(new JLabel(""));
        panelTotales.add(new JLabel("Subtotal: $0"));

        panelTotales.add(new JLabel(""));
        panelTotales.add(new JLabel("Total: $0"));

        JPanel panelAcciones = new JPanel();
        panelAcciones.setBackground(panelPrincipal.getBackground());

        JButton btnGuardar = new JButton("Guardar comanda");
        btnGuardar.setBackground(new Color(20, 120, 120));
        btnGuardar.setForeground(Color.WHITE);

        JButton btnCancelar = new JButton("Cancelar comanda");
        btnCancelar.setBackground(new Color(220, 50, 50));
        btnCancelar.setForeground(Color.WHITE);

        JButton btnEntregar = new JButton("Marcar como entregada");
        btnEntregar.setBackground(new Color(50, 180, 50));
        btnEntregar.setForeground(Color.WHITE);

        panelAcciones.add(btnGuardar);
        panelAcciones.add(btnCancelar);
        panelAcciones.add(btnEntregar);

        panelBottom.add(panelTotales, BorderLayout.NORTH);
        panelBottom.add(panelAcciones, BorderLayout.SOUTH);

        panelPrincipal.add(panelBottom, BorderLayout.SOUTH);

        add(panelPrincipal, BorderLayout.CENTER);

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
    }
}
