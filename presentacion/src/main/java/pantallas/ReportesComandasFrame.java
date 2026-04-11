/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pantallas;

import Componentes.Sidebar;
import Componentes.panelSuperior;
import controlador.Coordinadoor;
import dto.ReporteComandaDTO;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.time.LocalDate;
import java.util.List;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import static javax.swing.WindowConstants.EXIT_ON_CLOSE;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author munos
 */
public class ReportesComandasFrame extends JFrame {

    private JTextField txtInicio;
    private JTextField txtFin;
    private JTable tabla;
    private JLabel lblTotal;

    public ReportesComandasFrame(panelSuperior pa, Sidebar sliede) {
        sliede.setPreferredSize(new Dimension(0, 0));
        setTitle("Reportes Comandas");
        setSize(1200, 600);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        add(pa, BorderLayout.NORTH);
        add(sliede, BorderLayout.WEST);

        JPanel panelCentro = new JPanel();
        panelCentro.setLayout(new BoxLayout(panelCentro, BoxLayout.Y_AXIS));
        panelCentro.setBackground(new Color(220, 220, 220));

        panelCentro.add(Box.createVerticalStrut(30));

        JPanel panelFiltros = new JPanel(new FlowLayout(FlowLayout.CENTER, 15, 10));
        panelFiltros.setOpaque(false);

        JLabel lblRango = new JLabel("Rango fecha");

        txtInicio = new JTextField(10);
        txtInicio.setText("2026-03-01");

        JLabel lblHasta = new JLabel("Hasta");

        txtFin = new JTextField(10);
        txtFin.setText("2026-03-31");

        panelFiltros.add(lblRango);
        panelFiltros.add(txtInicio);
        panelFiltros.add(lblHasta);
        panelFiltros.add(txtFin);

        panelCentro.add(panelFiltros);

        JButton btnFiltrar = new JButton("Filtrar");
        btnFiltrar.setBackground(new Color(20, 87, 87));
        btnFiltrar.setForeground(Color.WHITE);

        JPanel panelBoton = new JPanel();
        panelBoton.setOpaque(false);
        panelBoton.add(btnFiltrar);

        panelCentro.add(panelBoton);

        String[] columnas = {"Fecha", "Hora", "Mesa", "Total venta", "Estado", "Cliente"};

        DefaultTableModel modelo = new DefaultTableModel();
        modelo.setColumnIdentifiers(columnas);

        tabla = new JTable(modelo);

        JScrollPane scroll = new JScrollPane(tabla);
        scroll.setPreferredSize(new Dimension(800, 200));

        JPanel panelTabla = new JPanel();
        panelTabla.setOpaque(false);
        panelTabla.add(scroll);

        panelCentro.add(panelTabla);

        lblTotal = new JLabel("Total: $0");

        JPanel panelTotal = new JPanel();
        panelTotal.setOpaque(false);
        panelTotal.add(lblTotal);

        panelCentro.add(panelTotal);

        JButton btnPDF = new JButton("Generar PDF");
        JButton btnRegresar = new JButton("Regresar al menu");

        btnPDF.setBackground(new Color(20, 87, 87));
        btnPDF.setForeground(Color.WHITE);

        btnRegresar.setBackground(new Color(20, 87, 87));
        btnRegresar.setForeground(Color.WHITE);

        JPanel panelBotones = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 10));
        panelBotones.setOpaque(false);

        panelBotones.add(btnPDF);
        panelBotones.add(btnRegresar);

        panelCentro.add(panelBotones);
        add(panelCentro);
        JPanel panelInferior = new JPanel();
        panelInferior.setBackground(new Color(20, 87, 87));
        panelInferior.setPreferredSize(new Dimension(0, 65));

        add(panelInferior, BorderLayout.SOUTH);

        btnFiltrar.addActionListener(e -> filtrar());
    }

    private void filtrar() {
        try {
            LocalDate fechaInicio = LocalDate.parse(txtInicio.getText());
            LocalDate fechaFin = LocalDate.parse(txtFin.getText());

            List<ReporteComandaDTO> lista = Coordinadoor.getCoordinador()
                    .obtenerReporteComandas(fechaInicio, fechaFin);

            llenarTabla(lista);

        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error en las fechas");
        }
    }

    public void llenarTabla(List<ReporteComandaDTO> lista) {
        DefaultTableModel modelo = (DefaultTableModel) tabla.getModel();
        modelo.setRowCount(0);
        double total = 0;
        for (ReporteComandaDTO dto : lista) {

            Object[] fila = {
                dto.getFecha(),
                dto.getHora(),
                dto.getNumeroMesa(),
                "$" + dto.getTotalVenta(),
                dto.getEstado(),
                dto.getNombreCliente()
            };
            modelo.addRow(fila);
            total += dto.getTotalVenta();
        }
        lblTotal.setText("Total: $" + total);
    }
}