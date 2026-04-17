/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package objetosNegocio;

import Enums.EstadoProducto;
import static Enums.EstadoProducto.ACTIVO;
import InterfacesDAO.IProductoDAO;
import Mappers.ProductoMapper;
import dto.ProductoDTO;
import entidades.Producto;
import excepciones.NegocioExcepcion;
import excepciones.PersistenciaException;
import interfaces.IProductoBO;
import java.util.AbstractList;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import validadores.ValidarCamposProductos;

/**
 * Clase de Objeto de Negocio (Business Object) para la gestión de Productos.
 * Contiene toda la lógica y reglas de negocio aplicables a los productos antes 
 * de interactuar con la capa de persistencia (DAO).
 */
public class ProductoBO implements IProductoBO {

    private IProductoDAO productoDAO;
    private static final Logger logger = Logger.getLogger(ProductoBO.class.getName());

    /**
     * Constructor que inyecta la dependencia del DAO.
     * @param productoDAO Implementación de la interfaz de acceso a datos de productos.
     */
    public ProductoBO(IProductoDAO productoDAO) {
        this.productoDAO = productoDAO;
    }

    /**
     * Obtiene una lista con todos los productos registrados en el sistema.
     * @return Lista de objetos {@link ProductoDTO}.
     * @throws NegocioExcepcion Si ocurre un error al consultar la capa de persistencia.
     */
    @Override
    public List<ProductoDTO> obtenerProductos() throws NegocioExcepcion {
        try {
            logger.info("Obteniendo productos en BO");
            List<Producto> lista = productoDAO.obtenerProductos();
            return lista.stream().map(p -> ProductoMapper.toDTO(p)).toList();
        } catch (PersistenciaException ex) {
            logger.log(Level.SEVERE, "Error en el BO al obtener la lista de productos", ex);
            throw new NegocioExcepcion("Error en el BO al obtener la lista de productos", ex);
        }
    }

    /**
     * Busca un producto específico mediante su identificador único.
     * @param id El identificador numérico del producto.
     * @return Un objeto {@link ProductoDTO} con los datos del producto.
     * @throws NegocioExcepcion Si el ID es inválido o ocurre un error en la base de datos.
     */
    @Override
    public ProductoDTO obtenerProductoPorId(Long id) throws NegocioExcepcion {
        try {
            if (id == null || id <= 0) {
                throw new NegocioExcepcion("El id en la BO no puede ser nulo o menor e igual que 0");
            }
            Producto producto = productoDAO.obtenerProductoPorId(id);
            return ProductoMapper.toDTO(producto);
        } catch (NegocioExcepcion q) {
            throw q;
        } catch (PersistenciaException e) {
            logger.log(Level.SEVERE, "Error en la BO al obtener el producto", e);
            throw new NegocioExcepcion("Error en la BO al obtener el producto", e);
        }
    }

    /**
     * Valida y guarda un nuevo producto en el sistema.
     * Aplica validaciones de formato y verifica que el nombre no esté duplicado.
     * @param dto El objeto con los datos del nuevo producto a guardar.
     * @throws NegocioExcepcion Si los datos son inválidos, el nombre ya existe o hay un error de guardado.
     */
    @Override
    public void guardarProducto(ProductoDTO dto) throws NegocioExcepcion {
        try {
            validarDatos(dto); // Solo se llama una vez ahora
            
            if (productoDAO.existeProductoNombre(dto.getNombre())) {
                throw new NegocioExcepcion("Ya existe un producto con ese nombre");
            }
            
            Producto pro = ProductoMapper.toEntity(dto);
            productoDAO.guardarProducto(pro);
        } catch (PersistenciaException e) {
            logger.log(Level.SEVERE, "Error al guardar el producto en la BO", e);
            throw new NegocioExcepcion("Error al guardar el producto en la BO", e);
        }
    }

    /**
     * Valida y actualiza la información de un producto existente.
     * @param dto El objeto con los datos actualizados del producto.
     * @throws NegocioExcepcion Si los datos son inválidos o falla la actualización.
     */
    @Override
    public void actualizarProducto(ProductoDTO dto) throws NegocioExcepcion {
        try {
            validarDatos(dto);
            Producto producto = ProductoMapper.toEntity(dto);
            productoDAO.actualizarProducto(producto);
            logger.info("Producto actualizado correctamente");
        } catch (PersistenciaException e) {
            logger.log(Level.SEVERE, "Error al actualizar el producto en la BO", e);
            throw new NegocioExcepcion("Error al actualizar el producto en la BO", e);
        }
    }

    /**
     * Alterna el estado de un producto (de ACTIVO a INACTIVO y viceversa).
     * @param id El identificador del producto a modificar.
     * @throws NegocioExcepcion Si el ID es inválido o falla la conexión.
     */
    @Override
    public void cambiarEstadoProducto(Long id) throws NegocioExcepcion {
        try {
            if (id == null || id <= 0) {
                throw new NegocioExcepcion("El id del producto es inválido");
            }
            Producto producto = productoDAO.obtenerProductoPorId(id);

            if (producto.getEstado() == EstadoProducto.ACTIVO) {
                producto.setEstado(EstadoProducto.INACTIVO);
            } else {
                producto.setEstado(EstadoProducto.ACTIVO);
            }

            productoDAO.actualizarProducto(producto);
            logger.info("Estado actualizado correctamente");
        } catch (PersistenciaException e) {
            logger.log(Level.SEVERE, "Error al actualizar el estado del producto en la BO", e);
            throw new NegocioExcepcion("Error al actualizar el estado del producto en la BO", e);
        }
    }

    /**
     * Busca productos en el sistema aplicando múltiples filtros opcionales.
     * @param nombre Texto parcial o completo del nombre del producto.
     * @param categoria Categoría o tipo de producto.
     * @param estado Estado del producto (ej. ACTIVO, INACTIVO).
     * @return Lista de {@link ProductoDTO} que coinciden con los criterios.
     * @throws NegocioExcepcion Si falla la consulta en la capa de datos.
     */
    @Override
    public List<ProductoDTO> filtrarProductos(String nombre, String categoria, String estado) throws NegocioExcepcion {
        try {
            if (nombre != null) {
                nombre = nombre.trim();
            }

            List<Producto> productos = productoDAO.filtrarProductos(nombre, categoria, estado);
            List<ProductoDTO> listaDTO = new ArrayList<>();

            for (Producto x : productos) {
                ProductoDTO pro = ProductoMapper.toDTO(x);
                listaDTO.add(pro);
            }
            return listaDTO;
        } catch (PersistenciaException e) {
            logger.log(Level.SEVERE, "Error al filtrar los productos en la BO", e);
            throw new NegocioExcepcion("Error al filtrar los productos en la BO", e);
        }
    }

    /**
     * Elimina físicamente un producto del sistema mediante su ID.
     * @param id Identificador del producto a eliminar.
     * @throws NegocioExcepcion Si el ID es inválido o falla la eliminación en BD.
     */
    @Override
    public void eliminarProducto(Long id) throws NegocioExcepcion {
        try {
            if (id == null || id <= 0) {
                throw new NegocioExcepcion("El id del producto es inválido");
            }
            productoDAO.eliminarProducto(id);
            logger.info("Producto eliminado correctamente");
        } catch (PersistenciaException e) {
            logger.log(Level.SEVERE, "Error en la BO al eliminar el producto", e);
            throw new NegocioExcepcion("Error al eliminar el producto", e);
        }
    }

    /**
     * Verifica que todos los campos obligatorios de un producto cumplan con las 
     * reglas de negocio establecidas antes de procesarlos.
     * @param producto Objeto DTO a validar.
     * @throws NegocioExcepcion Si alguno de los campos no cumple con el formato o está ausente.
     */
    public void validarDatos(ProductoDTO producto) throws NegocioExcepcion {
        if (producto == null) {
            throw new NegocioExcepcion("El producto no puede estar nulo");
        }

        if (producto.getNombre() == null || producto.getNombre().trim().isEmpty()) {
            throw new NegocioExcepcion("El nombre es obligatorio");
        }

        if (!ValidarCamposProductos.nombre(producto.getNombre())) {
            throw new NegocioExcepcion("Nombre invalido");
        }

        if (producto.getDescripcion() == null || producto.getDescripcion().trim().isEmpty()) {
            throw new NegocioExcepcion("La descripcion es obligatoria");
        }

        if (!ValidarCamposProductos.descripcion(producto.getDescripcion())) {
            throw new NegocioExcepcion("Descripcion invalida");
        }

        if (!ValidarCamposProductos.precio(producto.getPrecio())) {
            throw new NegocioExcepcion("Precion invalido"); // *Nota: Corregí el error de dedo "Precion"
        }

        if (producto.getTipo() == null) {
            throw new NegocioExcepcion("Debe seleccionar una categoría");
        }

        if (producto.getReceta() == null || producto.getReceta().isEmpty()) {
            throw new NegocioExcepcion("Debe agregar al menos un ingrediente");
        }
    }
}
