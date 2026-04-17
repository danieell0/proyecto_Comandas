package InterfacesDAO;

import entidades.Mesa;
import excepciones.PersistenciaException;
import java.util.List;

/**
 * Interfaz que define las operaciones de acceso a datos para la entidad
 * {@link Mesa}.
 * 
 * <p>Pertenece a la capa DAO (Data Access Object) y establece los métodos
 * necesarios para consultar y actualizar la información de las mesas
 * en la base de datos.</p>
 * 
 * <p>Incluye operaciones para obtener todas las mesas, filtrarlas por estado
 * y buscar mesas específicas por su identificador.</p>
 * 
 * @author munos
 */
public interface IMesaDAO {

    /**
     * Actualiza una mesa existente en la base de datos.
     * 
     * @param mesa entidad {@link Mesa} con los datos actualizados
     * @return entidad {@link Mesa} actualizada
     * 
     * @throws PersistenciaException si ocurre un error durante la operación
     */
    Mesa actualizar(Mesa mesa) throws PersistenciaException;

    /**
     * Busca una mesa por su identificador.
     * 
     * @param id identificador de la mesa
     * @return objeto {@link Mesa} si existe, o {@code null} si no se encuentra
     * 
     * @throws PersistenciaException si ocurre un error en la consulta
     */
    Mesa buscarPorId(Long id) throws PersistenciaException;
    
    /**
     * Obtiene todas las mesas registradas en el sistema.
     * 
     * @return lista de objetos {@link Mesa}
     * 
     * @throws PersistenciaException si ocurre un error en la consulta
     */
    List<Mesa> obtenerTodas() throws PersistenciaException;

    /**
     * Obtiene las mesas que se encuentran en estado disponible.
     * 
     * @return lista de mesas disponibles
     * 
     * @throws PersistenciaException si ocurre un error en la consulta
     */
    List<Mesa> obtenerDisponibles() throws PersistenciaException;
}