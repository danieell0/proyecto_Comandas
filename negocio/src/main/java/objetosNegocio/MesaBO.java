package objetosNegocio;

import DAO.MesaDAO;
import Enums.EstadoMesa;
import adaptadores.MesaAdapter;
import dto.MesaDTO;
import entidades.Mesa;
import excepciones.NegocioExcepcion;
import excepciones.PersistenciaException;
import interfaces.IMesaBO;
import java.util.List;

/**
 * Implementación de la interfaz {@link IMesaBO} que contiene la lógica
 * de negocio para la gestión de mesas.
 * 
 * <p>Esta clase se encarga de coordinar la interacción entre la capa de
 * persistencia (DAO) y las capas superiores, aplicando validaciones y
 * transformando entidades a DTOs mediante el uso de adaptadores.</p>
 * 
 * <p><b>Responsabilidades principales:</b></p>
 * <ul>
 *   <li>Consultar todas las mesas del sistema.</li>
 *   <li>Filtrar mesas disponibles.</li>
 *   <li>Actualizar el estado de una mesa.</li>
 * </ul>
 * 
 * <p><b>Reglas de negocio relevantes:</b></p>
 * <ul>
 *   <li>El identificador de la mesa no puede ser nulo.</li>
 *   <li>La mesa debe existir para poder modificar su estado.</li>
 *   <li>El cambio de estado debe mantenerse consistente con el flujo del sistema.</li>
 * </ul>
 * 
 * @author munos
 */
public class MesaBO implements IMesaBO {

    /**
     * Acceso a datos de mesas.
     */
    private MesaDAO mesaDAO = new MesaDAO();

    /**
     * Obtiene todas las mesas registradas en el sistema.
     * 
     * @return lista de {@link MesaDTO}
     * 
     * @throws NegocioExcepcion si ocurre un error al recuperar los datos
     */
    @Override
    public List<MesaDTO> obtenerMesas() throws NegocioExcepcion {
        try {
            List<Mesa> mesas = mesaDAO.obtenerTodas();
            return MesaAdapter.listaEntidadDTO(mesas);

        } catch (PersistenciaException e) {
            throw new NegocioExcepcion("Error al obtener las mesas");
        }
    }

    /**
     * Obtiene las mesas que se encuentran en estado disponible.
     * 
     * @return lista de mesas disponibles como {@link MesaDTO}
     * 
     * @throws NegocioExcepcion si ocurre un error en la consulta
     */
    @Override
    public List<MesaDTO> obtenerDisponibles() throws NegocioExcepcion {
        try {
            List<Mesa> mesas = mesaDAO.obtenerDisponibles();
            return MesaAdapter.listaEntidadDTO(mesas);

        } catch (PersistenciaException e) {
            throw new NegocioExcepcion("Error al obtener mesas disponibles");
        }
    }

    /**
     * Cambia el estado de una mesa.
     * 
     * <p>Este método valida la existencia de la mesa y actualiza su estado
     * en la base de datos.</p>
     * 
     * @param idMesa identificador de la mesa
     * @param estado nuevo estado de la mesa
     * 
     * @throws NegocioExcepcion si el id es nulo, la mesa no existe
     * o ocurre un error en la persistencia
     */
    @Override
    public void cambiarEstado(Long idMesa, EstadoMesa estado) throws NegocioExcepcion {

        try {
            // Validar ID
            if (idMesa == null) {
                throw new NegocioExcepcion("El id de la mesa no puede ser nulo");
            }

            // Buscar mesa
            Mesa mesa = mesaDAO.buscarPorId(idMesa);

            if (mesa == null) {
                throw new NegocioExcepcion("Mesa no encontrada");
            }

            // Actualizar estado
            mesa.setEstado(estado);

            mesaDAO.actualizar(mesa);

        } catch (PersistenciaException e) {
            throw new NegocioExcepcion("Error al cambiar el estado de la mesa");
        }
    }    
}