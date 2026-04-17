/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Mappers;

import dto.IngredienteSeleccionadoDTO;
import dto.ProductoDTO;
import entidades.DetalleReceta;
import entidades.Ingrediente;
import entidades.Producto;

/**
 * Clase utilitaria encargada de mapear (convertir) objetos de la entidad {@link Producto}
 * a su correspondiente Data Transfer Object {@link ProductoDTO} y viceversa.
 * * Además de trasladar los atributos básicos, esta clase maneja la lógica de conversión 
 * para la receta del producto, enlazando los ingredientes seleccionados con sus detalles.
 */
public class ProductoMapper {

    /**
     * Convierte una entidad {@link Producto} proveniente de la base de datos 
     * en un objeto {@link ProductoDTO} para su uso seguro en la capa de presentación.
     * * @param p El objeto de tipo entidad Producto que se desea convertir.
     * @return Un nuevo objeto ProductoDTO con los datos mapeados, o {@code null} si la entidad de entrada es nula.
     */
    public static ProductoDTO toDTO(Producto p) {
        if (p == null) {
            return null;
        }
        ProductoDTO dto = new ProductoDTO();
        dto.setId(p.getId());
        dto.setNombre(p.getNombre());
        dto.setPrecio(p.getPrecio());
        dto.setTipo(p.getTipo());
        dto.setEstado(p.getEstado());
        dto.setRutaImagen(p.getRutaImagen());
        return dto;
    }

    /**
     * Convierte un Data Transfer Object {@link ProductoDTO} proveniente de la vista 
     * en una entidad {@link Producto} lista para ser guardada o procesada por JPA.
     * * Este método también inspecciona la lista de ingredientes seleccionados (la receta). 
     * Si contiene elementos, crea los objetos {@link DetalleReceta} y los asocia 
     * automáticamente a la entidad del producto resultante.
     * * @param dto El objeto de transferencia de datos que contiene la información capturada.
     * @return Una nueva entidad Producto con los datos básicos y su receta construida, o {@code null} si el DTO es nulo.
     */
    public static Producto toEntity(ProductoDTO dto) {
        if (dto == null) {
            return null;
        }
        Producto p = new Producto();
        p.setId(dto.getId());
        p.setNombre(dto.getNombre());
        p.setPrecio(dto.getPrecio());
        p.setDescripcion(dto.getDescripcion());
        p.setTipo(dto.getTipo());
        p.setEstado(dto.getEstado());
        p.setRutaImagen(dto.getRutaImagen());

        if (dto.getReceta() != null) {
            for (IngredienteSeleccionadoDTO x : dto.getReceta()) {
                DetalleReceta detalle = new DetalleReceta();
                detalle.setCantidadRequerida(x.getCantidad());
                Ingrediente ingrediente=new Ingrediente();
                ingrediente.setId(x.getId());
                detalle.setIngrediente(ingrediente);
                ingrediente.setNombre(x.getNombre());
                p.agregarIngrediente(detalle);
            }
        }
        return p;
    }

}
