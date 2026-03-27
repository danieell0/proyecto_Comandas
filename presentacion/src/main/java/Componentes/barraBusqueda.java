/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Componentes;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 *
 * @author Jorge
 */
public class barraBusqueda extends JPanel {
    
    private JTextField txtBuscar;
    
    public barraBusqueda() {
        //agregamos un layout 
        setLayout(new BorderLayout());
        //ponemos el color de fondo
        setBackground(Color.WHITE);
        //creamos un textfield 
        txtBuscar = new JTextField("Search...");
        //le damos formato
        txtBuscar.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        //agrego un tamaño 
        txtBuscar.setPreferredSize(new Dimension(200, 30));
        //lo agrego 
        add(txtBuscar,BorderLayout.CENTER);
    }

    public JTextField getTxtBuscar() {
        return txtBuscar;
    }
    
}
