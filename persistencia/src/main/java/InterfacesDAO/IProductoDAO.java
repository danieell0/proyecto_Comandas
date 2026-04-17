/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package InterfacesDAO;

import entidades.Producto;
import excepciones.PersistenciaException;
import java.util.List;

/**
 *
 * @author Jorge
 */
public interface IProductoDAO {
    // metodo para obtener los productos de la base de datos
    public List<Producto> obtenerProductos() throws PersistenciaException;
    // metodo para obtener un producto en base al id
    public Producto obtenerProductoPorId(Long id) throws PersistenciaException;
    // metodo para guardar un producto dentro de la bd
    public void guardarProducto(Producto producto) throws PersistenciaException;
    // metodo para actualizar un producto de la base de datos
    public void actualizarProducto(Producto producto) throws PersistenciaException;
    //metodo para eliminar un producto de la base de datos
    public void eliminarProducto(Long id) throws PersistenciaException;
    //metodo para filtrar productos en base a nombres, categorias, y estados
    public List<Producto> filtrarProductos(String nombre, String categoria, String estado) throws PersistenciaException;
    // metodo para comprobar si existe un producto dentro de la base de datos, regresa true o false
    public boolean existeProductoNombre(String nombre)throws PersistenciaException;

}
