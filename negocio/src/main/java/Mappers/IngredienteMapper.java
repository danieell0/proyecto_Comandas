/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Mappers;

import dto.IngredienteDTO;
import entidades.Ingrediente;

/**
 *
 * @author Jorge
 */
public class IngredienteMapper {
    
    public static IngredienteDTO toDTO(Ingrediente i){
       if(i==null){
           return null;
       } 
       IngredienteDTO dto=new IngredienteDTO();
       dto.setId(i.getId());
       dto.setNombre(i.getNombre());
       dto.setUnidadMedida(i.getUnidadMedida());
       return dto;
    }
    
    public static Ingrediente toEntity(IngredienteDTO dto){
        if(dto==null){
            return null;
        }
        Ingrediente i=new Ingrediente();
        i.setId(dto.getId());
        i.setNombre(dto.getNombre());
        i.setUnidadMedida(dto.getUnidadMedida());
        return i;
    }
    
}
