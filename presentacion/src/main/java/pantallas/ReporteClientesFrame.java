/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pantallas;

import Componentes.Sidebar;
import Componentes.panelSuperior;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import static javax.swing.WindowConstants.EXIT_ON_CLOSE;
import javax.swing.table.DefaultTableModel;

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

        //creamos el panel centro 
        JPanel panelCentro = new JPanel();
        //le asignamos un boxlayout al panel centro 
        panelCentro.setLayout(new BoxLayout(panelCentro, BoxLayout.Y_AXIS));
        //le asigno un color 
        panelCentro.setBackground(new Color(220, 220, 220));
        //agregamos un espacio 
        panelCentro.add(Box.createVerticalStrut(30));

        //creamos el panel que contienen los filtros 
        JPanel panelFiltros = new JPanel(new FlowLayout(FlowLayout.CENTER, 15, 10));
        //hacemos que se vea el color del fondo 
        panelFiltros.setOpaque(false);
        //label que dice nombre 
        JLabel lblNombre = new JLabel("Nombre:");
        //creamos un textFiel de tamaño 12
        JTextField txtNombre = new JTextField(12);
        //label que dicie las minimo de visitas
        JLabel lblVisitas = new JLabel("Min visitas:");
        //tectfiel con espacio de 5 para el numero de visitas 
        JTextField txtVisitas = new JTextField(5);
        //agrego los elementos 
        panelFiltros.add(lblNombre);
        panelFiltros.add(txtNombre);
        panelFiltros.add(lblVisitas);
        panelFiltros.add(txtVisitas);
        panelCentro.add(panelFiltros);

        //creo un boton de filtrar 
        JButton btnFiltrar = new JButton("Filtrar");
        //le asigno un control
        btnFiltrar.setBackground(new Color(20, 87, 87));
        //color de fondo para las letras del boton
        btnFiltrar.setForeground(Color.WHITE);

        //creo un panel donde guardare el boton 
        JPanel panelBoton = new JPanel();
        //hacemos que se vea el fondo del panel centro 
        panelBoton.setOpaque(false);
        //agregamos elementos 
        panelBoton.add(btnFiltrar);
        panelCentro.add(panelBoton);

        //creamos un label que mostrara la fecha del sistema
        JLabel lblInfo = new JLabel("Generado: " + java.time.LocalDate.now());
        //creamos un panel que contendra el label info 
        JPanel panelInfo = new JPanel();
        //ponemos que se vea el fondo del panel centro 
        panelInfo.setOpaque(false);
        //agregamos elementos 
        panelInfo.add(lblInfo);
        panelCentro.add(panelInfo);

        //creamos un String con los encabesados de la tabla
        String[] columnas = {"Cliente", "Visitas", "Total Gastado", "Ultima comanda"};
        //le asignamos el modelo
        DefaultTableModel modelo = new DefaultTableModel(columnas, 0);
        //creamos una tabla con el modelo 
        JTable tabla = new JTable(modelo);
        //llenamos el alto del scroll
        tabla.setFillsViewportHeight(true);

        //creamos un scroll
        JScrollPane scroll = new JScrollPane(tabla);
        //le damos dimenciones al scroll 
        scroll.setPreferredSize(new Dimension(600, 150));
        //creamos un panel donde se almacenara la tabla 
        JPanel panelTabla = new JPanel();
        //hacemos que se vea el fondo del panel centro 
        panelTabla.setOpaque(false);
        //agrego el scroll que tiene la tabla 
        panelTabla.add(scroll);
        //agrego al panel centro el panel de la tabla
        panelCentro.add(panelTabla);
        //cremos un label que dice total 
        JLabel total = new JLabel("Total: ");
        //cremos un textfiel donde aparecera el total
        JTextField txtTotal = new JTextField(10);
        //cremos el paneldonde se almacenara los elementos del total 
        JPanel panelTotal = new JPanel();
        //hacemos que se vea el fondo del panel centro 
        panelTotal.setOpaque(false);
        //agregamos elementos 
        panelTotal.add(total);
        panelTotal.add(txtTotal);
        panelCentro.add(panelTotal);
        
        //creamos el boton de el pdf y el de regresar
        JButton btnPdf = new JButton("Generar PDF");
        JButton btnRegresar = new JButton("Regresar");
        //agregamos el color de fondo del boton y de la letra
        btnPdf.setBackground(new Color(20, 87, 87));
        btnPdf.setForeground(Color.WHITE);
        btnRegresar.setBackground(new Color(20, 87, 87));
        btnRegresar.setForeground(Color.WHITE);
        
        //creamos un panel que contendra los botones
        JPanel panelBotones = new JPanel(new FlowLayout(FlowLayout.CENTER));
        //hacemos que se vea el fondo del panel centro 
        panelBotones.setOpaque(false);
        //agregamos elementos 
        panelBotones.add(btnPdf);
        panelBotones.add(btnRegresar);
        panelCentro.add(panelBotones);
        add(panelCentro);

        //agrego un panel inferior 
        JPanel panelInferior = new JPanel();
        panelInferior.setBackground(new Color(20, 87, 87));
        panelInferior.setPreferredSize(new Dimension(0, 65));
        add(panelInferior, BorderLayout.SOUTH);
    }

}
