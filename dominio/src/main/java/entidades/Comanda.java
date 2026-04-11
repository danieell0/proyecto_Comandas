/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entidades;

import Enums.EstadoComandas;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

/**
 *
 * @author Jorge
 */
@Entity
@Table(name = "comandas")
public class Comanda implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_comanda")
    private Long id;

    @Column(name = "folio", nullable = false, length = 50)
    private String folio;

    @Column(name = "fecha_hora", nullable = false)
    private LocalDateTime fechaHora;

    @Column(name = "total_venta", nullable = false)
    private Double totalVenta;

    @Enumerated(EnumType.STRING)
    @Column(name = "estado", nullable = false)
    private EstadoComandas estado;

    /**
     * esta es de la relcacion con el mesero de muchos a uno 
     */
    @ManyToOne
    @JoinColumn(name = "id_mesero", nullable = false)
    private EmpleadoMesero mesero;
    
    /**
     * esta es la relacion con lo  de cliente es de muchos a uno tambien 
     */
    @ManyToOne
    @JoinColumn(name = "id_cliente", nullable = false)
    private Cliente cliente;
    
    /**
     * checa este sebas, este es la relacion con lo que vas a hacer, con el de mesa 
     * checa que este bien, debe de ser uno a uno, checa si le pusiste como yo id_mesa, si no ponle como lo pusiste 
     */
    @OneToOne
    @JoinColumn(name = "id_mesa",nullable = false)
    private Mesa mesa;
    
    /**
     * esta es la de detales productos este es uno a muchos 
     */
    @OneToMany(mappedBy = "comanda", cascade = {CascadeType.PERSIST,CascadeType.MERGE,CascadeType.REMOVE},orphanRemoval = true )
    private List<DetalleProducto> detalles;

    public Comanda() {
    }

    public Comanda(String folio, LocalDateTime fechaHora, Double totalVenta, EstadoComandas estado, EmpleadoMesero mesero, Cliente cliente, Mesa mesa) {
        this.folio = folio;
        this.fechaHora = fechaHora;
        this.totalVenta = totalVenta;
        this.estado = estado;
        this.mesero = mesero;
        this.cliente = cliente;
        this.mesa = mesa;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFolio() {
        return folio;
    }

    public void setFolio(String folio) {
        this.folio = folio;
    }

    public LocalDateTime getFechaHora() {
        return fechaHora;
    }

    public void setFechaHora(LocalDateTime fechaHora) {
        this.fechaHora = fechaHora;
    }

    public Double getTotalVenta() {
        return totalVenta;
    }

    public void setTotalVenta(Double totalVenta) {
        this.totalVenta = totalVenta;
    }

    public EstadoComandas getEstado() {
        return estado;
    }

    public void setEstado(EstadoComandas estado) {
        this.estado = estado;
    }

    public EmpleadoMesero getMesero() {
        return mesero;
    }

    public void setMesero(EmpleadoMesero mesero) {
        this.mesero = mesero;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public Mesa getMesa() {
        return mesa;
    }

    public void setMesa(Mesa mesa) {
        this.mesa = mesa;
    }

    public List<DetalleProducto> getDetalles() {
        return detalles;
    }

    public void setDetalles(List<DetalleProducto> detalles) {
        this.detalles = detalles;
    }
    
    
}
