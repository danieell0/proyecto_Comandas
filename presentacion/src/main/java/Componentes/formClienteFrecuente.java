/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Componentes;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 *
 * @author Jorge
 */
public class formClienteFrecuente extends JPanel {

    private JTextField txtNombre;
    private JTextField txtTelefono;
    private JTextField txtCorreo;
    private JButton btnGuardar;

    public formClienteFrecuente() {

        //fondo del formulario 
        setBackground(Color.WHITE);
        //le asignamos 8 filas y 1 columna y una separacion de 10 px
        setLayout(new GridLayout(8, 1, 10, 10));
        //este es el espacio interno
        setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        //titulo del form
        JLabel titulo = new JLabel("Cliente Frecuente");
        //estilo del titulo 
        titulo.setFont(new Font("Segoe UI", Font.BOLD, 16));

        //textfield para escribir 
        txtNombre = new JTextField();
        txtTelefono = new JTextField();
        txtCorreo = new JTextField();

        //labels de titulo 
        JLabel nombre = new JLabel("Nombre");
        nombre.setFont(new Font("Segoe UI", Font.BOLD, 12));
        JLabel telefono = new JLabel("Telefono");
        telefono.setFont(new Font("Segoe UI", Font.BOLD, 12));
        JLabel correo = new JLabel("Correo");
        correo.setFont(new Font("Segoe UI", Font.BOLD, 12));
        
        //boton de guardar cliente
        btnGuardar=new JButton("Guardar Cliente");
        //color del bototn 
        btnGuardar.setBackground(new Color(20, 87, 87));
        //color del texto del boton 
        btnGuardar.setForeground(Color.WHITE);
        
        //agregamos todo 
        add(titulo);
        add(nombre);
        add(txtNombre);
        add(telefono);
        add(txtTelefono);
        add(correo);
        add(txtCorreo);
        add(btnGuardar);

    }

    public JTextField getTxtNombre() {
        return txtNombre;
    }

    public JTextField getTxtTelefono() {
        return txtTelefono;
    }

    public JTextField getTxtCorreo() {
        return txtCorreo;
    }

    public JButton getBtnGuardar() {
        return btnGuardar;
    }
    
    

}
