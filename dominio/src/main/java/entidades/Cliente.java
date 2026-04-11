/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entidades;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * La clase 'Cliente' representa a las personas que compran en el restaurante,
 * es decir estan involucrados con una comanda, y almacenamos la informacion de
 * estos que nos permite un mejor servicio al cliente y control de sus
 * operaciones involucradas
 *
 * Es una entidad JPA que se mapea a la table clientes en la base de datos
 *
 * @author Benjamin
 */
@Entity
@Table(name = "clientes")
@Inheritance(strategy = InheritanceType.JOINED) //estrategia de tabla por subclase
@DiscriminatorColumn(name = "tipo_cliente") //columna para diferenciar el tipo
public class Cliente implements Serializable {

    /**
     * Id del cliente, diferenciador numerico unico
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_cliente")
    private Long id;

    /**
     * Nombre del cliente
     */
    @Column(name = "nombre", nullable = false, length = 100)
    private String nombre;

    /**
     * Apellido paterno
     */
    @Column(name = "apellido_paterno", length = 100)
    private String apellidoPaterno;

    /**
     * Apellido materno
     */
    @Column(name = "apellido_materno", length = 100)
    private String apellidoMaterno;

    /**
     * Telefono del cliente
     */
    @Column(name = "telefono", length = 20, nullable = false)
    private String telefono;

    /**
     * Correo electronico
     */
    @Column(name = "correo_electronico", length = 100, nullable = true)   //ES OPCIONAL
    private String correoElectronico;

    /**
     * Fecha de registro del cliente 
     */
    @Column(name = "fecha_registro")
    private LocalDate fechaRegistro;

    /**
     * Relación Uno a Muchos con Comanda.
     * Un cliente puede tener un historial de muchas comandas.
     */
    @OneToMany(mappedBy = "cliente")
    private List<Comanda> comandas;
    
    /**
     * Constructor por omision
     */
    public Cliente() {
        
    }

    /**
     * Constructor con todos los datos
     */
    public Cliente(String nombre, String apellidoPaterno, String apellidoMaterno, String telefono, LocalDate fechaRegistro, String correoElectronico) {
        this.nombre = nombre;
        this.apellidoPaterno = apellidoPaterno;
        this.apellidoMaterno = apellidoMaterno;
        this.telefono = telefono;
        this.fechaRegistro = fechaRegistro;
        this.correoElectronico = correoElectronico;
    }

    public Cliente(String nombre, String apellidoPaterno, String apellidoMaterno, String telefono) {
        this.nombre = nombre;
        this.apellidoPaterno = apellidoPaterno;
        this.apellidoMaterno = apellidoMaterno;
        this.telefono = telefono;
    }

    public List<Comanda> getComandas() {
        return comandas;
    }

    public void setComandas(List<Comanda> comandas) {
        this.comandas = comandas;
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

    public String getApellidoPaterno() {
        return apellidoPaterno;
    }

    public void setApellidoPaterno(String apellidoPaterno) {
        this.apellidoPaterno = apellidoPaterno;
    }

    public String getApellidoMaterno() {
        return apellidoMaterno;
    }

    public void setApellidoMaterno(String apellidoMaterno) {
        this.apellidoMaterno = apellidoMaterno;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public LocalDate getFechaRegistro() {
        return fechaRegistro;
    }

    public void setFechaRegistro(LocalDate fechaRegistro) {
        this.fechaRegistro = fechaRegistro;
    }

    public String getCorreoElectronico() {
        return correoElectronico;
    }

    public void setCorreoElectronico(String correoElectronico) {
        this.correoElectronico = correoElectronico;
    }

}
