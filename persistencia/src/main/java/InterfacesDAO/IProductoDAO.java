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

    public List<Producto> obtenerProductos() throws PersistenciaException;

    public Producto obtenerProductoPorId(Long id) throws PersistenciaException;

    public void guardarProducto(Producto producto) throws PersistenciaException;

    public void actualizarProducto(Producto producto) throws PersistenciaException;
    
    public void eliminarProducto(Long id) throws PersistenciaException;
    
    public List<Producto> filtrarProductos(String nombre, String categoria, String estado) throws PersistenciaException;

}
