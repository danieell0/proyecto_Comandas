/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package interfaces;

import dto.IngredienteDTO;
import excepciones.NegocioExcepcion;
import java.util.List;

/**
 *
 * @author Benjamin
 */
public interface IIngredienteBO {

    public void agregarIngrediente(IngredienteDTO dto) throws NegocioExcepcion;
    
    public List<IngredienteDTO> obtenerTodosLosIngredientes() throws NegocioExcepcion;
    
    void eliminarIngrediente(Long id) throws NegocioExcepcion;
    
    public java.util.List<IngredienteDTO> buscarIngredientes(String nombre, String unidad) throws NegocioExcepcion;
    
    public void actualizarStock(Long id, Double nuevoStock) throws NegocioExcepcion;
    
    public List<IngredienteDTO> obtenerIngredientes() throws NegocioExcepcion;

    public List<IngredienteDTO> buscarPorNombre(String nombre) throws NegocioExcepcion;
    
}
