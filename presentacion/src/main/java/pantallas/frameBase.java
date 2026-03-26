/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pantallas;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 *
 * @author Jorge
 */
public class frameBase extends JFrame {

    public frameBase() {
        setTitle("Frame base");
        setSize(800, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());
        contenido();
    }

    public void contenido() {
        
        //chavales esto es del panel superior eh
        JPanel panelSuperior = new JPanel();
        //aqui le agregamos el color azul
        panelSuperior.setBackground(new Color(0, 102, 102));
        //le asignamos un tamaño al panel 
        panelSuperior.setPreferredSize(new Dimension(800, 60));
        //aqui le decimos que todo lo que entre a este panel estara centrado
        panelSuperior.setLayout(new FlowLayout(FlowLayout.CENTER));
        
        //aqui esta el titulo que aparece arriba, este es el que van a modificar
        JLabel titulo= new JLabel("Titulo");
        //aqui le ponemos color al fondo de las letras 
        titulo.setForeground(Color.WHITE);
        //formato de letra es arial, negritas, tamaño 20
        titulo.setFont(new Font("Arial", Font.BOLD, 20));
        //agregamos el titulo al panel superior 
        panelSuperior.add(titulo);        
        
        //panel central el de color gris maso 
        JPanel panelCentral=new JPanel();
        //le agreagamos border layout 
        panelCentral.setLayout(new BorderLayout());
        //ponemos el color giris del fondo
        panelCentral.setBackground(new Color(192,198,204));
       
        //panel inferior el que esta hasta abajo
        JPanel panelInferior = new JPanel();
        //le agregamos el color azul 
        panelInferior.setBackground(new Color(0, 102, 102));
        //la asignamos un tamaño
        panelInferior.setPreferredSize(new Dimension(800, 50));

        //aqui agregamos los 3 paneles
        add(panelSuperior, BorderLayout.NORTH);
        add(panelCentral, BorderLayout.CENTER);
        add(panelInferior, BorderLayout.SOUTH);
    }

}
