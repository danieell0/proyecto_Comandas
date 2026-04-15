/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import Enums.EstadoProducto;
import Enums.TipoProducto;
import InterfacesDAO.IProductoDAO;
import conexion.ConexionBD;
import entidades.Producto;
import excepciones.PersistenciaException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

/**
 *
 * @author Jorge
 */
public class ProductoDAO implements IProductoDAO {

    private static final Logger logger = Logger.getLogger(ProductoDAO.class.getName());

    @Override
    public List<Producto> obtenerProductos() throws PersistenciaException {
        EntityManager em = ConexionBD.crearConexion();
        try {
            String comandoJPQL = """
                                 SELECT p FROM Producto p
                               """;
            TypedQuery<Producto> query = em.createQuery(comandoJPQL, Producto.class);
            return query.getResultList();
        } catch (Exception e) {
            logger.log(Level.SEVERE, "Error al obtener los productos", e);
            throw new PersistenciaException("Error al obtener los productos", e);
        } finally {
            em.close();
        }
    }

    @Override
    public Producto obtenerProductoPorId(Long id) throws PersistenciaException {
        EntityManager em = ConexionBD.crearConexion();
        try {
            if (id == null || id <= 0) {
                throw new PersistenciaException("Error, el id no puede ser nulo o ser meno e igual que 0.");
            }
            Producto producto = em.find(Producto.class, id);
            if (producto == null) {
                throw new PersistenciaException("Error, no se encontro el producto con ese id");
            }
            return producto;
        } catch (PersistenciaException q) {
            throw q;
        } catch (Exception e) {
            logger.log(Level.SEVERE, "Error al obtener el producto con el id: " + id, e);
            throw new PersistenciaException("Error al obtener el producto con el id: " + id, e);
        } finally {
            em.close();
        }
    }

    @Override
    public void guardarProducto(Producto producto) throws PersistenciaException {
        EntityManager em = ConexionBD.crearConexion();
        try {
            em.getTransaction().begin();
            em.persist(producto);
            em.getTransaction().commit();
        }catch (Exception e) {
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            logger.log(Level.SEVERE, "Error al Registrar el producto", e);
            throw new PersistenciaException("Error al registrar el producto", e);
        } finally {
            em.close();
        }
    }

    @Override
    public void actualizarProducto(Producto producto) throws PersistenciaException {
        EntityManager em = ConexionBD.crearConexion();
        try {
            if (producto == null || producto.getId() == null) {
                throw new PersistenciaException("Error, el producto o su id no puede estar vacio.");
            }
            em.getTransaction().begin();
            em.merge(producto);
            em.getTransaction().commit();
        } catch (PersistenciaException q) {
            throw q;
        } catch (Exception e) {
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            logger.log(Level.SEVERE, "Error al actualizar el producto", e);
            throw new PersistenciaException("Error al actualizar el producto", e);
        } finally {
            em.close();
        }
    }

    @Override
    public List<Producto> filtrarProductos(String nombre, String categoria, String estado) throws PersistenciaException {
        EntityManager em = ConexionBD.crearConexion();
        try {
            StringBuilder comandoJPQL = new StringBuilder("SELECT p FROM Producto p WHERE 1=1");

            if (nombre != null && !nombre.trim().isEmpty()) {
                comandoJPQL.append(" AND LOWER(p.nombre) LIKE LOWER(:nombre)");
            }

            if (categoria != null && !categoria.equals("Todas")) {
                comandoJPQL.append(" AND p.tipo =:categoria");
            }

            if (estado != null && !estado.equals("Todos")) {
                comandoJPQL.append(" AND p.estado =:estado");
            }
            TypedQuery<Producto> query = em.createQuery(comandoJPQL.toString(), Producto.class);

            if (nombre != null && !nombre.trim().isEmpty()) {
                query.setParameter("nombre", "%" + nombre + "%");
            }

            if (categoria != null && !"Todas".equalsIgnoreCase(categoria)) {
                query.setParameter("categoria", TipoProducto.valueOf(categoria.toUpperCase()));
            }

            if (estado != null && !"Todos".equalsIgnoreCase(estado)) {
                EstadoProducto estadoEnum = null;

                if ("Activo".equalsIgnoreCase(estado)) {
                    estadoEnum = EstadoProducto.ACTIVO;
                } else if ("Inactivo".equalsIgnoreCase(estado)) {
                    estadoEnum = EstadoProducto.INACTIVO;
                }
                query.setParameter("estado", estadoEnum);
            }

            return query.getResultList();
        } catch (Exception e) {
            logger.log(Level.SEVERE, "Error al filtrar los productos", e);
            throw new PersistenciaException("Error al filtrar los productos", e);
        } finally {
            em.close();
        }

    }

    @Override
    public void eliminarProducto(Long id) throws PersistenciaException {
        EntityManager em = ConexionBD.crearConexion();
        try {
            if (id == null || id <= 0) {
                throw new PersistenciaException("El id no puede ser nulo o menor/igual a 0");
            }
            Producto producto = em.find(Producto.class, id);
            if (producto == null) {
                throw new PersistenciaException("El producto no existe");
            }
            
            em.getTransaction().begin();
            String comandoJPQL = "DELETE FROM DetalleProducto d WHERE d.producto.id =:id";
            Query query = em.createQuery(comandoJPQL);
            query.setParameter("id", id);
            query.executeUpdate();
            
            em.remove(producto);
            em.getTransaction().commit();
        } catch (PersistenciaException q) {
            throw q;
        } catch (Exception e) {
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            logger.log(Level.SEVERE, "Error al eliminar el producto", e);
            throw new PersistenciaException("Error al eliminar el producto", e);
        } finally {
            em.close();
        }
    }

}
