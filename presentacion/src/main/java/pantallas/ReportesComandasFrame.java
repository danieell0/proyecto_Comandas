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
import javax.swing.JSeparator;
import javax.swing.JTable;
import javax.swing.JTextField;
import static javax.swing.WindowConstants.EXIT_ON_CLOSE;

/**
 *
 * @author Jorge
 */
public class ReportesComandasFrame extends JFrame {

    public ReportesComandasFrame(panelSuperior pa, Sidebar sliede) {
        //tamaño del menu 
        sliede.setPreferredSize(new Dimension(0, 0));
        //titulo del panell
        setTitle("Reportes Comandas");
        //tamaño
        setSize(1200, 600);
        //cerramos el frame 
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        //le agregamos el border layout
        setLayout(new BorderLayout());
        //agregamos elementos 
        add(pa, BorderLayout.NORTH);
        add(sliede, BorderLayout.WEST);

        //creamos un panel centro que contendra todo
        JPanel panelCentro = new JPanel();
        //le ponemos un boxlayout 
        panelCentro.setLayout(new BoxLayout(panelCentro, BoxLayout.Y_AXIS));
        //le asignamos el color gris
        panelCentro.setBackground(new Color(220, 220, 220));
        //creamos un espacio
        panelCentro.add(Box.createVerticalStrut(30));
        //creamos el panel que contiene los filtros 
        JPanel panelFiltros = new JPanel(new FlowLayout(FlowLayout.CENTER, 15, 10));
        //ponemos el set opaque para que se vea el fondo en vez del panel filtro
        panelFiltros.setOpaque(false);
        //creamos el label rango
        JLabel lblRango = new JLabel("Rango fecha");
        //el text field que tendra la fecha inicio
        JTextField txtInicio = new JTextField("Fecha inicio", 10);
        //el label que tiene hasta
        JLabel lblHasta = new JLabel("Hasta");
        //textField de la fecha fin 
        JTextField txtFin = new JTextField("Fecha fin", 10);

        //agregamos componentes 
        panelFiltros.add(lblRango);
        panelFiltros.add(txtInicio);
        panelFiltros.add(lblHasta);
        panelFiltros.add(txtFin);
        panelCentro.add(panelFiltros);

        //creamos el boton de filtrar 
        JButton btnFiltrar = new JButton("Filtrar");
        //le asigmamos un color 
        btnFiltrar.setBackground(new Color(20, 87, 87));
        //le ponemos color a la letra 
        btnFiltrar.setForeground(Color.WHITE);
        //creamos un panel para el boton
        JPanel panelBoton = new JPanel();
        //igual,hacemos que se vea el fondo del panel centro 
        panelBoton.setOpaque(false);
        //agregamos componentes 
        panelBoton.add(btnFiltrar);
        panelCentro.add(panelBoton);

        //ponemos los encabezados de la tabla
        String[] columnas = {"Fecha", "Hora", "Mesa", "Total venta", "Estado", "Cliente"};
        //guardamos los registros 
        Object[][] datos = {
            {"21/03/2026", "15:30", "4", "$700", "Activo", "Gerundio"},
            {"20/03/2026", "18:00", "3", "$500", "Entregado", "Arturo"},
            {"18/03/2026", "13:45", "1", "$300", "Entregado", "Sebas"}
        };

        //creamos la tabla 
        JTable tabla = new JTable(datos, columnas);
        //creamos un scroll
        JScrollPane scroll = new JScrollPane(tabla);
        //le asignamos un tamaño al scroll 
        scroll.setPreferredSize(new Dimension(600, 100));
        //creamos un panel para la tabla 
        JPanel panelTabla = new JPanel();
        //fondo del panel centro 
        panelTabla.setOpaque(false);
        //agregamos el el scroll a la tabla 
        panelTabla.add(scroll);
        //agregamos la tabla
        panelCentro.add(panelTabla);
        //label del total
        JLabel lblTotal = new JLabel("Total: $1500");
        //panel que tendra el label del total 
        JPanel panelTotal = new JPanel();
        panelTotal.setOpaque(false);
        panelTotal.add(lblTotal);

        panelCentro.add(panelTotal);
        
        //creamos los botones 
        JButton btnPDF = new JButton("Generar PDF");
        JButton btnRegresar = new JButton("Regresar el menu");
        //le asignamos color a los botones y a la fuente 
        btnPDF.setBackground(new Color(20, 87, 87));
        btnPDF.setForeground(Color.WHITE);
        btnRegresar.setBackground(new Color(20, 87, 87));
        btnRegresar.setForeground(Color.WHITE);
        //creamos un panel para los botones 
        JPanel panelBotones = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 10));
        panelBotones.setOpaque(false);
        //agregamos los botones
        panelBotones.add(btnPDF);
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
