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
 * @author Jorge
 */
public interface IIngredienteBO {

    public List<IngredienteDTO> obtenerIngredientes() throws NegocioExcepcion;

    public List<IngredienteDTO> buscarPorNombre(String nombre) throws NegocioExcepcion;
    
}
