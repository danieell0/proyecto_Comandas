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
 * Esta clase representa a el tipo de cliente frecuente que se registra en el
 * sistema para poder llevar control de recompensas y estadisticas que se toman
 * de este tipo de clientes como puntos de fidelidad, numero de visitas y total
 * gastado,
 *
 * -----------------------------------------------------------------------------
 * Cada cliente podrá estar vinculado a múltiples comandas. Con base en ese
 * historial, el sistema realizará automáticamente el conteo de visitas, el
 * gasto total acumulado y calculará un sistema de puntos de fidelidad, donde
 * cada 20 pesos gastados equivaldrá a un punto.
 * -----------------------------------------------------------------------------
 *
 * Es una entidad JPA que se mapea a la tabla clientes_frecuentes en la base de
 * datos
 *
 * @author Benjamin
 */
@Entity
@Table(name = "clientes_frecuentes") //nombre de la tabla
@PrimaryKeyJoinColumn(name = "id_cliente") // atributo que hereda de la clase cliente y es el id
@DiscriminatorValue("FRECUENTE") //tipo de cliente (valor de el DiscriminatorColumn)
public class ClienteFrecuente extends Cliente implements Serializable {

    @Column(name = "puntos_fidelidad", nullable = false)
    private Double puntosFidelidad;

    @Column(name = "numero_visitas", nullable = false)
    private Integer numeroVisitas;

    @Column(name = "gasto_total_acumulado")
    private Double gastoTotalAcumulado;
    
    public ClienteFrecuente() {
    }

    public ClienteFrecuente(Double puntosFidelidad, Integer numeroVisitas, Double gastoTotalAcumulado) {
        this.puntosFidelidad = puntosFidelidad;
        this.numeroVisitas = numeroVisitas;
        this.gastoTotalAcumulado = gastoTotalAcumulado;
    }

    public ClienteFrecuente(Double puntosFidelidad, Integer numeroVisitas, Double gastoTotalAcumulado, String nombre, String apellidoPaterno, String apellidoMaterno, String telefono, LocalDate fechaRegistro, String correoElectronico) {
        super(nombre, apellidoPaterno, apellidoMaterno, telefono, fechaRegistro, correoElectronico);
        this.puntosFidelidad = puntosFidelidad;
        this.numeroVisitas = numeroVisitas;
        this.gastoTotalAcumulado = gastoTotalAcumulado;
    }

    public ClienteFrecuente(Double puntosFidelidad, Integer numeroVisitas, Double gastoTotalAcumulado, String nombre, String apellidoPaterno, String apellidoMaterno, String telefono) {
        super(nombre, apellidoPaterno, apellidoMaterno, telefono);
        this.puntosFidelidad = puntosFidelidad;
        this.numeroVisitas = numeroVisitas;
        this.gastoTotalAcumulado = gastoTotalAcumulado;
    }
    
    public ClienteFrecuente(String nombre, String apellidoPaterno, String apellidoMaterno,String telefono, LocalDate fechaRegistro, Double puntosFidelidad) {
        super(nombre, apellidoPaterno, apellidoMaterno, telefono);
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

    public Double getGastoTotalAcumulado() {
        return gastoTotalAcumulado;
    }

    public void setGastoTotalAcumulado(Double gastoTotalAcumulado) {
        this.gastoTotalAcumulado = gastoTotalAcumulado;
    }

}
