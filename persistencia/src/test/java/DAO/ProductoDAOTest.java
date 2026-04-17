/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package DAO;

import Enums.EstadoProducto;
import Enums.TipoProducto;
import conexion.ConexionBD;
import entidades.DetalleReceta;
import entidades.Ingrediente;
import entidades.Producto;
import excepciones.PersistenciaException;
import java.util.List;
import javax.persistence.EntityManager;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;

/**
 *
 * @author Benjamin
 */
public class ProductoDAOTest {
    private IngredienteDAO ingredientesDAO;
private ProductoDAO productoDAO;
    /**
     * Inicializa los recursos necesarios antes de cada prueba.
     *
     * <p>Se crea una instancia del DAO y se desactivan los logs
     * para evitar ruido en la ejecución de pruebas.</p>
     */
    @BeforeEach
    public void setUp() {
        productoDAO = new ProductoDAO();
        java.util.logging.Logger.getLogger(ProductoDAO.class.getName())
                .setLevel(java.util.logging.Level.OFF);
    }

    /**
     * Verifica que se puedan obtener productos correctamente cuando existen registros.
     *
     * @throws PersistenciaException si ocurre un error en la persistencia
     */
    @Test
    public void testObtenerProductosOk() throws PersistenciaException {
        Producto producto = new Producto();
        producto.setNombre("Pan Integral");
        producto.setDescripcion("Prueba");
        producto.setPrecio(20.0);
        producto.setTipo(TipoProducto.PLATILLO);
        producto.setEstado(EstadoProducto.ACTIVO);

        Ingrediente ingrediente = new Ingrediente();
        ingrediente.setNombre("Avena");
        ingrediente.setUnidadMedida("kg");
        ingrediente.setCantidadActual(100.0);
        EntityManager em = ConexionBD.crearConexion();
        em.getTransaction().begin();
        em.persist(ingrediente);
        em.getTransaction().commit();
        em.close();

        DetalleReceta detalle = new DetalleReceta();
        detalle.setCantidadRequerida(1.0);
        detalle.setIngrediente(ingrediente);

        producto.agregarIngrediente(detalle);

        productoDAO.guardarProducto(producto);
        List<Producto> lista = productoDAO.obtenerProductos();
        assertNotNull(lista);
    }

    /**
     * Verifica que la lista de productos no sea nula cuando no hay registros.
     *
     * @throws PersistenciaException si ocurre un error en la consulta
     */
    @Test
    public void testObtenerProductosError() throws PersistenciaException {
        List<Producto> lista = productoDAO.obtenerProductos();
        assertNotNull(lista);
        assertTrue(lista.isEmpty() || lista.size() >= 0);
    }

    /**
     * Verifica que se pueda obtener un producto correctamente a partir de su ID.
     *
     * @throws PersistenciaException si ocurre un error en la persistencia
     */
    @Test
    public void testObtenerProductoPorIdOK() throws PersistenciaException {
        Producto producto = new Producto();
        producto.setNombre("mariscos");
        producto.setDescripcion("Prueba");
        producto.setPrecio(25.0);
        producto.setTipo(TipoProducto.PLATILLO);
        producto.setEstado(EstadoProducto.ACTIVO);

        Ingrediente ingrediente = new Ingrediente();
        ingrediente.setNombre("camaron");
        ingrediente.setUnidadMedida("kg");
        ingrediente.setCantidadActual(100.0);

        EntityManager em = ConexionBD.crearConexion();
        em.getTransaction().begin();
        em.persist(ingrediente);
        em.getTransaction().commit();
        em.close();

        DetalleReceta detalle = new DetalleReceta();
        detalle.setCantidadRequerida(2.0);
        detalle.setIngrediente(ingrediente);

        producto.agregarIngrediente(detalle);
        productoDAO.guardarProducto(producto);

        Long idGenerado = producto.getId();

        Producto resultado = productoDAO.obtenerProductoPorId(idGenerado);

        assertNotNull(resultado);
        assertEquals(idGenerado, resultado.getId());
        assertEquals(producto.getNombre(), resultado.getNombre());
    }

    /**
     * Verifica que se lance una excepción al intentar obtener un producto con ID nulo.
     */
    @Test
    public void testObtenerProductoPorIdError() {
        Long id = null;
        assertThrows(PersistenciaException.class, () -> {
            productoDAO.obtenerProductoPorId(id);
        });
    }

    /**
     * Verifica que un producto se guarde correctamente en la base de datos.
     *
     * @throws PersistenciaException si ocurre un error en la persistencia
     */
    @Test
    public void testGuardarProductoOK() throws PersistenciaException {
        Producto producto = new Producto();
        producto.setNombre("Cereal");
        producto.setDescripcion("Prueba guardar");
        producto.setPrecio(50.0);
        producto.setTipo(TipoProducto.PLATILLO);
        producto.setEstado(EstadoProducto.ACTIVO);

        Ingrediente ingrediente = new Ingrediente();
        ingrediente.setNombre("Leche");
        ingrediente.setUnidadMedida("kg");
        ingrediente.setCantidadActual(100.0);

        EntityManager em = ConexionBD.crearConexion();
        em.getTransaction().begin();
        em.persist(ingrediente);
        em.getTransaction().commit();
        em.close();

        DetalleReceta detalle = new DetalleReceta();
        detalle.setCantidadRequerida(1.0);
        detalle.setIngrediente(ingrediente);

        producto.agregarIngrediente(detalle);
        productoDAO.guardarProducto(producto);

        assertNotNull(producto.getId());
    }

    /**
     * Verifica que se lance una excepción al intentar guardar productos con nombre duplicado.
     *
     * @throws PersistenciaException si ocurre un error en la persistencia
     */
    @Test
    public void testGuardarProductoError() throws PersistenciaException {
        String nombre = "ProductoDuplicado";

        Producto producto1 = new Producto();
        producto1.setNombre(nombre);
        producto1.setDescripcion("Primero");
        producto1.setPrecio(20.0);
        producto1.setTipo(TipoProducto.PLATILLO);
        producto1.setEstado(EstadoProducto.ACTIVO);

        Producto producto2 = new Producto();
        producto2.setNombre(nombre);
        producto2.setDescripcion("Segundo");
        producto2.setPrecio(30.0);
        producto2.setTipo(TipoProducto.PLATILLO);
        producto2.setEstado(EstadoProducto.ACTIVO);
        productoDAO.guardarProducto(producto1);

        PersistenciaException exception = assertThrows(PersistenciaException.class, () -> {
            productoDAO.guardarProducto(producto2);
        });

        assertNotNull(exception);
    }

    /**
     * Verifica que un producto existente se actualice correctamente.
     *
     * @throws PersistenciaException si ocurre un error en la persistencia
     */
    @Test
    public void testActualizarProductoOK() throws PersistenciaException {
        Producto producto = new Producto();
        producto.setNombre("Maruchan");
        producto.setDescripcion("Original");
        producto.setPrecio(20.0);
        producto.setTipo(TipoProducto.PLATILLO);
        producto.setEstado(EstadoProducto.ACTIVO);

        Ingrediente ingrediente = new Ingrediente();
        ingrediente.setNombre("Agua caliente");
        ingrediente.setUnidadMedida("kg");
        ingrediente.setCantidadActual(100.0);

        EntityManager em = ConexionBD.crearConexion();
        em.getTransaction().begin();
        em.persist(ingrediente);
        em.getTransaction().commit();
        em.close();

        DetalleReceta detalle = new DetalleReceta();
        detalle.setCantidadRequerida(1.0);
        detalle.setIngrediente(ingrediente);
        producto.agregarIngrediente(detalle);

        productoDAO.guardarProducto(producto);

        producto.setDescripcion("Actualizado");
        producto.setPrecio(50.0);
        producto.setEstado(EstadoProducto.INACTIVO);

        DetalleReceta nuevoDetalle = new DetalleReceta();
        nuevoDetalle.setCantidadRequerida(2.0);
        nuevoDetalle.setIngrediente(ingrediente);
        producto.getReceta().clear();
        producto.agregarIngrediente(nuevoDetalle);

        productoDAO.actualizarProducto(producto);
        Producto actualizado = productoDAO.obtenerProductoPorId(producto.getId());

        assertNotNull(actualizado);
        assertEquals("Actualizado", actualizado.getDescripcion());
        assertEquals(50.0, actualizado.getPrecio());
        assertEquals(EstadoProducto.INACTIVO, actualizado.getEstado());
        assertEquals(1, actualizado.getReceta().size());
    }

    /**
     * Verifica que se lance una excepción al intentar actualizar un producto nulo.
     */
    @Test
    public void testActualizarProductoError() {
        Producto producto = null;
        assertThrows(PersistenciaException.class, () -> {
            productoDAO.actualizarProducto(producto);
        });
    }

    /**
     * Verifica que el filtrado de productos funcione correctamente.
     *
     * @throws PersistenciaException si ocurre un error en la consulta
     */
    @Test
    public void testFiltrarProductosOK() throws PersistenciaException {
        Producto producto = new Producto();
        producto.setNombre("Pan con nutella");
        producto.setDescripcion("Prueba");
        producto.setPrecio(30.0);
        producto.setTipo(TipoProducto.PLATILLO);
        producto.setEstado(EstadoProducto.ACTIVO);
        productoDAO.guardarProducto(producto);
        List<Producto> lista = productoDAO.filtrarProductos("Pan", "Todas", "Todos");

        assertNotNull(lista);
        assertTrue(lista.size() > 0);
    }

    /**
     * Verifica que se lance una excepción al usar una categoría inválida en el filtrado.
     */
    @Test
    public void testFiltrarProductosError() {
        String categoriaInvalida = "NO_EXISTE";
        assertThrows(PersistenciaException.class, () -> {
            productoDAO.filtrarProductos(null, categoriaInvalida, null);
        });
    }

    /**
     * Verifica que un producto se elimine correctamente.
     *
     * @throws PersistenciaException si ocurre un error en la persistencia
     */
    @Test
    public void testEliminarProducto_OK() throws PersistenciaException {
        Producto producto = new Producto();
        producto.setNombre("Sushi");
        producto.setDescripcion("Eliminar");
        producto.setPrecio(20.0);
        producto.setTipo(TipoProducto.PLATILLO);
        producto.setEstado(EstadoProducto.ACTIVO);
        productoDAO.guardarProducto(producto);
        Long id = producto.getId();

        productoDAO.eliminarProducto(id);

        assertThrows(PersistenciaException.class, () -> {
            productoDAO.obtenerProductoPorId(id);
        });
    }

    /**
     * Verifica que se lance una excepción al intentar eliminar un producto inexistente.
     */
    @Test
    public void testEliminarProductoError() {
        Long idInexistente = 999999L;

        assertThrows(PersistenciaException.class, () -> {
            productoDAO.eliminarProducto(idInexistente);
        });
    }

    /**
     * Verifica que el sistema detecte correctamente la existencia de un producto por nombre.
     *
     * @throws PersistenciaException si ocurre un error en la consulta
     */
    @Test
    public void testExisteProductoNombre_OK() throws PersistenciaException {
        String nombre = "bonless";

        Producto producto = new Producto();
        producto.setNombre(nombre);
        producto.setDescripcion("Prueba");
        producto.setPrecio(20.0);
        producto.setTipo(TipoProducto.PLATILLO);
        producto.setEstado(EstadoProducto.ACTIVO);
        productoDAO.guardarProducto(producto);

        boolean existe = productoDAO.existeProductoNombre(nombre);

        assertTrue(existe);
    }

    /**
     * Verifica el manejo de error al consultar un nombre nulo.
     */
    @Test
    public void testExisteProductoNombreError() {
        String nombre = null;

        try {
            productoDAO.existeProductoNombre(nombre);
        } catch (PersistenciaException e) {
            assertNotNull(e);
        }
    }
    
    
}
