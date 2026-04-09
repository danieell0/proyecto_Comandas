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
 * Clase que representa los detalles de un producto tomando en cuenta la relacion
 * con una comanda y sus atributos
 * @author Benjamin
 */
@Entity
@Table(name = "detalles_producto")
public class DetalleProducto implements Serializable{
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_detalle_producto")
    private Long id;
    
    /**
     * Instrucciones especiales del cliente para la cocina (ej. "Sin cebolla", "Término medio").
     * Es opcional, por lo que no lleva 'nullable = false'.
     */
    @Column(name = "comentario_comanda")
    private String comentarioComanda;

    /**
     * Número de unidades de este producto solicitadas en la comanda.
     */
    @Column(name = "cantidad", nullable = false)
    private Integer cantidad;

    /**
     * Precio unitario del producto congelado al momento de la venta.
     * Evita que cambios futuros en el precio del menú alteren el total de comandas pasadas.
     */
    @Column(name = "precio_unitario", nullable = false)
    private Double precio;

    /**
     * Resultado de multiplicar la cantidad por el precio unitario.
     * Aunque puede calcularse al vuelo, guardarlo en BD acelera la generación de reportes financieros.
     */
    @Column(name = "subtotal", nullable = false)
    private Double subtotal;

    /**
     * Relación Muchos a Uno con la Comanda.
     * Muchos 'Detalles' pertenecen a una sola 'Comanda'.
     */
    @ManyToOne(optional = false)
    @JoinColumn(name = "id_comanda", nullable = false)
    private Comanda comanda;

    /**
     * Relación Muchos a Uno con el Producto.
     * Muchos 'Detalles' pueden referenciar al mismo 'Producto' del menú.
     */
    @ManyToOne(optional = false)
    @JoinColumn(name = "id_producto", nullable = false)
    private Producto producto;

    public DetalleProducto() {
    }

    public DetalleProducto(Long id, String comentarioComanda, Integer cantidad, Double precio, Double subtotal, Comanda comanda, Producto producto) {
        this.id = id;
        this.comentarioComanda = comentarioComanda;
        this.cantidad = cantidad;
        this.precio = precio;
        this.subtotal = subtotal;
        this.comanda = comanda;
        this.producto = producto;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getComentarioComanda() {
        return comentarioComanda;
    }

    public void setComentarioComanda(String comentarioComanda) {
        this.comentarioComanda = comentarioComanda;
    }

    public Integer getCantidad() {
        return cantidad;
    }

    public void setCantidad(Integer cantidad) {
        this.cantidad = cantidad;
    }

    public Double getPrecio() {
        return precio;
    }

    public void setPrecio(Double precio) {
        this.precio = precio;
    }

    public Double getSubtotal() {
        return subtotal;
    }

    public void setSubtotal(Double subtotal) {
        this.subtotal = subtotal;
    }

    public Comanda getComanda() {
        return comanda;
    }

    public void setComanda(Comanda comanda) {
        this.comanda = comanda;
    }

    public Producto getProducto() {
        return producto;
    }

    public void setProducto(Producto producto) {
        this.producto = producto;
    }
    
    
}
