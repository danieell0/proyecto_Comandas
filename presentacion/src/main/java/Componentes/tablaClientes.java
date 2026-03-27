/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Componentes;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import javax.swing.BorderFactory;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;

/**
 *
 * @author Jorge
 */
public class tablaClientes extends JPanel {

    private JTable tabla;
    private DefaultTableModel modelo;
    
    public tablaClientes(){
        //creamos un layou para centrarlo 
        setLayout(new BorderLayout());
        //poner un fondo blanco 
        setBackground(Color.WHITE);
        //este es el espacio interno 
        setBorder(BorderFactory.createEmptyBorder(10,10,10,10));
        
        //fedinimos las columnas de la tabla y las filas empiwzan en 0
        modelo=new DefaultTableModel(new Object[]{"Nombre","Telefono","Correo","Puntos","Visitas"},0);
        //a la tabla le ponemos el modelo creado 
        tabla=new JTable(modelo);
        //altura de las filas 
        tabla.setRowHeight(30);
        //funte de la tabla 
        tabla.setFont(new Font("Segoe UI", Font.BOLD,13));
        //aqui obtenemos la cabecera de la tabla 
        JTableHeader header=tabla.getTableHeader();
        //color de la cabecera 
        header.setBackground(new Color(20, 87, 87));
        //color del texto 
        header.setForeground(Color.white);
        //le ponemos un scroll a la tabla 
        JScrollPane scroll=new JScrollPane(tabla);
        //la agregamos al panel
        add(scroll,BorderLayout.CENTER);
    }

    public JTable getTabla() {
        return tabla;
    }

    public DefaultTableModel getModelo() {
        return modelo;
    }
    
    
    
}
