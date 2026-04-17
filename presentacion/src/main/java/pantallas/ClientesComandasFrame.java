package pantallas;

import Componentes.barraBusqueda;
import Componentes.tablaClientes;
import controlador.ClienteSeleccionListener;
import controlador.Coordinadoor;
import dto.ClienteDTO;
import excepciones.NegocioExcepcion;
import java.awt.BorderLayout;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

/**
 * Ventana gráfica que permite buscar y seleccionar clientes para asociarlos
 * a una comanda.
 * 
 * <p>Esta clase integra una barra de búsqueda y una tabla de clientes. Permite
 * filtrar clientes dinámicamente conforme se escribe en la barra de búsqueda,
 * y seleccionar un cliente mediante doble clic en la tabla.</p>
 * 
 * <p>Cuando un cliente es seleccionado, se notifica mediante un
 * {@link ClienteSeleccionListener}, permitiendo desacoplar la lógica de la UI
 * del resto del sistema.</p>
 * 
 * @author munos
 */
public class ClientesComandasFrame extends JFrame {

    /** Componente de tabla que muestra la lista de clientes */
    private tablaClientes tabla;

    /** Componente de barra de búsqueda para filtrar clientes */
    private barraBusqueda barra;

    /** Listener que maneja el evento de selección de cliente */
    private ClienteSeleccionListener listener;

    /**
     * Constructor que inicializa la ventana con sus componentes principales
     * y configura los eventos de interacción.
     *
     * @param tabla componente de tabla de clientes
     * @param barra componente de barra de búsqueda
     */
    public ClientesComandasFrame(tablaClientes tabla, barraBusqueda barra) {

        this.tabla = tabla;
        this.barra = barra;

        setTitle("Seleccionar Cliente");
        setSize(600, 400);
        setLayout(new BorderLayout());

        add(barra, BorderLayout.NORTH);
        add(tabla, BorderLayout.CENTER);

        // Evento para búsqueda dinámica de clientes
        barra.getTxtBuscar().addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                String texto = barra.getTxtBuscar().getText();
                List<ClienteDTO> lista = Coordinadoor.getCoordinador().buscarClientes(texto);
                cargarTabla(lista);
            }
        });

        // Evento para selección de cliente con doble clic
        tabla.getTabla().addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {

                if (e.getClickCount() == 2) {

                    int fila = tabla.getTabla().getSelectedRow();
                    if (fila == -1) 
                        return;

                    ClienteDTO cliente = new ClienteDTO();
                    cliente.setId((Long) tabla.getModelo().getValueAt(fila, 0));
                    cliente.setNombre(tabla.getModelo().getValueAt(fila, 1).toString());

                    if (listener != null) {
                        listener.clienteSeleccionado(cliente);
                    }
                    dispose();
                }
            }
        });

        // Carga inicial de clientes
        try {
            cargarTabla(Coordinadoor.getCoordinador().obtenerClientes());
        } catch (NegocioExcepcion e) {
            JOptionPane.showMessageDialog(this, e.getMessage());
        }
    }

    /**
     * Establece el listener que será notificado cuando un cliente sea seleccionado.
     *
     * @param listener implementación de {@link ClienteSeleccionListener}
     */
    public void setClienteSeleccionListener(ClienteSeleccionListener listener) {
        this.listener = listener;
    }

    /**
     * Carga la lista de clientes en la tabla.
     *
     * @param lista lista de objetos {@link ClienteDTO} a mostrar
     */
    private void cargarTabla(List<ClienteDTO> lista) {
        tabla.getModelo().setRowCount(0);

        for (ClienteDTO c : lista) {
            tabla.getModelo().addRow(new Object[]{
                c.getId(),
                c.getNombre(),
                c.getApellidoPaterno(),
                c.getApellidoMaterno(),
                c.getCorreoElectronico(),
                c.getTelefono(),
                c.getNumeroVisitas(),
                c.getPuntosFidelidad()
            });
        }
    }
}
