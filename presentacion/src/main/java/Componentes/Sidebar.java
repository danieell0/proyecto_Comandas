/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Componentes;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 *
 * @author Jorge
 */
public class Sidebar extends JPanel {

    public Sidebar() {
        //aqui es donde le ponemos el color de fondo al menu 
        setBackground(new Color(20, 87, 87));
        // esto es lo ancho del menu
        setPreferredSize(new Dimension(200, 0));
        //con esto decimos que se acomodara todo verticalmente 
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        //este es el titulo que aparece
        JLabel titulo=new JLabel("Comandas");
        //este es el color del texto que esta como titulo 
        titulo.setForeground(Color.WHITE);
        //este es el estilo de la letra del titulo 
        titulo.setFont(new Font("Segoe UI",Font.BOLD,18));
        //aqui sentramos el titulo
        titulo.setAlignmentX(Component.CENTER_ALIGNMENT);
        // este es el espacio de arriba o el padding 
        add(Box.createVerticalStrut(30));
        //agregamos el titulo al menu 
        add(titulo); 

    }

}
