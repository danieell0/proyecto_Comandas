package pantallas;

import Componentes.Sidebar;
import java.awt.BorderLayout;
import Componentes.panelSuperior;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import controlador.controlDeNavegacion;

/**
 * Ventana principal del sistema de comandas.
 * 
 * <p>Esta clase representa el menú principal de la aplicación, desde donde
 * se puede acceder a los distintos módulos del sistema como comandas,
 * productos, ingredientes, clientes frecuentes y reportes.</p>
 * 
 * <p>Incluye un panel lateral (sidebar) que puede mostrarse u ocultarse,
 * así como un panel central con botones de acceso rápido a cada módulo.</p>
 * 
 * <p>La navegación entre pantallas se gestiona mediante la clase
 * {@link controlDeNavegacion}.</p>
 * 
 * @author munos
 */
public class MenuPrincipalFrame extends JFrame {

    /** Indica si el menú lateral está visible */
    private boolean menuVisible = false;

    /**
     * Constructor que inicializa la ventana principal del sistema.
     *
     * @param sidebar componente lateral de navegación
     * @param pa panel superior de la aplicación
     */
    public MenuPrincipalFrame(Sidebar sidebar, panelSuperior pa) {
        setTitle("Sistema de Comandas");
        setSize(1200, 650);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        sidebar.setPreferredSize(new Dimension(0, 0));

        add(pa, BorderLayout.NORTH);
        add(sidebar, BorderLayout.WEST);

        JPanel panelCentral = new JPanel();
        panelCentral.setLayout(new GridLayout(2, 3, 30, 30));
        panelCentral.setBorder(BorderFactory.createEmptyBorder(30, 30, 30, 30));
        panelCentral.setBackground(new Color(220, 225, 230));

        JButton btnComandas = crearBoton("Comandas", new Color(63, 63, 160));
        JButton btnProductos = crearBoton("Productos", new Color(46, 139, 87));
        JButton btnIngredientes = crearBoton("Ingredientes", new Color(184, 115, 51));
        JButton btnClientes = crearBoton("Clientes Frecuentes", new Color(150, 70, 150));
        JButton btnReportes = crearBoton("Reportes", new Color(100, 100, 100));

        panelCentral.add(btnComandas);
        panelCentral.add(btnProductos);
        panelCentral.add(btnIngredientes);
        panelCentral.add(btnClientes);
        panelCentral.add(btnReportes);

        add(panelCentral, BorderLayout.CENTER);

        JPanel panelInferior = new JPanel();
        panelInferior.setBackground(new Color(20, 87, 87));
        panelInferior.setPreferredSize(new Dimension(0, 30));
        add(panelInferior, BorderLayout.SOUTH);

        // Evento para mostrar u ocultar el sidebar
        pa.getBtnMenu().addActionListener(e -> {
            if (menuVisible) {
                sidebar.setPreferredSize(new Dimension(0, 0));
            } else {
                sidebar.setPreferredSize(new Dimension(200, 0));
            }
            menuVisible = !menuVisible;

            sidebar.revalidate();
            sidebar.repaint();
        });

        // Eventos de navegación
        btnComandas.addActionListener(e -> abrir("Comandas"));
        btnProductos.addActionListener(e -> abrir("Productos"));
        btnIngredientes.addActionListener(e -> abrir("Ingredientes"));
        btnClientes.addActionListener(e -> abrir("Clientes Frecuentes"));
        btnReportes.addActionListener(e -> abrir("Reportes"));
    }

    /**
     * Crea un botón estilizado para el menú principal.
     *
     * @param texto texto que se mostrará en el botón
     * @param color color de fondo del botón
     * @return botón configurado
     */
    private JButton crearBoton(String texto, Color color) {
        JButton btn = new JButton(texto);

        btn.setFocusPainted(false);
        btn.setFont(new Font("Segoe UI", Font.BOLD, 18));
        btn.setForeground(Color.WHITE);
        btn.setBackground(color);
        btn.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        // Efecto visual al pasar el mouse
        btn.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btn.setBackground(color.darker());
            }

            public void mouseExited(java.awt.event.MouseEvent evt) {
                btn.setBackground(color);
            }
        });

        return btn;
    }

    /**
     * Abre el módulo correspondiente según el nombre recibido.
     * 
     * <p>Utiliza el controlador de navegación para dirigir al usuario
     * a la ventana adecuada.</p>
     *
     * @param modulo nombre del módulo a abrir
     */
    private void abrir(String modulo) {

        controlDeNavegacion nav = controlDeNavegacion.getcontrolNavegacion();

        switch (modulo) {

            case "Clientes Frecuentes":
                nav.abrirFrameBase();
                break;

            case "Comandas":
                nav.abrirMesasFrame();
                break;

            case "Productos":
                nav.abrirProductos();
                break;

            case "Ingredientes":
                nav.abrirFrameBase();
                break;

            case "Reportes":
                nav.abrirMenuReportes();
                break;

            default:
                nav.abrirMenuPrincipal();
        }
    }
}
