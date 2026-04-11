/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dto;

import java.time.LocalDate;
import java.time.LocalTime;

/**
 *
 * @author munos
 */
public class ReporteComandaDTO {
    // Fecha y hora separa
    private LocalDate fecha;
    private LocalTime hora;
    private String numeroMesa;
    private Double totalVenta;
    private String estado;
    private String nombreCliente;

    public ReporteComandaDTO() {
    }

    public ReporteComandaDTO(LocalDate fecha, LocalTime hora, String numeroMesa, Double totalVenta, String estado, String nombreCliente) {
        this.fecha = fecha;
        this.hora = hora;
        this.numeroMesa = numeroMesa;
        this.totalVenta = totalVenta;
        this.estado = estado;
        this.nombreCliente = nombreCliente;
    }

    // Getters y Setters
    public LocalDate getFecha() {
        return fecha;
    }

    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }

    public LocalTime getHora() {
        return hora;
    }

    public void setHora(LocalTime hora) {
        this.hora = hora;
    }

    public String getNumeroMesa() {
        return numeroMesa;
    }

    public void setNumeroMesa(String numeroMesa) {
        this.numeroMesa = numeroMesa;
    }

    public Double getTotalVenta() {
        return totalVenta;
    }

    public void setTotalVenta(Double totalVenta) {
        this.totalVenta = totalVenta;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getNombreCliente() {
        return nombreCliente;
    }

    public void setNombreCliente(String nombreCliente) {
        this.nombreCliente = nombreCliente;
    }
}
