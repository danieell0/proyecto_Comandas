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
    
    //Metodo para agregar un ingrediente en la bd
    void agregarIngrediente(Ingrediente ingrediente) throws PersistenciaException;
    //metodo para comprobar si existe un ingrediente en la bd 
    boolean existeIngrediente(String nombre, String unidad);
    // metodo para consultar todos los ingredientes dentro de una lista
    List<Ingrediente> consultarTodos() throws PersistenciaException;
    //metodo para actualizar el stock de un ingrediente
    void actualizarStock(Long id, Double nuevoStock) throws PersistenciaException;
    //metodo para eliminar un ingrediente de la bd
    void borrarIngrediente(Long id) throws PersistenciaException;
    //metodo para buscar ingredientes en base al nombre y la unidad de medida
    public java.util.List<Ingrediente> buscarIngredientes(String nombre, String unidad) throws PersistenciaException;
    //metodo para obtener ingredientes de la base de datos 
    public List<Ingrediente> obtenerIngredientes() throws PersistenciaException;
    //metodo para obtener buscar un ingrediente por nombre en la base de datos
    public List<Ingrediente> buscarPorNombre(String nombre) throws PersistenciaException;
    
}
