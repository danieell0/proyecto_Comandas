/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pantallas;

import Componentes.Sidebar;
import Componentes.panelSuperior;
import java.awt.BorderLayout;
import java.awt.Dimension;
import javax.swing.JFrame;
import static javax.swing.WindowConstants.EXIT_ON_CLOSE;

/**
 *
 * @author Jorge
 */
public class ReporteClientesFrame extends JFrame {

    public ReporteClientesFrame(panelSuperior pa, Sidebar sliede) {
        //tamaño del menu 
        sliede.setPreferredSize(new Dimension(0, 0));
        //titulo del panell
        setTitle("Reportes Clientes");
        //tamaño
        setSize(1200, 600);
        //cerramos el frame 
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        //le agregamos el border layout
        setLayout(new BorderLayout());
        //agregamos elementos 
        add(pa, BorderLayout.NORTH);
        add(sliede, BorderLayout.WEST);
        
        
        
    }

}
