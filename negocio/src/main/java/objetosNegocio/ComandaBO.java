package objetosNegocio;

import DAO.ClienteDAO;
import DAO.ComandaDAO;
import DAO.MesaDAO;
import Enums.EstadoComandas;
import Enums.EstadoMesa;
import adaptadores.ComandaAdapter;
import adaptadores.DetalleProductoAdapter;
import dto.ComandaDTO;
import dto.DetalleProductoDTO;
import entidades.Cliente;
import entidades.Comanda;
import entidades.DetalleProducto;
import entidades.EmpleadoMesero;
import entidades.Mesa;
import excepciones.NegocioExcepcion;
import excepciones.PersistenciaException;
import interfaces.IComandaBO;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

/**
 * Implementación de la interfaz {@link IComandaBO} que contiene la lógica
 * de negocio para la gestión de comandas.
 * 
 * <p>Esta clase se encarga de validar reglas de negocio, coordinar el acceso
 * a la capa de persistencia (DAO) y transformar entidades a DTOs mediante
 * adaptadores.</p>
 * 
 * <p><b>Reglas de negocio principales:</b></p>
 * <ul>
 *   <li>Una mesa no puede tener más de una comanda activa.</li>
 *   <li>Solo se puede crear una comanda si la mesa está disponible.</li>
 *   <li>Una comanda debe contener al menos un producto.</li>
 *   <li>Al crear una comanda, la mesa cambia a estado OCUPADA.</li>
 *   <li>Al cerrar una comanda, la mesa vuelve a estado DISPONIBLE.</li>
 * </ul>
 * 
 * @author munos
 */
public class ComandaBO implements IComandaBO {

    /**
     * Acceso a datos de comandas.
     */
    private ComandaDAO comandaDAO = new ComandaDAO();

    /**
     * Acceso a datos de mesas.
     */
    private MesaDAO mesaDAO = new MesaDAO();

    /**
     * Acceso a datos de clientes.
     */
    private ClienteDAO clienteDAO = new ClienteDAO();

    /**
     * Crea una nueva comanda aplicando validaciones de negocio.
     * 
     * @param dto datos de la comanda a crear
     * @return {@link ComandaDTO} creada
     * 
     * @throws NegocioExcepcion si ocurre alguna violación de reglas de negocio
     */
    @Override
    public ComandaDTO crearComanda(ComandaDTO dto) throws NegocioExcepcion {
        try {

            // Validar existencia de mesa
            Mesa mesa = mesaDAO.buscarPorId(dto.getIdMesa());
            if (mesa == null) {
                throw new NegocioExcepcion("Mesa no encontrada");
            }

            // Validar que no haya comanda activa
            Comanda existente = comandaDAO.obtenerPorMesa(mesa.getId());
            if (existente != null) {
                throw new NegocioExcepcion("Ya existe una comanda activa en esta mesa");
            }

            // Validar estado de la mesa
            if (mesa.getEstado() != EstadoMesa.Disponible) {
                throw new NegocioExcepcion("La mesa no esta disponible");
            }

            // Validar detalles
            if (dto.getDetalles() == null || dto.getDetalles().isEmpty()) {
                throw new NegocioExcepcion("La comanda debe tener al menos un producto");
            }

            // Obtener cliente
            Cliente cliente;
            if (dto.getIdCliente() == null) {
                cliente = clienteDAO.obtenerClienteGeneral();
            } else {
                cliente = clienteDAO.buscarPorId(dto.getIdCliente());
            }

            // Crear referencia de mesero
            EmpleadoMesero mesero = new EmpleadoMesero();
            mesero.setId(dto.getIdMesero());

            // Crear comanda
            Comanda comanda = new Comanda();
            comanda.setFolio(generarFolio());
            comanda.setFechaHora(LocalDateTime.now());
            comanda.setEstado(EstadoComandas.ABIERTA);
            comanda.setMesa(mesa);
            comanda.setCliente(cliente);
            comanda.setMesero(mesero);

            // Procesar detalles
            List<DetalleProducto> detallesEntidad = new ArrayList<>();
            double total = 0;

            for (DetalleProductoDTO d : dto.getDetalles()) {

                DetalleProducto det = DetalleProductoAdapter.dtoAEntidad(d);
                det.setComanda(comanda);

                if (det.getSubtotal() == null) {
                    det.setSubtotal(det.getCantidad() * det.getPrecio());
                }

                total += det.getSubtotal();
                detallesEntidad.add(det);
            }

            comanda.setDetalles(detallesEntidad);
            comanda.setTotalVenta(total);

            // Actualizar estado de mesa
            mesa.setEstado(EstadoMesa.Ocupada);
            mesaDAO.actualizar(mesa);

            // Guardar comanda
            comandaDAO.guardar(comanda);

            return ComandaAdapter.entidadADTO(comanda);

        } catch (PersistenciaException e) {
            throw new NegocioExcepcion("Error al crear la comanda: " + e.getMessage());
        }
    }

    /**
     * Obtiene la comanda activa asociada a una mesa.
     * 
     * @param idMesa identificador de la mesa
     * @return {@link ComandaDTO} o null si no existe
     * 
     * @throws NegocioExcepcion si ocurre un error
     */
    @Override
    public ComandaDTO obtenerPorMesa(Long idMesa) throws NegocioExcepcion {
        try {
            Comanda comanda = comandaDAO.obtenerPorMesa(idMesa);

            if (comanda == null) {
                return null;
            }

            return ComandaAdapter.entidadADTO(comanda);

        } catch (PersistenciaException e) {
            throw new NegocioExcepcion("Error al obtener comanda");
        }
    }

    /**
     * Genera un folio único para la comanda basado en la fecha actual
     * y un consecutivo diario.
     * 
     * <p>Formato: OB-YYYYMMDD-XXX</p>
     * 
     * @return folio generado
     * @throws PersistenciaException si falla el conteo de comandas
     */
    private String generarFolio() throws PersistenciaException {

        String fecha = LocalDate.now()
                .format(DateTimeFormatter.ofPattern("yyyyMMdd"));

        int consecutivo = comandaDAO.contarComandasHoy();

        return String.format("OB-%s-%03d", fecha, consecutivo);
    }

    /**
     * Cierra una comanda cambiando su estado y liberando la mesa.
     * 
     * @param idComanda identificador de la comanda
     * @param estado nuevo estado (ENTREGADA o CANCELADA)
     * 
     * @throws NegocioExcepcion si ocurre un error o el estado no es válido
     */
    @Override
    public void cerrarComanda(Long idComanda, EstadoComandas estado) throws NegocioExcepcion {
        try {

            if (estado != EstadoComandas.ENTREGADA && estado != EstadoComandas.CANCELADA) {
                throw new NegocioExcepcion("Estado no valido para cerrar comanda");
            }

            Comanda comanda = comandaDAO.buscarPorId(idComanda);
            if (comanda == null) {
                throw new NegocioExcepcion("Comanda no encontrada");
            }

            if (comanda.getEstado() != EstadoComandas.ABIERTA) {
                throw new NegocioExcepcion("La comanda ya fue cerrada");
            }

            comanda.setEstado(estado);

            // Liberar mesa
            Mesa mesa = comanda.getMesa();
            mesa.setEstado(EstadoMesa.Disponible);

            comandaDAO.actualizar(comanda);
            mesaDAO.actualizar(mesa);

        } catch (PersistenciaException e) {
            throw new NegocioExcepcion("Error al cerrar la comanda");
        }
    }
}