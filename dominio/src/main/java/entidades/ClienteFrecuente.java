/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entidades;

import java.io.Serializable;
import java.time.LocalDate;
import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

/**
 *
 * @author Benjamin
 */
@Entity
@Table(name = "clientes_empresariales")
@PrimaryKeyJoinColumn(name = "id_cliente")
@DiscriminatorValue("FRECUENTE")
public class ClienteFrecuente extends Cliente implements Serializable{
    
    
    @Column(name = "puntos_fidelidad")
    private Double puntosFidelidad;

    @Column(name = "numero_visitas")
    private Integer numeroVisitas;

    public ClienteFrecuente() {
    }

    public ClienteFrecuente(Double puntosFidelidad, Integer numeroVisitas, String nombre, String apellidoPaterno, String apellidoMaterno, String telefono, LocalDate fechaRegistro, String correoElectronico, Double gastoTotalAcumulado) {
        super(nombre, apellidoPaterno, apellidoMaterno, telefono, fechaRegistro, correoElectronico, gastoTotalAcumulado);
        this.puntosFidelidad = puntosFidelidad;
        this.numeroVisitas = numeroVisitas;
    }
    //para pruebas
    public ClienteFrecuente(String nombre, String apellidoPaterno, String apellidoMaterno, String telefono, LocalDate fechaRegistro, Double puntosFidelidad) {
        // Llamamos al constructor de la clase padre (Cliente)
        super(nombre, apellidoPaterno, apellidoMaterno, telefono);
        // Seteamos los campos restantes que no están en el constructor padre
        this.setFechaRegistro(fechaRegistro); 
        this.puntosFidelidad = puntosFidelidad;
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
