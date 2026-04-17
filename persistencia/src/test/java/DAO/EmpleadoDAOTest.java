/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package DAO;

import entidades.EmpleadoMesero;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;

/**
 *
 * @author Benjamin
 */
public class EmpleadoDAOTest {
    
    private final EmpleadoDAO empleadoDAO = new EmpleadoDAO();

    /**
     * Limpia la BD antes de cada prueba.
     */
    @BeforeEach
    public void limpiarAntes() {
        EntityManager em = conexion.ConexionBD.crearConexion();
        EntityTransaction tx = em.getTransaction();

        tx.begin();
        em.createQuery("DELETE FROM EmpleadoMesero").executeUpdate();
        tx.commit();

        em.close();
    }

    /**
     * Prueba OK: encuentra un mesero existente.
     */
    @Test
    public void testBuscarMeseroPorCodigo_OK() throws Exception {

        EntityManager em = conexion.ConexionBD.crearConexion();
        EntityTransaction tx = em.getTransaction();

        EmpleadoMesero mesero = new EmpleadoMesero();
        mesero.setCodigoMesero(123L);
        mesero.setNombre("Juan");

        tx.begin();
        em.persist(mesero);
        tx.commit();
        em.close();

        EmpleadoMesero resultado = empleadoDAO.buscarMeseroPorCodigo(123L);

        assertNotNull(resultado);
        assertEquals(123L, resultado.getCodigoMesero());
        assertEquals("Juan", resultado.getNombre());
    }

    /**
     * Prueba: retorna null si no existe.
     */
    @Test
    public void testBuscarMeseroPorCodigo_NoExiste() throws Exception {

        EmpleadoMesero resultado = empleadoDAO.buscarMeseroPorCodigo(99999L);

        assertNull(resultado);
    }
    
}
