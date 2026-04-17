package pantallas;

import Componentes.Sidebar;
import Componentes.panelSuperior;
import Enums.EstadoMesa;
import dto.MesaDTO;
import java.awt.Color;
import java.awt.GridLayout;
import java.util.List;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import objetosNegocio.MesaBO;

/**
 * Ventana que muestra la lista de mesas disponibles en el sistema.
 * 
 * <p>Permite visualizar el estado de cada mesa mediante botones:
 * <ul>
 *   <li>Color verde: mesa disponible</li>
 *   <li>Color rojo: mesa ocupada</li>
 * </ul>
 * </p>
 * 
 * <p>Al seleccionar una mesa, se abre la ventana de comandas asociada
 * a dicha mesa.</p>
 * 
 * <p>La información de las mesas se obtiene a través de {@link MesaBO}.</p>
 * 
 * @author munos
 */
public class MesasFrame extends JFrame {

    /** Objeto de negocio para la gestión de mesas */
    private MesaBO mesaBO = new MesaBO();

    /**
     * Constructor que inicializa la ventana de mesas y carga su contenido.
     */
    public MesasFrame() {

        setTitle("Mesas");
        setSize(800, 600);
        setLocationRelativeTo(null);
        setLayout(new GridLayout(4, 5, 10, 10));

        cargarMesas();
    }

    /**
     * Carga las mesas desde la capa de negocio y las muestra en la interfaz
     * como botones con su estado correspondiente.
     */
    private void cargarMesas() {
        try {
            List<MesaDTO> mesas = mesaBO.obtenerMesas();

            for (MesaDTO mesa : mesas) {
                JButton btnMesa = new JButton(mesa.getNumero());

                // Asignación de color según el estado de la mesa
                if (mesa.getEstado() == EstadoMesa.Disponible) {
                    btnMesa.setBackground(Color.GREEN);
                } else {
                    btnMesa.setBackground(Color.RED);
                }

                // Evento para abrir la comanda de la mesa seleccionada
                btnMesa.addActionListener(e -> abrirComanda(mesa));
                add(btnMesa);
            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error al cargar mesas");
        }
    }

    /**
     * Abre la ventana de comandas para la mesa seleccionada.
     * 
     * <p>Inicializa los componentes necesarios y pasa la mesa seleccionada
     * al nuevo frame.</p>
     *
     * @param mesa objeto {@link MesaDTO} seleccionado
     */
    private void abrirComanda(MesaDTO mesa) {

        try {
            Sidebar sidebar = new Sidebar();
            panelSuperior panel = new panelSuperior("Mesa " + mesa.getNumero());

            ComandasFrame frame = new ComandasFrame(sidebar, panel);
            frame.setMesaSeleccionada(mesa);
            frame.setLocationRelativeTo(this);
            frame.setVisible(true);

            // Cierra la ventana actual
            this.dispose();

        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error al abrir la comanda");
        }
    }
}