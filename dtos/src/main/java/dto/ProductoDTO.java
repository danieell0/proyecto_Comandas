/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dto;

import Enums.EstadoProducto;
import Enums.TipoProducto;
import java.util.List;

/**
 *
 * @author Jorge
 */
public class ProductoDTO {
    
    private Long id;
    private String nombre; 
    private Double precio; 
    private TipoProducto tipo;
    private String descripcion;
    private EstadoProducto estado;
    private String rutaImagen;
    private List<IngredienteSeleccionadoDTO> receta;

    public ProductoDTO() {
    }
    
    public ProductoDTO(String nombre, Double precio, TipoProducto tipo, String descripcion, EstadoProducto estado, String rutaImagen, List<IngredienteSeleccionadoDTO> receta) {
        this.nombre = nombre;
        this.precio = precio;
        this.tipo = tipo;
        this.descripcion = descripcion;
        this.estado = estado;
        this.rutaImagen = rutaImagen;
        this.receta = receta;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Double getPrecio() {
        return precio;
    }

    public void setPrecio(Double precio) {
        this.precio = precio;
    }

    public TipoProducto getTipo() {
        return tipo;
    }

    public void setTipo(TipoProducto tipo) {
        this.tipo = tipo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public EstadoProducto getEstado() {
        return estado;
    }

    public void setEstado(EstadoProducto estado) {
        this.estado = estado;
    }

    public String getRutaImagen() {
        return rutaImagen;
    }

    public void setRutaImagen(String rutaImagen) {
        this.rutaImagen = rutaImagen;
    }

    public List<IngredienteSeleccionadoDTO> getReceta() {
        return receta;
    }

    public void setReceta(List<IngredienteSeleccionadoDTO> receta) {
        this.receta = receta;
    }
    
    
}