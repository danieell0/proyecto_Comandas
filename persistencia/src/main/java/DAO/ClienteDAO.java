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
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceException;
import javax.persistence.TypedQuery;

/**
 * Clase de Acceso a Datos (DAO) para la entidad Cliente. Se encarga de aislar
 * la capa de la base de datos del resto de la aplicación. Utiliza el API de
 * persistencia de Java (JPA) para realizar las operaciones de tipo CRUD (Crear,
 * Leer, Actualizar, Eliminar) sobre la base de datos 'comandas'.
 *
 * @author munos
 */
public class ClienteDAO implements IClienteDAO {

    // En tu ClienteDAO:
    public void actualizar(Cliente cliente) throws PersistenciaException {
        EntityManager em = ConexionBD.crearConexion();
        try {
            em.getTransaction().begin();
            em.merge(cliente); // Actualiza los datos en MySQL
            em.getTransaction().commit();
        } catch (Exception e) {
            em.getTransaction().rollback();
            throw new PersistenciaException("Error al actualizar cliente");
        } finally {
            em.close();
        }
    }
    
    /**
     * Actualiza la información de un cliente frecuente existente en la base de
     * datos. Utiliza el método 'merge' de JPA para sincronizar el objeto
     * modificado con el registro de la tabla.
     *
     * * @param cliente El objeto ClienteFrecuente con los datos actualizados
     * (debe contener un ID válido).
     * @return El objeto ClienteFrecuente tal como quedó guardado en la base de
     * datos.
     * @throws PersistenciaException Si ocurre un error durante la transacción
     * (ej. pérdida de conexión).
     */
    @Override
    public ClienteFrecuente editar(ClienteFrecuente cliente) throws PersistenciaException {
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
     * Inserta un nuevo registro de cliente en la base de datos. Al usar
     * 'persist', JPA le asignará automáticamente un ID generado al objeto.
     *
     * * @param cliente Objeto de tipo Cliente (o ClienteFrecuente) que se
     * desea guardar.
     * @return El mismo objeto Cliente, pero ahora con su ID asignado por la
     * base de datos.
     * @throws PersistenciaException Si ocurre un error de base de datos o
     * violación de restricciones.
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
     * Elimina físicamente un registro de cliente de la base de datos mediante
     * su ID. Primero busca si el cliente existe; si lo encuentra, procede a
     * eliminarlo.
     *
     * * @param idCliente Identificador único del cliente a borrar.
     * @return true si el cliente fue encontrado y eliminado con éxito, false si
     * el cliente no existía.
     * @throws PersistenciaException Si la transacción falla o el ID es
     * inválido.
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
     * Recupera todos los clientes frecuentes registrados en el sistema. Utiliza
     * una consulta JPQL (Java Persistence Query Language) básica.
     *
     * * @return Una lista completa de objetos {@link ClienteFrecuente}.
     * @throws PersistenciaException Si falla la consulta a la base de datos.
     */
    @Override
    public List<ClienteFrecuente> obtenerClientes() throws PersistenciaException {
        EntityManager em = ConexionBD.crearConexion();
        try {
            em.clear(); // Limpiamos caché local por si acaso
            String comandoJPQL = "SELECT p FROM ClienteFrecuente p";
            TypedQuery<ClienteFrecuente> query = em.createQuery(comandoJPQL, ClienteFrecuente.class);
            
            // --- AQUÍ ESTÁ LA SOLUCIÓN DEL CACHÉ ---
            // Le decimos a JPA: "Ignora lo que tienes guardado y ve por los datos frescos"
            query.setHint("javax.persistence.cache.storeMode", "REFRESH");
            
            return query.getResultList();
        } catch (Exception e) {
            throw new PersistenciaException("Error al obtener la lista de clientes frecuentes");
        } finally {
            em.close();
        }
    }

    /**
     * Realiza una búsqueda flexible usando JPQL. Busca coincidencias parciales
     * (LIKE) en el nombre, apellidos, teléfono o correo electrónico.
     *
     * * @param filtro Cadena de texto a buscar.
     * @return Una lista de entidades que coincidan parcial o totalmente con el
     * filtro.
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

    /**
     * Obtiene el cliente general del sistema.
     *
     * <p>
     * Este método busca un cliente genérico en la base de datos que se utiliza
     * cuando una comanda no tiene un cliente específico asociado.</p>
     *
     * <p>
     * Si no existe un cliente general, se crea automáticamente con valores
     * predeterminados y se persiste en la base de datos.</p>
     *
     * @return objeto {@link Cliente} correspondiente al cliente general
     *
     * @throws PersistenciaException si ocurre un error durante la consulta o
     * persistencia
     */
    @Override
    public Cliente obtenerClienteGeneral() throws PersistenciaException {
        EntityManager em = ConexionBD.crearConexion();
        try {
            return em.createQuery(
                    "SELECT c FROM Cliente c WHERE TYPE(c) = Cliente",
                    Cliente.class
            )
                    .setMaxResults(1)
                    .getSingleResult();

        } catch (NoResultException e) {

            // Crear cliente general si no existe
            Cliente cliente = new Cliente();
            cliente.setNombre("Cliente General");
            cliente.setApellidoPaterno("General");
            cliente.setApellidoMaterno("General");
            cliente.setTelefono("0000000000");
            cliente.setCorreoElectronico("general@cliente.com");
            cliente.setFechaRegistro(LocalDate.now());

            em.getTransaction().begin();
            em.persist(cliente);
            em.getTransaction().commit();

            return cliente;

        } catch (Exception e) {
            throw new PersistenciaException("Error al obtener cliente general", e);
        } finally {
            em.close();
        }
    }

    /**
     * Busca un cliente por su identificador.
     *
     * <p>
     * Realiza una consulta directa a la base de datos utilizando el
     * {@link EntityManager} para recuperar la entidad {@link Cliente}.</p>
     *
     * @param id identificador del cliente
     * @return objeto {@link Cliente} si existe, o {@code null} si no se
     * encuentra
     *
     * @throws PersistenciaException si ocurre un error durante la consulta
     */
    @Override
    public Cliente buscarPorId(Long id) throws PersistenciaException {
        EntityManager em = ConexionBD.crearConexion();

        try {
            return em.find(Cliente.class, id);

        } catch (Exception e) {
            throw new PersistenciaException("Error al buscar cliente por id", e);
        } finally {
            em.close();
        }
    }
}
