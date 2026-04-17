/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pantallas;

import Componentes.Sidebar;
import Componentes.panelSuperior;
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import controlador.Coordinadoor;
import controlador.controlDeNavegacion;
import dto.ReporteClienteDTO;
import excepciones.NegocioExcepcion;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.io.FileOutputStream;
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
 * @author Jorge
 */
public class ReporteClientesFrame extends JFrame {

    private boolean menuVisible = false;
    
    private JTextField txtNombre;
    private JTextField txtVisitas;
    private DefaultTableModel modelo;
    private JTextField txtTotal;

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
        txtNombre = new JTextField(12);
        //label que dicie las minimo de visitas
        JLabel lblVisitas = new JLabel("Min visitas:");
        //tectfiel con espacio de 5 para el numero de visitas 
        txtVisitas = new JTextField(5);

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

        modelo = new DefaultTableModel(columnas, 0);

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
        txtTotal = new JTextField(10);
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

        // Evento del botón Regresar
        btnRegresar.addActionListener(e -> {
            controlDeNavegacion.getcontrolNavegacion().abrirMenuReportes();
        });

        // Evento del botón Filtrar
        btnFiltrar.addActionListener(e -> cargarTablaReporte());

        // Cargamos la tabla automáticamente al abrir la pantalla
        cargarTablaReporte();

        btnPdf.addActionListener(e -> {
            GenerarPDF();
        });
        
        //agrego el action listener del boton del menu 
        pa.getBtnMenu().addActionListener(e -> {
            if (menuVisible) {
                sliede.setPreferredSize(new Dimension(0, 0));
            } else {
                sliede.setPreferredSize(new Dimension(200, 0));
            }
            menuVisible = !menuVisible;

            sliede.revalidate();
            sliede.repaint();
        });

    }

    /**
     * Extrae los filtros de la interfaz, solicita los datos al coordinador,
     * llena la tabla y calcula el total acumulado de todos los clientes
     * filtrados.
     */
    private void cargarTablaReporte() {
        try {
            // Obtener y limpiar los filtros
            String nombre = txtNombre.getText().trim();
            if (nombre.isEmpty()) {
                nombre = null;
            }

            Integer minVisitas = null;
            String visitasStr = txtVisitas.getText().trim();
            if (!visitasStr.isEmpty()) {
                minVisitas = Integer.parseInt(visitasStr);
            }

            // Pedirle los datos al Coordinador
            List<ReporteClienteDTO> lista = Coordinadoor.getCoordinador().generarReporteClientes(nombre, minVisitas);

            // Limpiamos la tabla
            modelo.setRowCount(0);
            double sumaTotalGastado = 0.0;

            // Llenamos la tabla y sumamos el total
            if (lista != null) {
                for (ReporteClienteDTO dto : lista) {

                    String fechaStr = (dto.getFechaUltimaComanda() != null)
                            ? dto.getFechaUltimaComanda().toLocalDate().toString()
                            : "N/A";

                    Object[] fila = {
                        dto.getNombreCliente(),
                        dto.getNumeroVisitas(),
                        "$" + dto.getTotalGastado(),
                        fechaStr
                    };
                    modelo.addRow(fila);

                    if (dto.getTotalGastado() != null) {
                        sumaTotalGastado += dto.getTotalGastado();
                    }
                }
            }

            // Mostrar el total global en la cajita
            txtTotal.setText("$" + String.format("%.2f", sumaTotalGastado));

        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "El filtro de 'Min visitas' debe ser un número.", "Dato Inválido", JOptionPane.WARNING_MESSAGE);
        } catch (NegocioExcepcion ex) {
            JOptionPane.showMessageDialog(this, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        } catch (Exception ex) {
            ex.printStackTrace(); // Esto pintará las letras rojas en tu consola de NetBeans
            JOptionPane.showMessageDialog(this, "Error real: " + ex.toString(), "Error", JOptionPane.ERROR_MESSAGE);
            //JOptionPane.showMessageDialog(this, "Ocurrió un error inesperado al cargar la tabla.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void GenerarPDF() {
        try {
            String rutaPDF =  System.getProperty("user.home") + "/Downloads/ReporteClientes.pdf";

            Document document = new Document();

            PdfWriter.getInstance(document, new FileOutputStream(rutaPDF));
            document.open();

            Font tituloFont = new Font(Font.FontFamily.HELVETICA, 18, Font.BOLD);
            Paragraph titulo = new Paragraph("Reporte de clientes", tituloFont);
            titulo.setAlignment(Element.ALIGN_CENTER);
            document.add(titulo);

            String nombre;
            if (txtNombre.getText().isEmpty()) {
                nombre = "Todos";
            } else {
                nombre = txtNombre.getText();
            }

            String visitas;

            if (txtVisitas.getText().isEmpty()) {
                visitas = "Todas";
            } else {
                visitas = txtVisitas.getText();
            }

            document.add(new Paragraph("Nombre: " + nombre));
            document.add(new Paragraph("Min visitas: " + visitas));
            document.add(new Paragraph("Fecha: " + java.time.LocalDate.now()));
            document.add(new Paragraph(" "));

            int columnas = modelo.getColumnCount();
            PdfPTable tabla = new PdfPTable(columnas);
            tabla.setWidthPercentage(100);

            for (int i = 0; i < columnas; i++) {
                PdfPCell header = new PdfPCell(new Phrase(modelo.getColumnName(i)));
                header.setHorizontalAlignment(Element.ALIGN_CENTER);
                header.setBackgroundColor(BaseColor.LIGHT_GRAY);
                tabla.addCell(header);
            }

            for (int i = 0; i < modelo.getRowCount(); i++) {
                for (int j = 0; j < columnas; j++) {
                    Object valor = modelo.getValueAt(i, j);
                    if (valor != null) {
                        tabla.addCell(valor.toString());
                    } else {
                        tabla.addCell("");
                    }
                }
            }

            document.add(tabla);
            document.add(new Paragraph(" "));

            Font totalFont = new Font(Font.FontFamily.HELVETICA, 12, Font.BOLD);
            Paragraph total = new Paragraph("TOTAL: " + txtTotal.getText(), totalFont);
            total.setAlignment(Element.ALIGN_RIGHT);
            document.add(total);

            document.close();

            JOptionPane.showMessageDialog(this, "Pdf generado correctamente en Descargas.");

        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error al generar el PDF: " + e.getMessage());
        }

    }

}
