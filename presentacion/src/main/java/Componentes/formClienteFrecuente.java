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
 *Componente visual (Vista) que representa el formulario de captura para un Cliente Frecuente.
 * Hereda de JPanel para poder ser incrustado en cualquier ventana (JFrame) de la aplicación.
 * * Este componente es completamente pasivo: se encarga exclusivamente del diseño (UI) 
 * y no contiene lógica de negocio ni eventos. Expone sus campos de texto y botones 
 * a través de métodos 'getter' para que un Controlador o Frame padre gestione las acciones.
 * @author Jorge
 */
public class formClienteFrecuente extends JPanel {

    private JTextField txtNombres;
    private JTextField txtApellidoPaterno;
    private JTextField txtApellidoMatero;
    private JTextField txtTelefono;
    private JTextField txtCorreo;
    private JButton btnGuardar;
    private JButton btnEliminar;

    public formClienteFrecuente() {

        //fondo del formulario 
        setBackground(Color.WHITE);
        //le asignamos 8 filas y 1 columna y una separacion de 10 px
        setLayout(new GridLayout(12, 1, 5, 5));
        //este es el espacio interno
        setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        //titulo del form
        JLabel titulo = new JLabel("Cliente Frecuente");
        //estilo del titulo 
        titulo.setFont(new Font("Segoe UI", Font.BOLD, 16));

        //textfield para escribir 
        txtNombres = new JTextField();
        txtApellidoPaterno=new JTextField();
        txtApellidoMatero=new JTextField();
        txtTelefono = new JTextField();
        txtCorreo = new JTextField();

        //labels de titulo 
        JLabel nombres = new JLabel("Nombre");
        nombres.setFont(new Font("Segoe UI", Font.BOLD, 12));
        JLabel apellidoPaterno=new JLabel("Apellido Pateno");
        apellidoPaterno.setFont(new Font("Segoe UI", Font.BOLD, 12));
        JLabel apellidoMaterno=new JLabel("Apellido Materno");
        apellidoMaterno.setFont(new Font("Segoe UI", Font.BOLD, 12));
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
        
        btnEliminar=new JButton("Eliminar");
        btnEliminar.setBackground(Color.RED);
        btnEliminar.setForeground(Color.WHITE);
        
        //agregamos todo 
        add(titulo);
        add(nombres);
        add(txtNombres);
        add(apellidoPaterno);
        add(txtApellidoPaterno);
        add(apellidoMaterno);
        add(txtApellidoMatero);
        add(telefono);
        add(txtTelefono);
        add(correo);
        add(txtCorreo);
        add(btnGuardar);

    }

    public JTextField getTxtNombres() {
        return txtNombres;
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

    public JTextField getTxtApellidoPaterno() {
        return txtApellidoPaterno;
    }

    public JTextField getTxtApellidoMatero() {
        return txtApellidoMatero;
    }

    public JButton getBtnEliminar() {
        return btnEliminar;
    }
    
}
