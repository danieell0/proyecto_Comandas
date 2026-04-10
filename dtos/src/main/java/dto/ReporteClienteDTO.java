/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dto;

import java.util.Date;

/**
 *
 * @author Jorge
 */
public class ReporteClienteDTO {

    private String nombre;
    private int visitas;
    private double totalGastado;
    private Date ultimaComanda;

    public ReporteClienteDTO(String nombre, int visitas, double totalGastado, Date ultimaComanda) {
        this.nombre = nombre;
        this.visitas = visitas;
        this.totalGastado = totalGastado;
        this.ultimaComanda = ultimaComanda;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getVisitas() {
        return visitas;
    }

    public void setVisitas(int visitas) {
        this.visitas = visitas;
    }

    public double getTotalGastado() {
        return totalGastado;
    }

    public void setTotalGastado(double totalGastado) {
        this.totalGastado = totalGastado;
    }

    public Date getUltimaComanda() {
        return ultimaComanda;
    }

    public void setUltimaComanda(Date ultimaComanda) {
        this.ultimaComanda = ultimaComanda;
    }

    
}
