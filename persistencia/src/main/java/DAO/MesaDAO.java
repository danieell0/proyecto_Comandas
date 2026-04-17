package DAO;

import Enums.EstadoMesa;
import InterfacesDAO.IMesaDAO;
import conexion.ConexionBD;
import entidades.Mesa;
import excepciones.PersistenciaException;
import java.util.List;
import javax.persistence.EntityManager;

/**
 * Implementación de la interfaz {@link IMesaDAO} que gestiona
 * las operaciones de persistencia para la entidad {@link Mesa}.
 * 
 * <p>Esta clase pertenece a la capa DAO (Data Access Object) y se encarga
 * de interactuar con la base de datos utilizando JPA.</p>
 * 
 * <p>Proporciona métodos para consultar, actualizar y filtrar mesas
 * según su estado.</p>
 * 
 * @author munos
 */
public class MesaDAO implements IMesaDAO {

    /**
     * Actualiza una mesa existente en la base de datos.
     * 
     * @param mesa entidad {@link Mesa} con los datos a actualizar
     * @return entidad {@link Mesa} actualizada
     * 
     * @throws PersistenciaException si ocurre un error durante la operación
     */
    @Override
    public Mesa actualizar(Mesa mesa) throws PersistenciaException {
        EntityManager em = ConexionBD.crearConexion();

        try {
            em.getTransaction().begin();
            Mesa actualizada = em.merge(mesa);
            em.getTransaction().commit();
            return actualizada;

        } catch (Exception e) {
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            throw new PersistenciaException("Error al actualizar mesa", e);
        } finally {
            em.close();
        }
    }

    /**
     * Busca una mesa por su identificador.
     * 
     * @param id identificador de la mesa
     * @return objeto {@link Mesa} encontrado o {@code null} si no existe
     * 
     * @throws PersistenciaException si ocurre un error en la consulta
     */
    @Override
    public Mesa buscarPorId(Long id) throws PersistenciaException {
        EntityManager em = ConexionBD.crearConexion();

        try {
            return em.find(Mesa.class, id);

        } catch (Exception e) {
            throw new PersistenciaException("Error al buscar mesa", e);
        } finally {
            em.close();
        }
    }

    /**
     * Obtiene todas las mesas registradas en la base de datos.
     * 
     * @return lista de objetos {@link Mesa}
     * 
     * @throws PersistenciaException si ocurre un error en la consulta
     */
    @Override
    public List<Mesa> obtenerTodas() throws PersistenciaException {
        EntityManager em = ConexionBD.crearConexion();

        try {
            return em.createQuery("SELECT m FROM Mesa m", Mesa.class)
                    .getResultList();

        } catch (Exception e) {
            throw new PersistenciaException("Error al obtener mesas", e);
        } finally {
            em.close();
        }
    }

    /**
     * Obtiene las mesas que se encuentran en estado disponible.
     * 
     * <p>Se filtran las mesas cuyo estado es {@link EstadoMesa#Disponible}.</p>
     * 
     * @return lista de mesas disponibles
     * 
     * @throws PersistenciaException si ocurre un error en la consulta
     */
    @Override
    public List<Mesa> obtenerDisponibles() throws PersistenciaException {
        EntityManager em = ConexionBD.crearConexion();

        try {
            return em.createQuery(
                    "SELECT m FROM Mesa m WHERE m.estado = :estado", Mesa.class)
                    .setParameter("estado", EstadoMesa.Disponible)
                    .getResultList();

        } catch (Exception e) {
            throw new PersistenciaException("Error al consultar mesas disponibles", e);
        } finally {
            em.close();
        }
    }
}