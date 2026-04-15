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
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

/**
 *
 * @author Jorge
 */
public class IngredienteDAO implements IIngredienteDAO {

    private static final Logger logger = Logger.getLogger(IngredienteDAO.class.getName());

    @Override
    public List<Ingrediente> obtenerIngredientes() throws PersistenciaException {
        EntityManager em = ConexionBD.crearConexion();
        try {
            String comandoJPQL = """
                               SELECT i FROM Ingrediente i
                               """;
            TypedQuery<Ingrediente> query = em.createQuery(comandoJPQL, Ingrediente.class);
            return query.getResultList();
        } catch (Exception e) {
            logger.log(Level.SEVERE, "Error al obtener la lista de ingredientes", e);
            throw new PersistenciaException("Error al obtener la lista de ingredientes", e);
        } finally {
            em.close();
        }
    }

    @Override
    public List<Ingrediente> buscarPorNombre(String nombre) throws PersistenciaException {
        EntityManager em = ConexionBD.crearConexion();
        try {
            String comandoJPQL = "SELECT i FROM Ingrediente i  WHERE LOWER(i.nombre) LIKE LOWER(:nombre)";
            TypedQuery<Ingrediente> query = em.createQuery(comandoJPQL, Ingrediente.class);
            query.setParameter("nombre", "%" + nombre + "%");
            return query.getResultList();
        } catch (Exception e) {
            logger.log(Level.SEVERE, "Error al buscar ingredientes", e);
            throw new PersistenciaException("Error al buscar ingredientes", e);
        } finally {
            em.close();
        }
    }

}
