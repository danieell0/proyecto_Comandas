/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Mappers;

import dto.IngredienteDTO;
import entidades.Ingrediente;

/**
 * Clase utilitaria encargada de mapear (convertir) objetos de la entidad {@link Ingrediente}
 * a su correspondiente Data Transfer Object {@link IngredienteDTO} y viceversa.
 * * Facilita el traslado seguro de información entre las capas de acceso a datos y la interfaz de usuario.
 */
public class IngredienteMapper {
    
    /**
     * Convierte una entidad {@link Ingrediente} proveniente de la base de datos 
     * en un objeto {@link IngredienteDTO} para su uso en la capa de presentación.
     * * @param i El objeto de tipo entidad Ingrediente que se desea convertir.
     * @return Un nuevo objeto IngredienteDTO con los datos mapeados, o {@code null} si la entidad de entrada es nula.
     */
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
    
    /**
     * Convierte un Data Transfer Object {@link IngredienteDTO} proveniente de la vista 
     * en una entidad {@link Ingrediente} lista para ser procesada por JPA/Base de datos.
     * * @param dto El objeto de transferencia de datos que contiene la información capturada.
     * @return Una nueva entidad Ingrediente con los datos asignados, o {@code null} si el DTO de entrada es nulo.
     */
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
