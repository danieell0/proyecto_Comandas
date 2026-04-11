/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controlador;

import dto.ClienteDTO;
import dto.ReporteClienteDTO;
import dto.ReporteComandaDTO;
import entidades.ClienteFrecuente;
import excepciones.NegocioExcepcion;
import excepciones.PersistenciaException;
import java.time.LocalDate;
import java.util.List;
import javax.swing.JOptionPane;
import objetosNegocio.ClienteBO;
import objetosNegocio.ReportesBO;
import objetosNegocio.ReportesComandasBO;
import pantallas.ClienteFrecuenteFrame;
import pantallas.ReportesComandasFrame;

/**
 *
 * Controlador principal (Coordinador) para el módulo de Clientes Frecuentes.
 * Implementa el patrón Singleton para garantizar una única instancia global que 
 * gestione la comunicación entre las interfaces gráficas (Vistas) y la capa de Negocio (BO).
 * Su responsabilidad es recibir las peticiones del usuario, delegarlas al BO y 
 * mostrar mensajes de retroalimentación visual (JOptionPanes).
 * @author munos
 */
public class Coordinadoor {

    /**
     * Instancia estática y única de la clase (Patrón Singleton).
     */
    private static Coordinadoor cordinador;

    /**
     * Objeto de negocio que contiene toda la lógica y reglas de validación.
     */
    private ClienteBO clienteBO;
    
    /**
     * Objeto de negocio que contiene toda la logica para la generacion de reportes
     */
    private ReportesBO reportesBO;
    
    private ReportesComandasBO reportesComandas;
    
    private ReportesComandasFrame frameReportesComandas;
    
    /**
     * Referencia a la pantalla principal del módulo de clientes.
     */
    private ClienteFrecuenteFrame frameCliente;

    /**
     * Constructor privado para evitar que otras clases usen "new Coordinadoor()".
     * Fuerza el uso del método getCoordinador() para aplicar el patrón Singleton.
     */
    private Coordinadoor() {
        clienteBO = new ClienteBO();
        reportesBO = new ReportesBO();
        reportesComandas = new ReportesComandasBO();
    }

    /**
     * Punto de acceso global a la única instancia del Coordinador.
     * Si la instancia no existe, la crea; si ya existe, la devuelve.
     * * @return La instancia única de {@link Coordinadoor}.
     */
    public static Coordinadoor getCoordinador() {
        if (cordinador == null) {
            cordinador = new Coordinadoor();
        }
        return cordinador;
    }

    /**
     * Método inteligente que decide si debe registrar o actualizar un cliente.
     * Si el DTO no tiene ID, asume que es un cliente nuevo y lo registra.
     * Si el DTO ya tiene ID, asume que es una modificación y lo edita.
     * Además, gestiona los mensajes visuales (éxito o error) para el usuario.
     * * @param cliente Objeto DTO con los datos enviados desde la pantalla.
     */
    public void guardarCliente(ClienteDTO cliente) {

        try {
            if (cliente.getId() == null) {
                clienteBO.registrar(cliente);
                JOptionPane.showMessageDialog(null, "Cliente registrado ");
            } else {
                clienteBO.editar(cliente);
                JOptionPane.showMessageDialog(null, "Cliente actualizado ");
            }

        } catch (NegocioExcepcion e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "Error", JOptionPane.WARNING_MESSAGE);
        }
    }

    /**
     * Solicita a la capa de negocio la lista completa de clientes frecuentes.
     * * @return Lista de {@link ClienteDTO} para poblar las tablas visuales.
     * @throws NegocioExcepcion Si ocurre un error al obtener los datos.
     */
    public List<ClienteDTO> obtenerClientes() throws NegocioExcepcion {
        return clienteBO.obtenerClientes();
    }

    /**
     * Realiza una búsqueda filtrada de clientes manejando internamente las excepciones.
     * Si ocurre un error en la búsqueda, muestra un mensaje al usuario y retorna null.
     * * @param filtro Texto introducido en la barra de búsqueda de la pantalla.
     * @return Lista de DTOs coincidentes, o null si ocurre un error.
     */
    public List<ClienteDTO> buscarClientes(String filtro) {
        try {
            return clienteBO.consultarPorFiltro(filtro);
        } catch (NegocioExcepcion e) {
            JOptionPane.showMessageDialog(null, "Error en la búsqueda: " + e.getMessage());
            return null;
        }
    }

    /**
     * Solicita la eliminación de un cliente a la capa de negocio.
     * * @param idCliente Identificador del cliente a eliminar.
     * @throws NegocioExcepcion Si el cliente no existe o h ay un error de conexión.
     */
    public void eliminarClientes(Long idCliente) throws NegocioExcepcion {
        clienteBO.eliminar(idCliente);
    }
    
    /**
     * Solicita la generación del reporte de clientes frecuentes aplicando filtros.
     * @param nombre Filtro por nombre (puede ser vacío o null).
     * @param minVisitas Filtro de visitas mínimas (puede ser null).
     * @return Lista de clientes que cumplen los criterios para el reporte.
     * @throws NegocioExcepcion Si falla la recuperación de datos o hay errores de validación.
     */
    public List<ReporteClienteDTO> generarReporteClientes(String nombre, Integer minVisitas) throws NegocioExcepcion {
        return reportesBO.obtenerReporteClientes(nombre, minVisitas);
    }
    public List<ReporteComandaDTO> obtenerReporteComandas(LocalDate fechaInicio, LocalDate fechaFin) {
        try {
            return reportesComandas.obtenerReporteComandas(fechaInicio, fechaFin);
        } catch (NegocioExcepcion e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "Error", JOptionPane.WARNING_MESSAGE);
            return null;
        }
}
}