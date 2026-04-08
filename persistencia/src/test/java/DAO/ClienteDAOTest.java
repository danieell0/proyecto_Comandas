/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package DAO;

import entidades.ClienteFrecuente;
import excepciones.PersistenciaException;
import java.time.LocalDate;
import java.util.List;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;

/**
 *
 * @author Benjamin
 */
public class ClienteDAOTest {
//    private ClienteDAO clienteDAO;
//
//    // Esto se ejecuta ANTES de cada prueba para darnos un DAO fresquecito
//    @BeforeEach
//    public void setUp() {
//        clienteDAO = new ClienteDAO();
//    }
//
//    // ==========================================
//    // PRUEBAS PARA: guardarCliente()
//    // ==========================================
//
//    @Test
//    public void testGuardarCliente_FlujoBase() throws PersistenciaException {
//        // 1. Preparar: Creamos un cliente válido
//        ClienteFrecuente nuevoCliente = new ClienteFrecuente(
//                "Carlos", "Ruiz", "Gomez", "1234567890", LocalDate.now(), 0.0);
//
//        // 2. Ejecutar: Lo guardamos en la base de datos
//        ClienteFrecuente clienteGuardado = (ClienteFrecuente) clienteDAO.guardarCliente(nuevoCliente);
//
//        // 3. Validar: Comprobamos que JPA le haya asignado un ID (significa que sí se guardó)
//        assertNotNull(clienteGuardado.getId(), "El ID no debería ser nulo si se guardó correctamente");
//        assertEquals("Carlos", clienteGuardado.getNombre(), "El nombre guardado no coincide");
//    }
//
//    @Test
//    public void testGuardarCliente_FlujoAlternativo_DebeLanzarExcepcion() {
//        // 1. Preparar: Hacemos algo mal a propósito (mandar un cliente nulo)
//        ClienteFrecuente clienteInvalido = null;
//
//        // 2 y 3. Ejecutar y Validar: Le decimos a JUnit "Espero que este código lance una PersistenciaException"
//        PersistenciaException excepcion = assertThrows(PersistenciaException.class, () -> {
//            clienteDAO.guardarCliente(clienteInvalido);
//        });
//
//        // Validamos que el mensaje de error sea exactamente el que tú programaste
//        assertEquals("error al guardar", excepcion.getMessage());
//    }
//    
//    // ==========================================
//    // PRUEBAS PARA: editar()
//    // ==========================================
//
//    @Test
//    public void testEditarCliente_FlujoBase() throws PersistenciaException {
//        // 1. Preparar: Creamos y guardamos el cliente original
//        ClienteFrecuente nuevoCliente = new ClienteFrecuente(
//                "Carlos", "Ruiz", "Gomez", "1234567890", LocalDate.now(), 0.0);
//        
//        ClienteFrecuente clienteGuardado = (ClienteFrecuente) clienteDAO.guardarCliente(nuevoCliente);
//        
//        // 2. Modificar: ¡AQUÍ ESTÁ LA CLAVE! 
//        // Usamos el MISMO objeto 'clienteGuardado' (que ya tiene un ID) y le cambiamos los datos
//        clienteGuardado.setNombre("Antonio");
//        clienteGuardado.setApellidoPaterno("Olivas");
//        clienteGuardado.setApellidoMaterno("Gastelum");
//        clienteGuardado.setTelefono("6442095393");
//        
//        // 3. Ejecutar: Mandamos el objeto modificado al DAO
//        ClienteFrecuente clienteEditado = clienteDAO.editar(clienteGuardado);
//        
//        // 4. Validar: Comprobamos que los datos devueltos sean los nuevos
//        assertEquals("Antonio", clienteEditado.getNombre(), "El nombre no se actualizó");
//        assertEquals("Olivas", clienteEditado.getApellidoPaterno(), "El apellido no se actualizó");
//        assertEquals("6442095393", clienteEditado.getTelefono(), "El teléfono no se actualizó");
//        
//        // Validamos que siga siendo el mismo registro (mismo ID)
//        assertEquals(clienteGuardado.getId(), clienteEditado.getId(), "El ID no debería cambiar al editar");
//    }
//
//    @Test
//    public void testEditarCliente_FlujoAlternativo_DebeLanzarExcepcion() {
//        // 1. Preparar: Le mandamos un nulo al merge para forzar un error de JPA
//        ClienteFrecuente clienteInvalido = null;
//
//        // 2 y 3. Ejecutar y Validar: Esperamos que tu bloque catch atrape el error 
//        // y lance tu PersistenciaException personalizada
//        PersistenciaException excepcion = assertThrows(PersistenciaException.class, () -> {
//            clienteDAO.editar(clienteInvalido);
//        });
//
//        // Validamos que el mensaje sea exactamente el que pusiste en el throw de tu DAO
//        assertEquals("Error al editar el cliente", excepcion.getMessage());
//    }
//    
//    // ==========================================
//    // PRUEBAS PARA: eliminarCliente()
//    // ==========================================
//
//    @Test
//    public void testEliminarCliente_FlujoBase() throws PersistenciaException {
//        // 1. Preparar: Primero guardamos un cliente temporal exclusivamente para borrarlo
//        ClienteFrecuente clienteTemp = new ClienteFrecuente(
//                "Eliminar", "Este", "Registro", "0000000000", LocalDate.now(), 0.0);
//        
//        ClienteFrecuente clienteGuardado = (ClienteFrecuente) clienteDAO.guardarCliente(clienteTemp);
//        Long idParaBorrar = clienteGuardado.getId(); // Obtenemos el ID real que le dio la BD
//
//        // 2. Ejecutar: Llamamos a tu método con el ID válido
//        boolean resultado = clienteDAO.eliminarCliente(idParaBorrar);
//
//        // 3. Validar: Comprobamos que retorne true
//        assertTrue(resultado, "Debería retornar true porque el cliente existía y fue borrado");
//    }
//
//    @Test
//    public void testEliminarCliente_FlujoAlternativo_NoExiste() throws PersistenciaException {
//        // 1. Ejecutar: Le pasamos un ID altísimo que sabemos que no existe en tu tabla
//        boolean resultado = clienteDAO.eliminarCliente(999999L);
//
//        // 2. Validar: Comprobamos que pase por tu 'if (cliente == null)' y retorne false
//        assertFalse(resultado, "Debería retornar false porque el ID 999999 no existe en la BD");
//    }
//
//    @Test
//    public void testEliminarCliente_FlujoAlternativo_DebeLanzarExcepcion() {
//        // 1. Preparar: JPA no acepta IDs nulos en el método em.find(). 
//        // Si le mandamos null, lanzará un IllegalArgumentException interno que tu 'catch' va a atrapar.
//        Long idInvalido = null;
//
//        // 2 y 3. Ejecutar y Validar: Esperamos que tu código lance la PersistenciaException
//        PersistenciaException excepcion = assertThrows(PersistenciaException.class, () -> {
//            clienteDAO.eliminarCliente(idInvalido);
//        });
//
//        // Validamos que el texto del error sea exactamente el que tú escribiste en el throw
//        assertEquals("error al eliminar", excepcion.getMessage());
//    }
//    
//    // ==========================================
//    // PRUEBAS PARA: obtenerClientes()
//    // ==========================================
//
//    @Test
//    public void testObtenerClientes_FlujoBase() throws PersistenciaException {
//        // 1. Preparar: Insertamos un par de clientes para garantizar que haya datos
//        ClienteFrecuente cliente1 = new ClienteFrecuente(
//                "Maria", "Lopez", "Garcia", "1111111111", LocalDate.now(), 100.0);
//        ClienteFrecuente cliente2 = new ClienteFrecuente(
//                "Pedro", "Sanchez", "Lara", "2222222222", LocalDate.now(), 200.0);
//        
//        clienteDAO.guardarCliente(cliente1);
//        clienteDAO.guardarCliente(cliente2);
//
//        // 2. Ejecutar: Llamamos a tu método que trae a todos los clientes
//        List<ClienteFrecuente> listaClientes = clienteDAO.obtenerClientes();
//
//        // 3. Validar: 
//        // Primero, que la lista no sea nula
//        assertNotNull(listaClientes, "La lista devuelta no debería ser nula");
//        
//        // Segundo, que la lista tenga al menos los 2 clientes que acabamos de meter
//        // (Usamos assertTrue con '>=' porque podrían existir clientes de pruebas anteriores)
//        assertTrue(listaClientes.size() >= 2, "La lista debería contener al menos 2 clientes");
//        
//        // Tercero, verificamos que el tipo de objetos de la lista sean realmente ClienteFrecuente
//        assertTrue(listaClientes.get(0) instanceof ClienteFrecuente, "Los objetos de la lista deben ser del tipo ClienteFrecuente");
//    }
//
//    // ==========================================
//    // PRUEBAS PARA: consultarPorFiltro()
//    // ==========================================
//
//    @Test
//    public void testConsultarPorFiltro_FlujoBase() throws PersistenciaException {
//        // 1. Ejecutar: Buscamos un texto que sabemos que existe 
//        // (Asumiendo que la prueba anterior guardó a "Carlos")
//        List<ClienteFrecuente> resultados = clienteDAO.consultarPorFiltro("Carlos");
//
//        // 2. Validar: Comprobamos que la lista no sea nula y traiga al menos un resultado
//        assertNotNull(resultados, "La lista de resultados no debe ser nula");
//        assertFalse(resultados.isEmpty(), "Debería encontrar al menos un cliente con el nombre Carlos");
//    }
//    
//    @Test
//    public void testEliminarCliente_FlujoAlternativo_ClienteNoExiste() throws PersistenciaException {
//        // 1. Ejecutar: Intentamos borrar un ID que sabemos que no existe (ej. ID 99999)
//        boolean resultado = clienteDAO.eliminarCliente(99999L);
//        
//        // 2. Validar: Tu código dice que si no lo encuentra, retorna false. ¡Validémoslo!
//        assertFalse(resultado, "Debería retornar false al intentar eliminar un ID que no existe");
//    }
//    
}
