/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import InterfacesDAO.IClienteDAO;
import com.mysql.cj.QueryReturnType;
import conexion.ConexionBD;
import entidades.Cliente;
import entidades.ClienteFrecuente;
import excepciones.PersistenciaException;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceException;
import javax.persistence.TypedQuery;

/**
 * Clase de Acceso a Datos (DAO) para la entidad Cliente.
 * Se encarga de aislar la capa de la base de datos del resto de la aplicación.
 * Utiliza el API de persistencia de Java (JPA) para realizar las operaciones 
 * de tipo CRUD (Crear, Leer, Actualizar, Eliminar) sobre la base de datos 'comandas'.
 * @author munos
 */
public class ClienteDAO implements IClienteDAO {

    /**
     * Actualiza la información de un cliente frecuente existente en la base de datos.
     * Utiliza el método 'merge' de JPA para sincronizar el objeto modificado con el registro de la tabla.
     * * @param cliente El objeto ClienteFrecuente con los datos actualizados (debe contener un ID válido).
     * @return El objeto ClienteFrecuente tal como quedó guardado en la base de datos.
     * @throws PersistenciaException Si ocurre un error durante la transacción (ej. pérdida de conexión).
     */
    @Override
    public ClienteFrecuente editar(ClienteFrecuente cliente) throws PersistenciaException{
        EntityManager em = ConexionBD.crearConexion();

        try {
            em.getTransaction().begin();
            ClienteFrecuente actualizado = em.merge(cliente);
            em.getTransaction().commit();
            return actualizado;
        } catch (Exception e) {
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            throw new PersistenciaException("Error al editar el cliente");
        } finally {
            em.close();
        }
    }

    /**
     * Inserta un nuevo registro de cliente en la base de datos.
     * Al usar 'persist', JPA le asignará automáticamente un ID generado al objeto.
     * * @param cliente Objeto de tipo Cliente (o ClienteFrecuente) que se desea guardar.
     * @return El mismo objeto Cliente, pero ahora con su ID asignado por la base de datos.
     * @throws PersistenciaException Si ocurre un error de base de datos o violación de restricciones.
     */
    @Override
    public Cliente guardarCliente(Cliente cliente) throws PersistenciaException {
        EntityManager em = ConexionBD.crearConexion();
        try {
            em.getTransaction().begin();
            em.persist(cliente);
            em.getTransaction().commit();
            return cliente;
        } catch (Exception e) {
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            throw new PersistenciaException("error al guardar");
        } finally {
            em.close();
        }
    }

    /**
     * Elimina físicamente un registro de cliente de la base de datos mediante su ID.
     * Primero busca si el cliente existe; si lo encuentra, procede a eliminarlo.
     * * @param idCliente Identificador único del cliente a borrar.
     * @return true si el cliente fue encontrado y eliminado con éxito, false si el cliente no existía.
     * @throws PersistenciaException Si la transacción falla o el ID es inválido.
     */
    @Override
    public boolean eliminarCliente(Long idCliente) throws PersistenciaException {
        EntityManager em = ConexionBD.crearConexion();
        try {
            ClienteFrecuente cliente = em.find(ClienteFrecuente.class, idCliente);
            if (cliente == null) {
                return false;
            }
            em.getTransaction().begin();
            em.remove(cliente);
            em.getTransaction().commit();
            return true;
        } catch (Exception e) {
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            throw new PersistenciaException("error al eliminar");
        } finally {
            em.close();
        }
    }

    /**
     * Recupera todos los clientes frecuentes registrados en el sistema.
     * Utiliza una consulta JPQL (Java Persistence Query Language) básica.
     * * @return Una lista completa de objetos {@link ClienteFrecuente}.
     * @throws PersistenciaException Si falla la consulta a la base de datos.
     */
    @Override
    public List<ClienteFrecuente> obtenerClientes() throws PersistenciaException {
        EntityManager em=ConexionBD.crearConexion();
        try {
            String comandoJPQL="SELECT p FROM ClienteFrecuente p";
            TypedQuery<ClienteFrecuente> query=em.createQuery(comandoJPQL,ClienteFrecuente.class);
            return query.getResultList();
        } catch (Exception e) {
            throw new PersistenciaException("Error al obtener la lista de clientes frecuentes");
        }finally{
            em.close();
        }
    }
    
    /**
     * Realiza una búsqueda flexible usando JPQL. 
     * Busca coincidencias parciales (LIKE) en el nombre, apellidos, teléfono o correo electrónico.
     * * @param filtro Cadena de texto a buscar.
     * @return Una lista de entidades que coincidan parcial o totalmente con el filtro.
     * @throws PersistenciaException Si la consulta estructurada falla.
     */
    @Override
    public List<ClienteFrecuente> consultarPorFiltro(String filtro) throws PersistenciaException {
        EntityManager em = ConexionBD.crearConexion();
        try {
            String jpql = "SELECT c FROM ClienteFrecuente c WHERE c.nombre LIKE :filtro "
                    + "OR c.apellidoPaterno LIKE :filtro "
                    + "OR c.apellidoMaterno LIKE :filtro "
                    + "OR c.telefono LIKE :filtro "
                    + "OR c.correoElectronico LIKE :filtro";

            return em.createQuery(jpql, ClienteFrecuente.class)
                    .setParameter("filtro", "%" + filtro + "%")
                    .getResultList();
        } catch (Exception e) {
            throw new PersistenciaException("Error al realizar la busqueda filtrada", e);
        } finally {
            em.close();
        }
    }

}
