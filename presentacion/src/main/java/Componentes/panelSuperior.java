/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Componentes;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

/**
 *
 * @author Jorge
 */
public class panelSuperior extends JPanel {

    private JButton btnMenu;
    private JLabel titulo;

    public panelSuperior(String tituloTexto) {
        //los elementos agregados se alinearan 
        setLayout(new BorderLayout());
        //le asignamos un color
        setBackground(new Color(20, 87, 87));
        //esta es la altura de la barra
        setPreferredSize(new Dimension(0, 60));

        //este es el boton del menu
        btnMenu = new JButton("||");
        //este es el estilo del menu
        //meybe lo cambio por una imagen 
        btnMenu.setFont(new Font("Segoe UI", Font.BOLD, 18));
        //con este cambiamos el azul al darle clic al boton 
        btnMenu.setFocusPainted(false);
        //color del boton del menu
        btnMenu.setBackground(new Color(20, 87, 87));
        //color del icono del menu 
        btnMenu.setForeground(Color.WHITE);
        //borde del menu deshabilitado 
        btnMenu.setBorderPainted(false);

        //creamos un panel para mandar el boton a la izquierda 
        JPanel panelLeft = new JPanel(new FlowLayout(FlowLayout.LEFT));
        //mismo color que la barra
        panelLeft.setBackground(new Color(20, 87, 87));
        //agregamos el boton al panel izquierdo 
        panelLeft.add(btnMenu);
        //colocamos el panelizquierda a la izquierda  
        add(panelLeft, BorderLayout.WEST);

        //creamos el titulo
        titulo = new JLabel(tituloTexto);
        //color del titulo
        titulo.setForeground(Color.WHITE);
        //formato del titulo 
        titulo.setFont(new Font("Segoe UI", Font.BOLD, 22));
        //centrado del titulo 
        titulo.setHorizontalAlignment(SwingConstants.CENTER);
        //ubicacion del titulo 
        add(titulo, BorderLayout.CENTER);

    }

    public JButton getBtnMenu() {
        return btnMenu;
    }
    
}
