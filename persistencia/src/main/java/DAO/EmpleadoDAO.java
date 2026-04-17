/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import excepciones.PersistenciaException;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

/**
 * Clase data acces object para las operaciones relacionadas con los empleados
 * @author Benjamin
 */
public class EmpleadoDAO {
    
    /**
     * Busca a un mesero en la base de datos usando su código único.
     * * @param codigo El código numérico ingresado en la pantalla de login.
     * @return El objeto EmpleadoMesero si existe, o null si el código es incorrecto.
     * @throws PersistenciaException Si hay un error de conexión con MySQL.
     */
    public entidades.EmpleadoMesero buscarMeseroPorCodigo(Long codigo) throws PersistenciaException {
        EntityManager em = conexion.ConexionBD.crearConexion();
        try {
            // JPQL: Le decimos "Tráeme al mesero (m) donde su atributo 'codigoMesero' sea igual al parámetro que te voy a dar"
            String jpql = "SELECT m FROM EmpleadoMesero m WHERE m.codigoMesero = :codigoBuscado";
            
            TypedQuery<entidades.EmpleadoMesero> query = em.createQuery(jpql, entidades.EmpleadoMesero.class);
            query.setParameter("codigoBuscado", codigo); // Intercambiamos el parámetro

            // getSingleResult() ejecuta la consulta y trae un solo objeto
            return query.getSingleResult();
            
        } catch (javax.persistence.NoResultException e) {
            // Este catch es VITAL. Si MySQL dice "No hay nadie con ese código", 
            // JPA lanza un NoResultException. Lo atrapamos en silencio y devolvemos null
            // para que el BO sepa que el login falló.
            return null;
            
        } catch (Exception e) {
            // Si entra aquí, es porque se cayó la BD o hay un error de sintaxis
            throw new PersistenciaException("Error al consultar el código del mesero: " + e.getMessage());
            
        } finally {
            if (em != null && em.isOpen()) {
                em.close(); // Siempre cerrar la conexión
            }
        }
    }
    
}
