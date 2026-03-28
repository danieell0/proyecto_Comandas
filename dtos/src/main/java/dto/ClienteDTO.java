/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dto;

import java.time.LocalDate;

/**
 * Objeto de transferencia de datos que representa a un cliente frecuente 
 * que actua como un contenedor seguro para la transferencia de datos a traves
 * del sistema 
 * @author munos
 */
public class ClienteDTO {
    /**
     * Identifiacdor unico del cliente 
     */
    private Long id;
    private String nombre;
    private String apellidoPaterno;
    private String apellidoMaterno;
    private String telefono;
    private String correoElectronico;
    private LocalDate fechaRegistro;
    /**
     * Puntos de fidelidad acumulados de compras anteriores 
     */
    private Double puntosFidelidad;
    /**
     * Numero de visitas totales 
     */
    private Integer numeroVisitas;

    public ClienteDTO() {
    
    }

    /**
     * Constructor utilizado para poblar el dto con toda la informacion de un 
     * cliente frecuente y trasladarla a traves del sistema con seguridad
     */
    public ClienteDTO(Long id, String nombre, String apellidoPaterno, String apellidoMaterno, String telefono, String correoElectronico, LocalDate fechaRegistro, Double puntosFidelidad, Integer numeroVisitas) {
        this.id = id;
        this.nombre = nombre;
        this.apellidoPaterno = apellidoPaterno;
        this.apellidoMaterno = apellidoMaterno;
        this.telefono = telefono;
        this.correoElectronico = correoElectronico;
        this.fechaRegistro = fechaRegistro;
        this.puntosFidelidad = puntosFidelidad;
        this.numeroVisitas = numeroVisitas;
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

    public String getCorreoElectronico() {
        return correoElectronico;
    }

    public void setCorreoElectronico(String correoElectronico) {
        this.correoElectronico = correoElectronico;
    }

    public LocalDate getFechaRegistro() {
        return fechaRegistro;
    }

    public void setFechaRegistro(LocalDate fechaRegistro) {
        this.fechaRegistro = fechaRegistro;
    }

    public Double getPuntosFidelidad() {
        return puntosFidelidad;
    }

    public void setPuntosFidelidad(Double puntosFidelidad) {
        this.puntosFidelidad = puntosFidelidad;
    }

    public Integer getNumeroVisitas() {
        return numeroVisitas;
    }

    public void setNumeroVisitas(Integer numeroVisitas) {
        this.numeroVisitas = numeroVisitas;
    }
}