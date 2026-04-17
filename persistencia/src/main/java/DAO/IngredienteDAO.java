/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import InterfacesDAO.IIngredienteDAO;
import conexion.ConexionBD;

import entidades.Ingrediente;

import excepciones.PersistenciaException;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

/**
 * DAO para la gestion de ingredientes en la base de datos
 * Implementa la validacion de nombre y unidad de medida unicos
 * @author Benjamin
 */
public class IngredienteDAO implements IIngredienteDAO{
    
    /**
     * Registra un nuevo ingrediente si no existe uno con el mismo nombre y unidad.
     * @param ingrediente El ingrediente a registrar.
     * @throws PersistenciaException Si ya existe el registro o hay error de BD.
     */
    public void agregarIngrediente(Ingrediente ingrediente) throws PersistenciaException {
        EntityManager em = ConexionBD.crearConexion();
        try {
            // 1. Validar unicidad (Regla del documento)
            if (existeIngrediente(ingrediente.getNombre(), ingrediente.getUnidadMedida())) {
                throw new PersistenciaException("Ya existe el ingrediente '" + 
                        ingrediente.getNombre() + "' con la unidad '" + 
                        ingrediente.getUnidadMedida() + "'.");
            }

            em.getTransaction().begin();
            em.persist(ingrediente);
            em.getTransaction().commit();
        } catch (PersistenciaException e) {
            throw e; // Relanzamos nuestra validación
        } catch (Exception e) {
            if (em.getTransaction().isActive()) em.getTransaction().rollback();
            throw new PersistenciaException("Error al guardar el ingrediente: " + e.getMessage());
        } finally {
            em.close();
        }
    }

    /**
     * Verifica en la BD si ya existe la combinación nombre + unidad.
     */
    public boolean existeIngrediente(String nombre, String unidad) {
        EntityManager em = ConexionBD.crearConexion();
        try {
            String jpql = "SELECT COUNT(i) FROM Ingrediente i " +
                         "WHERE i.nombre = :nombre AND i.unidadMedida = :unidad";
            Long count = em.createQuery(jpql, Long.class)
                    .setParameter("nombre", nombre)
                    .setParameter("unidad", unidad)
                    .getSingleResult();
            return count > 0;
        } finally {
            em.close();
        }
    }

    /**
     * Obtiene todos los ingredientes registrados.
     */
    public List<Ingrediente> consultarTodos() throws PersistenciaException {
        EntityManager em = ConexionBD.crearConexion();
        try {
            TypedQuery<Ingrediente> query = em.createQuery("SELECT i FROM Ingrediente i", Ingrediente.class);
            return query.getResultList();
        } catch (Exception e) {
            throw new PersistenciaException("Error al consultar ingredientes: " + e.getMessage());
        } finally {
            em.close();
        }
    }

    /**
     * Actualiza solo el stock de un ingrediente (Ajuste de inventario).
     */
    public void actualizarStock(Long id, Double nuevoStock) throws PersistenciaException {
        EntityManager em = ConexionBD.crearConexion();
        try {
            em.getTransaction().begin();
            Ingrediente i = em.find(Ingrediente.class, id);
            if (i != null) {
                i.setCantidadActual(nuevoStock);
                em.merge(i);
            }
            em.getTransaction().commit();
        } catch (Exception e) {
            if (em.getTransaction().isActive()) em.getTransaction().rollback();
            throw new PersistenciaException("No se pudo actualizar el stock.");
        } finally {
            em.close();
        }
    }
    
    /**
     * Metodo que elimina un ingrediente de la base de datos
     * @param id
     * @throws PersistenciaException 
     */
    public void borrarIngrediente(Long id) throws PersistenciaException{
        EntityManager em = ConexionBD.crearConexion();
        try {
            em.getTransaction().begin();
            // Buscamos el objeto en el contexto de persistencia actual
            Ingrediente ingrediente = em.find(Ingrediente.class, id);
            
            if (ingrediente != null) {
                em.remove(ingrediente);
            } else {
                throw new PersistenciaException("El ingrediente con ID " + id + " ya no existe.");
            }
            
            em.getTransaction().commit();
        } catch (Exception e) {
            if (em.getTransaction().isActive()) em.getTransaction().rollback();
            throw new PersistenciaException("No se pudo eliminar el ingrediente: " + e.getMessage());
        } finally {
            em.close();
        }
    }
    
    @Override
    public java.util.List<Ingrediente> buscarIngredientes(String nombre, String unidad) throws PersistenciaException {
        EntityManager em = ConexionBD.crearConexion();
        try {
            // Empezamos la consulta base (1=1 es un truco para poder encadenar los AND fácilmente)
            StringBuilder jpql = new StringBuilder("SELECT i FROM Ingrediente i WHERE 1=1 ");
            
            // Si el usuario escribió algo, agregamos el filtro de nombre (LIKE para búsquedas parciales)
            if (nombre != null && !nombre.trim().isEmpty()) {
                jpql.append("AND LOWER(i.nombre) LIKE LOWER(:nombre) ");
            }
            // Si el usuario eligió una medida específica, la agregamos
            if (unidad != null && !unidad.equals("Todas las medidas")) {
                jpql.append("AND i.unidadMedida = :unidad ");
            }

            TypedQuery<Ingrediente> query = em.createQuery(jpql.toString(), Ingrediente.class);

            // Rellenamos los parámetros de la consulta si los agregamos
            if (nombre != null && !nombre.trim().isEmpty()) {
                query.setParameter("nombre", "%" + nombre.trim() + "%"); // Los % son para que busque coincidencias en cualquier parte del texto
            }
            if (unidad != null && !unidad.equals("Todas las medidas")) {
                query.setParameter("unidad", unidad);
            }

            return query.getResultList();
        } catch (Exception e) {
            throw new PersistenciaException("Error al realizar la búsqueda: " + e.getMessage());
        } finally {
            em.close();
        }
    }
}
