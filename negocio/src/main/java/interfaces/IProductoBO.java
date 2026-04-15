/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package interfaces;

import dto.ProductoDTO;
import excepciones.NegocioExcepcion;
import java.util.List;

/**
 *
 * @author Jorge
 */
public interface IProductoBO {

    public List<ProductoDTO> obtenerProductos() throws NegocioExcepcion;

    public ProductoDTO obtenerProductoPorId(Long id) throws NegocioExcepcion;

    public void guardarProducto(ProductoDTO dto) throws NegocioExcepcion;

    public void actualizarProducto(ProductoDTO dto) throws NegocioExcepcion;

    public void cambiarEstadoProducto(Long id) throws NegocioExcepcion;
    
    public void eliminarProducto(Long id) throws NegocioExcepcion;
    
    public List<ProductoDTO> filtrarProductos(String nombre, String categoria, String estado) throws NegocioExcepcion;
}
