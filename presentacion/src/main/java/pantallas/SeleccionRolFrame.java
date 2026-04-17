/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pantallas;

import Componentes.Sidebar;
import Componentes.panelSuperior;
import controlador.Coordinadoor;
import controlador.controlDeNavegacion;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

/**
 * Pantalla para seleccion de tipo de empleado antes de el menu principal, si es
 * administrador entra directo y si es empleado se captura el codigo del empleado para
 * poderlo enlazar con las comandas realizadas
 * @author Benjamin
 */
public class SeleccionRolFrame extends JFrame{
    
    private boolean menuVisible = false;
    
    public SeleccionRolFrame(panelSuperior pa, Sidebar slide) {
        //tamaño del menu
        //slide.setPreferredSize(new Dimension(0, 0));
        
        // Configuración básica de la ventana
        setTitle("Acceso al Sistema");
        setSize(1200, 600);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null); // Centrar en la pantalla
        setLayout(new BorderLayout());
        
        //agregar elementos
        add(pa, BorderLayout.NORTH);
        //add(slide, BorderLayout.WEST);
        
        //panel central donde van a estar los botones
        JPanel panelCentro = new JPanel(new GridBagLayout());
        //le ponemos como color gris al fondo
        panelCentro.setBackground(new Color(220, 220, 220));
        
        //se crean los botones
        JButton btnAdministrador = new JButton("Soy Administrador");
        JButton btnMesero = new JButton("Soy Mesero");
        
        btnAdministrador.setPreferredSize(new Dimension(350, 200));
        btnMesero.setPreferredSize(new Dimension(350, 200));
        
        btnAdministrador.setBackground(new Color(153, 153, 153));
        btnMesero.setBackground(new Color(153, 153, 153));
        
        GridBagConstraints gbc = new GridBagConstraints();
        //posicion horizontal
        gbc.gridx = 0;
        //posicion vertical
        gbc.gridy = 0;
        //este es el margen que tiene cada objeto que se agrege al panel 
        gbc.insets = new Insets(20, 40, 20, 40);
        
        panelCentro.add(btnAdministrador, gbc);
        
        gbc.gridx = 1;
        panelCentro.add(btnMesero, gbc);
        
        //agregamos el panel centro
        add(panelCentro, BorderLayout.CENTER);
        
        // panel inferior
        JPanel panelInferior = new JPanel();
        panelInferior.setBackground(new Color(20, 87, 87));
        panelInferior.setPreferredSize(new Dimension(0, 65));
        add(panelInferior, BorderLayout.SOUTH);
        
        /*
        //action listener para el boton del menu
        pa.getBtnMenu().addActionListener(e -> {
            if(menuVisible){
                slide.setPreferredSize(new Dimension (0, 0));
            } else {
                slide.setPreferredSize(new Dimension(200, 0));
            }
            menuVisible = !menuVisible;
            
            slide.revalidate();
            slide.repaint();
        });
        */

        //Action listener de los botones principales
        btnAdministrador.addActionListener(e -> {
            controlDeNavegacion.getcontrolNavegacion().abrirMenuPrincipal();
        });
        
        
        // 2. Acceso de Mesero (Pide código)
        btnMesero.addActionListener(e -> {
            String lectura = JOptionPane.showInputDialog(this, 
                    "Ingrese su código numérico de mesero:", 
                    "Acceso de Mesero", 
                    JOptionPane.QUESTION_MESSAGE);
            
            if (lectura != null && !lectura.trim().isEmpty()) {
                try {
                    Long codigoIngresado = Long.parseLong(lectura.trim());
                    
                    // --- CONEXIÓN REAL AL SISTEMA ---
                    dto.EmpleadoMeseroDTO mesero = Coordinadoor.getCoordinador().iniciarSesionMesero(codigoIngresado);
                    
                    // Si llega a esta línea, la sesión fue un éxito
                    JOptionPane.showMessageDialog(this, "¡Bienvenido, " + mesero.getNombreCompleto() + "!");
                    
                    // AQUÍ ABRIMOS LA PANTALLA DE TRABAJO DEL MESERO
                    // (Sustituye 'PantallaComandas' por el nombre real de tu JFrame de mesas/comandas)
                    // new PantallaComandas().setVisible(true); 
                    
                    this.dispose(); // Cerramos la ventanita de login
                    
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(this, "Por favor, ingrese solo números.", "Error", JOptionPane.ERROR_MESSAGE);
                } catch (excepciones.NegocioExcepcion ex) {
                    // Si el BO dice que el código no existe, mostramos el error
                    JOptionPane.showMessageDialog(this, ex.getMessage(), "Acceso Denegado", JOptionPane.WARNING_MESSAGE);
                }
            }
        });
        
    }
    
}
