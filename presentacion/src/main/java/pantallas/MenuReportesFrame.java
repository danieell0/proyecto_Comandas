/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pantallas;

import controlador.controlDeNavegacion;
import Componentes.Sidebar;
import Componentes.panelSuperior;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Insets;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import static javax.swing.WindowConstants.EXIT_ON_CLOSE;

/**
 *
 * @author Jorge
 */
public class MenuReportesFrame extends JFrame {

    private boolean menuVisible = false;

    public MenuReportesFrame(panelSuperior pa, Sidebar sliede) {
        //tamaño del menu 
        sliede.setPreferredSize(new Dimension(0, 0));

        //titulo del panell
        setTitle("Reportes");
        //tamaño
        setSize(1200, 600);
        //cerramos el frame 
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        //le agregamos el border layout
        setLayout(new BorderLayout());

        //agregamos elementos 
        add(pa, BorderLayout.NORTH);
        add(sliede, BorderLayout.WEST);

        //creamos un panel centro donde estaran los bototnes 
        JPanel panelCentro = new JPanel(new GridBagLayout());
        //le ponemos como un color gris al fondo
        panelCentro.setBackground(new Color(220, 220, 220));

        //creamos los botones 
        JButton btnClientes = new JButton("Reporte Clientes");
        JButton btnComandas = new JButton("Reportes comandas");

        btnClientes.setPreferredSize(new Dimension(350, 200));
        btnComandas.setPreferredSize(new Dimension(350, 200));

        btnClientes.setBackground(new Color(153, 153, 153));
        btnComandas.setBackground(new Color(153, 153, 153));

        GridBagConstraints gbc = new GridBagConstraints();
        //posicion horizontal
        gbc.gridx = 0;
        //posicion vertical 
        gbc.gridy = 0;
        //este es el margen que tiene cada objeto que se agrege al panel 
        gbc.insets = new Insets(20, 40, 20, 40);

        panelCentro.add(btnClientes, gbc);

        gbc.gridx = 1;
        panelCentro.add(btnComandas, gbc);

        //agregamos el panel centro 
        add(panelCentro, BorderLayout.CENTER);

        //agrego un panel inferior 
        JPanel panelInferior = new JPanel();
        panelInferior.setBackground(new Color(20, 87, 87));
        panelInferior.setPreferredSize(new Dimension(0, 65));
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
        
        //Action listeners de los dos botones principales
        btnClientes.addActionListener(e -> {
            controlDeNavegacion.getcontrolNavegacion().abrirReportesClientes();
        });
        
        btnComandas.addActionListener(e -> {
            controlDeNavegacion.getcontrolNavegacion().abrirReportesComandas();
        });
        
    }
    
}