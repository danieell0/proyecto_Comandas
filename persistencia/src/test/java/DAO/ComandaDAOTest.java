/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package DAO;

import Enums.EstadoComandas;
import entidades.Comanda;
import excepciones.PersistenciaException;
import java.time.LocalDateTime;
import javax.persistence.EntityManager;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;

/**
 *
 * @author Benjamin
 */
public class ComandaDAOTest {
    
   private ComandaDAO comandaDAO;
    private Comanda comandaPrueba;

    @BeforeEach
    public void setUp() {
        comandaDAO = new ComandaDAO();
        comandaPrueba = new Comanda();
        
        // Configuramos los datos básicos de la comanda
        comandaPrueba.setFolio("TEST-001");
        comandaPrueba.setFechaHora(LocalDateTime.now());
        comandaPrueba.setEstado(EstadoComandas.ABIERTA);
        comandaPrueba.setTotalVenta(150.0);
        
        // --- LA SOLUCIÓN INTELIGENTE: BUSCAR EL PRIMER REGISTRO DISPONIBLE ---
        javax.persistence.EntityManager em = conexion.ConexionBD.crearConexion();
        try {
            // Buscamos dinámicamente el PRIMER registro de cada tabla (sin importar su ID)
            entidades.Cliente clienteReal = em.createQuery("SELECT c FROM Cliente c", entidades.Cliente.class)
                    .setMaxResults(1).getResultStream().findFirst().orElse(null);
                    
            entidades.Mesa mesaReal = em.createQuery("SELECT m FROM Mesa m", entidades.Mesa.class)
                    .setMaxResults(1).getResultStream().findFirst().orElse(null);
                    
            entidades.EmpleadoMesero meseroReal = em.createQuery("SELECT e FROM EmpleadoMesero e", entidades.EmpleadoMesero.class)
                    .setMaxResults(1).getResultStream().findFirst().orElse(null);
            
            // Validamos que la base de datos no esté completamente vacía
            if (clienteReal == null || mesaReal == null || meseroReal == null) {
                throw new RuntimeException("La BD está vacía. ¡Abre tu app y registra al menos 1 Cliente, 1 Mesa y 1 Mesero antes de probar!");
            }
            
            // Asignamos las entidades reales encontradas a nuestra comanda de prueba
            comandaPrueba.setCliente(clienteReal);
            comandaPrueba.setMesa(mesaReal);
            comandaPrueba.setMesero(meseroReal);
            
        } finally {
            em.close();
        }
    }

    // ==========================================
    // PRUEBAS PARA: guardar()
    // ==========================================

    @Test
    public void testGuardarComanda_HappyPath() {
        assertDoesNotThrow(() -> {
            comandaDAO.guardar(comandaPrueba);
        }, "No debería lanzar excepción al guardar una comanda válida.");

        // Verificamos que JPA le haya asignado un ID automáticamente
        assertNotNull(comandaPrueba.getId(), "El ID de la comanda no debería ser nulo después de guardarla.");
    }

    @Test
    public void testGuardarComanda_UnhappyPath_ComandaNula() {
        // Intentar guardar un objeto nulo hará que JPA explote, 
        // y tu DAO debe atraparlo y lanzar tu PersistenciaException.
        assertThrows(PersistenciaException.class, () -> {
            comandaDAO.guardar(null);
        }, "Debería lanzar PersistenciaException si se intenta guardar un objeto nulo.");
    }

    // ==========================================
    // PRUEBAS PARA: buscarPorId()
    // ==========================================

    @Test
    public void testBuscarPorId_HappyPath() throws PersistenciaException {
        // Primero la guardamos
        comandaDAO.guardar(comandaPrueba);
        Long idGenerado = comandaPrueba.getId();

        // Luego la buscamos
        Comanda encontrada = comandaDAO.buscarPorId(idGenerado);

        assertNotNull(encontrada, "Debería encontrar la comanda que acabamos de guardar.");
        assertEquals("TEST-001", encontrada.getFolio(), "El folio debería coincidir.");
    }

    @Test
    public void testBuscarPorId_UnhappyPath_NoExiste() throws PersistenciaException {
        // Buscamos un ID fantasma. Tu método usa em.find(), el cual devuelve null si no existe.
        Long idFantasma = 999999L;
        Comanda encontrada = comandaDAO.buscarPorId(idFantasma);

        assertNull(encontrada, "Debería retornar null si el ID de la comanda no existe.");
    }

    // ==========================================
    // PRUEBAS PARA: actualizar()
    // ==========================================

    @Test
    public void testActualizarComanda_HappyPath() throws PersistenciaException {
        // 1. Guardar
        comandaDAO.guardar(comandaPrueba);
        
        // 2. Modificar el estado y el total
        comandaPrueba.setEstado(EstadoComandas.ENTREGADA);
        comandaPrueba.setTotalVenta(500.0);
        
        // 3. Actualizar
        assertDoesNotThrow(() -> {
            comandaDAO.actualizar(comandaPrueba);
        }, "No debería lanzar error al actualizar una comanda existente.");

        // 4. Verificar en la BD
        Comanda verificacion = comandaDAO.buscarPorId(comandaPrueba.getId());
        assertEquals(EstadoComandas.ENTREGADA, verificacion.getEstado(), "El estado debió cambiar a ENTREGADA.");
        assertEquals(500.0, verificacion.getTotalVenta(), "El total debió actualizarse.");
    }

    // ==========================================
    // PRUEBAS PARA: contarComandasHoy()
    // ==========================================

    @Test
    public void testContarComandasHoy_HappyPath() throws PersistenciaException {
        // Vemos cuántas hay antes de empezar el test
        int conteoInicial = comandaDAO.contarComandasHoy();

        // Guardamos una nueva comanda que tiene la fecha de HOY
        comandaDAO.guardar(comandaPrueba);

        // Volvemos a contar
        int conteoFinal = comandaDAO.contarComandasHoy();

        // La diferencia debe ser exactamente 1
        assertEquals(conteoInicial + 1, conteoFinal, "El conteo de hoy debería haber aumentado en 1.");
    }
    
}
