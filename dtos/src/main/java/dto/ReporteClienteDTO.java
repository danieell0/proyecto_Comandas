/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dto;

import java.time.LocalDateTime;
import java.util.Date;

/**
 * Objeto de Transferencia de Datos específico para el reporte de clientes.
 * Almacena datos agregados y calculados (sumas, conteos) directamente desde la base de datos.
 * 
 * Este permite hacer la dto projection con los calculados de cliente frecuente
 * @author Jorge
 */
public class ReporteClienteDTO {

    private String nombreCliente;
    private Long numeroVisitas;
    private Double totalGastado;
    private LocalDateTime fechaUltimaComanda;
    
    public ReporteClienteDTO(){}

    /**
     * Constructor utilizado para la proyeccion el orden de parametros deben de coincidir exactamente con la consulta
     */
    public ReporteClienteDTO(String nombreCliente, Long numeroVisitas, Double totalGastado, LocalDateTime fechaUltimaComanda) {
        this.nombreCliente = nombreCliente;
        this.numeroVisitas = numeroVisitas;
        this.totalGastado = totalGastado;
        this.fechaUltimaComanda = fechaUltimaComanda;
    }

    public String getNombreCliente() {
        return nombreCliente;
    }

    public void setNombreCliente(String nombreCliente) {
        this.nombreCliente = nombreCliente;
    }

    public Long getNumeroVisitas() {
        return numeroVisitas;
    }

    public void setNumeroVisitas(Long numeroVisitas) {
        this.numeroVisitas = numeroVisitas;
    }

    public Double getTotalGastado() {
        return totalGastado;
    }

    public void setTotalGastado(Double totalGastado) {
        this.totalGastado = totalGastado;
    }

    public LocalDateTime getFechaUltimaComanda() {
        return fechaUltimaComanda;
    }

    public void setFechaUltimaComanda(LocalDateTime fechaUltimaComanda) {
        this.fechaUltimaComanda = fechaUltimaComanda;
    }

}
