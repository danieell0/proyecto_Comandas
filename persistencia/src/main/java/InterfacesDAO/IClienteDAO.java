package InterfacesDAO;

import entidades.Cliente;
import entidades.ClienteFrecuente;
import excepciones.PersistenciaException;
import java.util.List;

/**
 * Interfaz que define las operaciones de acceso a datos para la entidad
 * {@link Cliente}.
 * 
 * <p>Pertenece a la capa DAO (Data Access Object) y establece los métodos
 * necesarios para realizar operaciones CRUD y consultas específicas sobre
 * clientes.</p>
 * 
 * <p>Incluye soporte para clientes generales y clientes frecuentes,
 * permitiendo gestionar ambos tipos dentro del sistema.</p>
 * 
 * @author Jorge
 */
public interface IClienteDAO {
    
    /**
     * Guarda un nuevo cliente en la base de datos.
     * 
     * @param cliente objeto {@link Cliente} a persistir
     * @return cliente guardado
     * 
     * @throws PersistenciaException si ocurre un error en la operación
     */
    public Cliente guardarCliente(Cliente cliente) throws PersistenciaException;
    
    /**
     * Elimina un cliente por su identificador.
     * 
     * @param idCliente identificador del cliente
     * @return {@code true} si se eliminó correctamente, {@code false} en caso contrario
     * 
     * @throws PersistenciaException si ocurre un error en la operación
     */
    public boolean eliminarCliente(Long idCliente) throws PersistenciaException;
    
    /**
     * Obtiene la lista de clientes frecuentes registrados.
     * 
     * @return lista de {@link ClienteFrecuente}
     * 
     * @throws PersistenciaException si ocurre un error en la consulta
     */
    public List<ClienteFrecuente> obtenerClientes() throws PersistenciaException;
    
    /**
     * Edita la información de un cliente frecuente.
     * 
     * @param Cliente objeto {@link ClienteFrecuente} con los datos actualizados
     * @return cliente frecuente actualizado
     * 
     * @throws PersistenciaException si ocurre un error en la operación
     */
    public ClienteFrecuente editar(ClienteFrecuente Cliente) throws PersistenciaException;
    
    /**
     * Consulta clientes frecuentes utilizando un filtro de búsqueda.
     * 
     * <p>El filtro puede aplicarse sobre atributos como nombre, correo u otros
     * campos definidos en la implementación.</p>
     * 
     * @param filtro criterio de búsqueda
     * @return lista de clientes que coinciden con el filtro
     * 
     * @throws PersistenciaException si ocurre un error en la consulta
     */
    public List<ClienteFrecuente> consultarPorFiltro(String filtro) throws PersistenciaException;
    
    /**
     * Obtiene el cliente general del sistema.
     * 
     * <p>Este cliente se utiliza cuando no se especifica uno en operaciones
     * como la creación de comandas.</p>
     * 
     * @return objeto {@link Cliente} correspondiente al cliente general
     * 
     * @throws PersistenciaException si ocurre un error en la consulta
     */
    public Cliente obtenerClienteGeneral() throws PersistenciaException;
    
    /**
     * Busca un cliente por su identificador.
     * 
     * @param id identificador del cliente
     * @return objeto {@link Cliente} si existe, o {@code null} si no se encuentra
     * 
     * @throws PersistenciaException si ocurre un error en la consulta
     */
    public Cliente buscarPorId(Long id) throws PersistenciaException;

}
