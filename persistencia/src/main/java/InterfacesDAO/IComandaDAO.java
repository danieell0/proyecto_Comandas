package InterfacesDAO;

import entidades.Comanda;
import excepciones.PersistenciaException;

/**
 * Interfaz que define las operaciones de acceso a datos para la entidad
 * {@link Comanda}.
 * 
 * <p>Pertenece a la capa DAO (Data Access Object) y establece los métodos
 * necesarios para realizar operaciones de persistencia, consulta y actualización
 * de comandas en la base de datos.</p>
 * 
 * <p>Incluye métodos especializados como la obtención de comandas activas
 * por mesa y el conteo de comandas del día.</p>
 * 
 * @author munos
 */
public interface IComandaDAO {

    /**
     * Guarda una nueva comanda en la base de datos.
     * 
     * @param comanda entidad {@link Comanda} a persistir
     * 
     * @throws PersistenciaException si ocurre un error durante la operación
     */
    void guardar(Comanda comanda) throws PersistenciaException;

    /**
     * Obtiene la comanda activa asociada a una mesa.
     * 
     * @param idMesa identificador de la mesa
     * @return objeto {@link Comanda} si existe, o {@code null} si no hay comanda activa
     * 
     * @throws PersistenciaException si ocurre un error en la consulta
     */
    Comanda obtenerPorMesa(Long idMesa) throws PersistenciaException;

    /**
     * Busca una comanda por su identificador.
     * 
     * @param id identificador de la comanda
     * @return objeto {@link Comanda} encontrado o {@code null} si no existe
     * 
     * @throws PersistenciaException si ocurre un error en la consulta
     */
    Comanda buscarPorId(Long id) throws PersistenciaException;

    /**
     * Actualiza una comanda existente en la base de datos.
     * 
     * @param comanda entidad {@link Comanda} con los datos actualizados
     * @return entidad {@link Comanda} actualizada
     * 
     * @throws PersistenciaException si ocurre un error durante la operación
     */
    Comanda actualizar(Comanda comanda) throws PersistenciaException;

    /**
     * Cuenta la cantidad de comandas registradas en el día actual.
     * 
     * <p>Se utiliza principalmente para generar folios consecutivos diarios.</p>
     * 
     * @return número de comandas del día actual
     * 
     * @throws PersistenciaException si ocurre un error en la consulta
     */
    int contarComandasHoy() throws PersistenciaException;
}
