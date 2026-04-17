/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package InterfacesDAO;

import entidades.Ingrediente;
import excepciones.PersistenciaException;
import java.util.List;

/**
 * Interfaz para el data acces object de ingrediente 
 * @author Benjamin
 */
public interface IIngredienteDAO {
    
    void agregarIngrediente(Ingrediente ingrediente) throws PersistenciaException;
    
    boolean existeIngrediente(String nombre, String unidad);
    
    List<Ingrediente> consultarTodos() throws PersistenciaException;
    
    void actualizarStock(Long id, Double nuevoStock) throws PersistenciaException;
    
    void borrarIngrediente(Long id) throws PersistenciaException;
    
    public java.util.List<Ingrediente> buscarIngredientes(String nombre, String unidad) throws PersistenciaException;
    
}
