package DAO;

import Enums.EstadoComandas;
import InterfacesDAO.IComandaDAO;
import conexion.ConexionBD;
import entidades.Comanda;
import excepciones.PersistenciaException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import javax.persistence.EntityManager;

/**
 * Implementación de la interfaz {@link IComandaDAO} que gestiona
 * las operaciones de persistencia para la entidad {@link Comanda}.
 * 
 * <p>Esta clase pertenece a la capa DAO (Data Access Object) y se encarga
 * de interactuar directamente con la base de datos mediante JPA.</p>
 * 
 * <p>Incluye operaciones CRUD básicas y consultas específicas como:
 * obtener la comanda activa por mesa y contar comandas del día.</p>
 * 
 * @author munos
 */
public class ComandaDAO implements IComandaDAO {

    /**
     * Guarda una nueva comanda en la base de datos.
     * 
     * @param comanda entidad {@link Comanda} a persistir
     * 
     * @throws PersistenciaException si ocurre un error durante la operación
     */
    @Override
    public void guardar(Comanda comanda) throws PersistenciaException {
        EntityManager em = ConexionBD.crearConexion();
        try {
            em.getTransaction().begin();
            em.persist(comanda);
            em.getTransaction().commit();

        } catch (Exception e) {
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            throw new PersistenciaException("Error al guardar comanda", e);
        } finally {
            em.close();
        }
    }

    /**
     * Obtiene la comanda activa asociada a una mesa.
     * 
     * <p>Solo retorna la comanda cuyo estado sea {@link EstadoComandas#ABIERTA}.</p>
     * 
     * @param idMesa identificador de la mesa
     * @return {@link Comanda} si existe, o {@code null} si no hay comanda activa
     * 
     * @throws PersistenciaException si ocurre un error en la consulta
     */
    @Override
    public Comanda obtenerPorMesa(Long idMesa) throws PersistenciaException {

        EntityManager em = ConexionBD.crearConexion();

        try {
            return em.createQuery(
                    "SELECT c FROM Comanda c WHERE c.mesa.id = :id AND c.estado = :estado",
                    Comanda.class)
                    .setParameter("id", idMesa)
                    .setParameter("estado", EstadoComandas.ABIERTA)
                    .getResultStream()
                    .findFirst()
                    .orElse(null);

        } catch (Exception e) {
            throw new PersistenciaException("Error al obtener comanda por mesa", e);
        } finally {
            em.close();
        }
    }

    /**
     * Busca una comanda por su identificador.
     * 
     * @param id identificador de la comanda
     * @return {@link Comanda} encontrada o {@code null} si no existe
     * 
     * @throws PersistenciaException si ocurre un error en la consulta
     */
    @Override
    public Comanda buscarPorId(Long id) throws PersistenciaException {
        EntityManager em = ConexionBD.crearConexion();

        try {
            return em.find(Comanda.class, id);

        } catch (Exception e) {
            throw new PersistenciaException("Error al buscar comanda", e);
        } finally {
            em.close();
        }
    }

    /**
     * Actualiza una comanda existente en la base de datos.
     * 
     * @param comanda entidad {@link Comanda} con cambios
     * @return entidad {@link Comanda} actualizada
     * 
     * @throws PersistenciaException si ocurre un error durante la actualización
     */
    @Override
    public Comanda actualizar(Comanda comanda) throws PersistenciaException {
        EntityManager em = ConexionBD.crearConexion();

        try {
            em.getTransaction().begin();
            Comanda c = em.merge(comanda);
            em.getTransaction().commit();
            return c;

        } catch (Exception e) {
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            throw new PersistenciaException("Error al actualizar comanda", e);
        } finally {
            em.close();
        }
    }

    /**
     * Cuenta la cantidad de comandas registradas en el día actual.
     * 
     * <p>Se utiliza principalmente para generar folios consecutivos diarios.</p>
     * 
     * @return número de comandas del día actual
     * 
     * @throws PersistenciaException si ocurre un error en la consulta
     */
    @Override
    public int contarComandasHoy() throws PersistenciaException {
        EntityManager em = ConexionBD.crearConexion();
        try {
            LocalDate hoy = LocalDate.now();
            LocalDateTime inicio = hoy.atStartOfDay();
            LocalDateTime fin = hoy.atTime(23, 59, 59);

            return em.createQuery(
                    "SELECT COUNT(c) FROM Comanda c WHERE c.fechaHora BETWEEN :inicio AND :fin",
                    Long.class)
                    .setParameter("inicio", inicio)
                    .setParameter("fin", fin)
                    .getSingleResult()
                    .intValue();

        } finally {
            em.close();
        }
    }
}