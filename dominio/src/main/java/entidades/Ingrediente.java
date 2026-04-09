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
import javax.persistence.Table;

/**
 * Clase que representa un ingrediente que puede ser usado en un producto
 * con sus atributos unicos 
 * 
 * - id: Long
- nombre: String
- unidadMedida: String
- cantidadActual: Double

 * @author Benjamin
 */
@Entity
@Table(name = "ingredientes")
public class Ingrediente implements Serializable{
    
    /**
     * Id del cliente, diferenciador numerico unico
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_ingrediente")
    private Long id;
    
    /**
     * Nombre del ingrediente
     */
    @Column(name = "nombre")
    private String nombre;
    
    @Column(name = "unidad_medida")
    private String unidadMedida;
    
    @Column(name = "cantidad_actual")
    private Double cantidadActual;
    
    /**
     * Constructor por omision
     */
    public Ingrediente(){}

    /**
     * Constructor con todos los atributos
     */
    public Ingrediente(Long id, String nombre, String unidadMedida, Double cantidadActual) {
        this.id = id;
        this.nombre = nombre;
        this.unidadMedida = unidadMedida;
        this.cantidadActual = cantidadActual;
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

    public String getUnidadMedida() {
        return unidadMedida;
    }

    public void setUnidadMedida(String unidadMedida) {
        this.unidadMedida = unidadMedida;
    }

    public Double getCantidadActual() {
        return cantidadActual;
    }

    public void setCantidadActual(Double cantidadActual) {
        this.cantidadActual = cantidadActual;
    }
    
    
    
}
