/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entidades;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * Clase que representa los detalles de una receta es decir la relacion entre un 
 * producto y sus ingredientes necesarios para prepararlo asi como detalles de 
 * cada uno
 * @author Benjamin
 */
@Entity
@Table(name = "detalles_receta")
public class DetalleReceta implements Serializable{
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_detalle_receta")
    private Long id;
    
    /**
     * Cantidad exacta del ingrediente requerida para este producto.
     * La unidad de medida (ej. gramos, piezas) se obtiene de la entidad Ingrediente asociada.
     */
    @Column(name = "cantidad_requerida", nullable = false)
    private Double cantidadRequerida; // Usamos Double por si son gramos (ej. 1.5 kg)
    
    /**
     * Relación Muchos a Uno con Producto.
     * Muchos 'Detalles de Receta' pertenecen a un solo 'Producto'.
     */
    @ManyToOne(optional = false)
    @JoinColumn(name = "id_producto", nullable = false)
    private Producto producto;
    
    /**
     * Relación Muchos a Uno con Ingrediente.
     * Muchos 'Detalles de Receta' pueden usar el mismo 'Ingrediente'.
     */
    @ManyToOne(optional = false)
    @JoinColumn(name = "id_ingrediente", nullable = false)
    private Ingrediente ingrediente;
    
    // Constructores
    public DetalleReceta() {
    }

    public DetalleReceta(Double cantidadRequerida, Producto producto, Ingrediente ingrediente) {
        this.cantidadRequerida = cantidadRequerida;
        this.producto = producto;
        this.ingrediente = ingrediente;
    }
    
    // Getters y Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Double getCantidadRequerida() {
        return cantidadRequerida;
    }

    public void setCantidadRequerida(Double cantidadRequerida) {
        this.cantidadRequerida = cantidadRequerida;
    }

    public Producto getProducto() {
        return producto;
    }

    public void setProducto(Producto producto) {
        this.producto = producto;
    }

    public Ingrediente getIngrediente() {
        return ingrediente;
    }

    public void setIngrediente(Ingrediente ingrediente) {
        this.ingrediente = ingrediente;
    }
}
