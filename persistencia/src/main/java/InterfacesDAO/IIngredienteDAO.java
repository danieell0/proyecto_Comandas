/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package InterfacesDAO;

import entidades.Ingrediente;
import excepciones.PersistenciaException;
import java.util.List;

/**
 *
 * @author Jorge
 */
public interface IIngredienteDAO {
    
    public List<Ingrediente> obtenerIngredientes() throws PersistenciaException;
    
    public List<Ingrediente> buscarPorNombre(String nombre) throws PersistenciaException;
    
}
