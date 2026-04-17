/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package DAO;

import entidades.Ingrediente;
import excepciones.PersistenciaException;
import java.util.List;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;

/**
 *
 * @author Benjamin
 */
public class IngredienteDAOTest {
    
    private IngredienteDAO ingredienteDAO;
    private Ingrediente ingredientePrueba;

    /**
     * @BeforeEach se ejecuta ANTES de cada prueba (@Test).
     * Lo usamos para preparar el terreno con datos frescos.
     */
    @BeforeEach
    public void setUp() {
        ingredienteDAO = new IngredienteDAO();
        ingredientePrueba = new Ingrediente();
        ingredientePrueba.setNombre("Ingrediente_Test_001");
        ingredientePrueba.setUnidadMedida("Kilogramos");
        ingredientePrueba.setCantidadActual(15.5);
    }

    /**
     * @AfterEach se ejecuta DESPUÉS de cada prueba.
     * Lo usamos como "escoba" para borrar de la BD lo que acabamos de insertar,
     * así tu base de datos real no se llena de basura.
     */
    @AfterEach
    public void tearDown() {
        try {
            // Buscamos si quedó vivo nuestro ingrediente de prueba y lo borramos
            List<Ingrediente> lista = ingredienteDAO.buscarIngredientes("Ingrediente_Test_001", "Kilogramos");
            for (Ingrediente i : lista) {
                ingredienteDAO.borrarIngrediente(i.getId());
            }
        } catch (PersistenciaException e) {
            // Silenciamos el error en el tearDown para que no afecte el resultado del test
        }
    }

    // ==========================================
    // PRUEBAS PARA: agregarIngrediente()
    // ==========================================

    @Test
    public void testAgregarIngrediente_HappyPath() {
        // Ejecución: Intentamos agregarlo y verificamos que NO lance error
        assertDoesNotThrow(() -> {
            ingredienteDAO.agregarIngrediente(ingredientePrueba);
        }, "No debería lanzar excepción si el ingrediente es nuevo.");

        // Verificación: Comprobamos que realmente se guardó en MySQL
        boolean existe = ingredienteDAO.existeIngrediente("Ingrediente_Test_001", "Kilogramos");
        assertTrue(existe, "El ingrediente debería existir en la BD después de agregarlo.");
    }

    @Test
    public void testAgregarIngrediente_UnhappyPath_YaExiste() {
        // Preparación: Agregamos el ingrediente una vez (esto debe funcionar)
        assertDoesNotThrow(() -> ingredienteDAO.agregarIngrediente(ingredientePrueba));

        // Ejecución y Verificación: Intentamos agregarlo OTRA VEZ con los mismos datos
        // Guardamos la excepción que lanza para analizarla
        PersistenciaException excepcionLanzada = assertThrows(PersistenciaException.class, () -> {
            ingredienteDAO.agregarIngrediente(ingredientePrueba);
        });

        // Comprobamos que el mensaje de error sea exactamente el que tú programaste
        assertTrue(excepcionLanzada.getMessage().contains("Ya existe el ingrediente"), 
                "Debería avisar que el ingrediente ya existe.");
    }

    // ==========================================
    // PRUEBAS PARA: borrarIngrediente()
    // ==========================================

    @Test
    public void testBorrarIngrediente_HappyPath() throws PersistenciaException {
        // Preparación: Insertamos el ingrediente y obtenemos su ID real generado por MySQL
        ingredienteDAO.agregarIngrediente(ingredientePrueba);
        Ingrediente guardado = ingredienteDAO.buscarIngredientes("Ingrediente_Test_001", "Kilogramos").get(0);

        // Ejecución: Intentamos borrarlo usando su ID
        assertDoesNotThrow(() -> {
            ingredienteDAO.borrarIngrediente(guardado.getId());
        }, "No debería lanzar excepción al borrar un ID válido.");

        // Verificación: Comprobamos que ya no exista en la BD
        boolean existeAun = ingredienteDAO.existeIngrediente("Ingrediente_Test_001", "Kilogramos");
        assertFalse(existeAun, "El ingrediente NO debería existir en la BD después de borrarlo.");
    }

    @Test
    public void testBorrarIngrediente_UnhappyPath_NoExiste() {
        // Preparación: Inventamos un ID que (esperemos) nunca exista en tu BD
        Long idInvento = 99999999L;

        // Ejecución y Verificación: Intentamos borrar ese ID fantasma
        PersistenciaException excepcionLanzada = assertThrows(PersistenciaException.class, () -> {
            ingredienteDAO.borrarIngrediente(idInvento);
        });

        // Comprobamos que salte tu validación personalizada
        assertTrue(excepcionLanzada.getMessage().contains("ya no existe"), 
                "Debería lanzar la excepción de que el ID no existe.");
    }
    
}
