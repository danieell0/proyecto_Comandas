package entidades;

import Enums.EstadoComandas;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;
import javax.persistence.*;

/**
 * Entidad que representa una comanda dentro del sistema.
 * 
 * <p>Una comanda contiene la información relacionada a una orden realizada
 * por un cliente en una mesa específica, atendida por un mesero, incluyendo
 * sus detalles de productos, estado, fecha y total de la venta.</p>
 * 
 * <p>Esta entidad está mapeada a la tabla <b>comandas</b> en la base de datos.</p>
 * 
 * Relaciones:
 * <ul>
 *   <li>Muchos a uno con {@link EmpleadoMesero} (mesero que atiende).</li>
 *   <li>Muchos a uno con {@link Cliente} (cliente que realiza la orden).</li>
 *   <li>Muchos a uno con {@link Mesa} (mesa asignada).</li>
 *   <li>Uno a muchos con {@link DetalleProducto} (productos de la comanda).</li>
 * </ul>
 * 
 * @author Jorge
 */
@Entity
@Table(name = "comandas")
public class Comanda implements Serializable {

    /**
     * Identificador único de la comanda.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_comanda")
    private Long id;

    /**
     * Folio único de la comanda.
     */
    @Column(name = "folio", nullable = false, length = 50)
    private String folio;

    /**
     * Fecha y hora en la que se creó la comanda.
     */
    @Column(name = "fecha_hora", nullable = false)
    private LocalDateTime fechaHora;

    /**
     * Total de la venta asociada a la comanda.
     */
    @Column(name = "total_venta", nullable = false)
    private Double totalVenta;

    /**
     * Estado actual de la comanda (ABIERTA, ENTREGADA, CANCELADA).
     */
    @Enumerated(EnumType.STRING)
    @Column(name = "estado", nullable = false)
    private EstadoComandas estado;

    /**
     * Relación muchos a uno con el mesero que atiende la comanda.
     */
    @ManyToOne
    @JoinColumn(name = "id_mesero", nullable = false)
    private EmpleadoMesero mesero;
    
    /**
     * Relación muchos a uno con el cliente que realiza la comanda.
     */
    @ManyToOne
    @JoinColumn(name = "id_cliente", nullable = false)
    private Cliente cliente;
    
    /**
     * Relación muchos a uno con la mesa asignada a la comanda.
     */
    @ManyToOne
    @JoinColumn(name = "id_mesa", nullable = false)
    private Mesa mesa;
    
    /**
     * Lista de detalles de productos asociados a la comanda.
     * 
     * <p>Incluye operaciones en cascada para persistir, actualizar y eliminar
     * los detalles junto con la comanda.</p>
     */
    @OneToMany(
        mappedBy = "comanda",
        cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REMOVE},
        orphanRemoval = true
    )
    private List<DetalleProducto> detalles;

    /**
     * Constructor por defecto.
     */
    public Comanda() {
    }

    /**
     * Constructor con parámetros principales de la comanda.
     * 
     * @param folio identificador de la comanda
     * @param fechaHora fecha y hora de creación
     * @param totalVenta total de la venta
     * @param estado estado de la comanda
     * @param mesero mesero asignado
     * @param cliente cliente asociado
     * @param mesa mesa asignada
     */
    public Comanda(String folio, LocalDateTime fechaHora, Double totalVenta, EstadoComandas estado, EmpleadoMesero mesero, Cliente cliente, Mesa mesa) {
        this.folio = folio;
        this.fechaHora = fechaHora;
        this.totalVenta = totalVenta;
        this.estado = estado;
        this.mesero = mesero;
        this.cliente = cliente;
        this.mesa = mesa;
    }

    /**
     * @return identificador de la comanda
     */
    public Long getId() {
        return id;
    }

    /**
     * @param id identificador de la comanda
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * @return folio de la comanda
     */
    public String getFolio() {
        return folio;
    }

    /**
     * @param folio folio de la comanda
     */
    public void setFolio(String folio) {
        this.folio = folio;
    }

    /**
     * @return fecha y hora de la comanda
     */
    public LocalDateTime getFechaHora() {
        return fechaHora;
    }

    /**
     * @param fechaHora fecha y hora de la comanda
     */
    public void setFechaHora(LocalDateTime fechaHora) {
        this.fechaHora = fechaHora;
    }

    /**
     * @return total de la venta
     */
    public Double getTotalVenta() {
        return totalVenta;
    }

    /**
     * @param totalVenta total de la venta
     */
    public void setTotalVenta(Double totalVenta) {
        this.totalVenta = totalVenta;
    }

    /**
     * @return estado actual de la comanda
     */
    public EstadoComandas getEstado() {
        return estado;
    }

    /**
     * @param estado estado de la comanda
     */
    public void setEstado(EstadoComandas estado) {
        this.estado = estado;
    }

    /**
     * @return mesero asignado
     */
    public EmpleadoMesero getMesero() {
        return mesero;
    }

    /**
     * @param mesero mesero asignado
     */
    public void setMesero(EmpleadoMesero mesero) {
        this.mesero = mesero;
    }

    /**
     * @return cliente asociado
     */
    public Cliente getCliente() {
        return cliente;
    }

    /**
     * @param cliente cliente asociado
     */
    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    /**
     * @return mesa asignada
     */
    public Mesa getMesa() {
        return mesa;
    }

    /**
     * @param mesa mesa asignada
     */
    public void setMesa(Mesa mesa) {
        this.mesa = mesa;
    }

    /**
     * @return lista de detalles de productos
     */
    public List<DetalleProducto> getDetalles() {
        return detalles;
    }

    /**
     * @param detalles lista de detalles de productos
     */
    public void setDetalles(List<DetalleProducto> detalles) {
        this.detalles = detalles;
    }
}