/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Mappers;

import dto.ProductoDTO;
import entidades.Producto;

/**
 *
 * @author Jorge
 */
public class ProductoMapper {
    
    //convierte de entidad a dto
    public static ProductoDTO toDTO(Producto p){
        if(p==null){
            return null;
        }
        ProductoDTO dto=new ProductoDTO();
        dto.setId(p.getId());
        dto.setNombre(p.getNombre());
        dto.setPrecio(p.getPrecio());
        dto.setTipo(p.getTipo());
        dto.setEstado(p.getEstado());
        dto.setRutaImagen(p.getRutaImagen());
        return dto;
    }
    
    //convierte de dto a entidad
    public static Producto toEntity(ProductoDTO dto) {
        if (dto == null) {
            return null;
        }
        Producto p = new Producto();
        p.setId(dto.getId());
        p.setNombre(dto.getNombre());
        p.setPrecio(dto.getPrecio());
        p.setTipo(dto.getTipo());
        p.setEstado(dto.getEstado());
        p.setRutaImagen(dto.getRutaImagen());
        return p;
    }
    
}
