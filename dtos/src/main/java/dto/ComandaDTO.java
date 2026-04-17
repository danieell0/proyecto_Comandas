package dto;

import Enums.EstadoComandas;
import java.time.LocalDateTime;
import java.util.List;

/**
 * Objeto de Transferencia de Datos (DTO) que representa una comanda.
 * 
 * <p>Esta clase se utiliza para transportar la información de una comanda
 * entre las diferentes capas del sistema (presentación, negocio, persistencia)
 * sin exponer directamente la entidad {@link entidades.Comanda}.</p>
 * 
 * <p>Incluye únicamente los datos necesarios para la operación, utilizando
 * identificadores (IDs) para las relaciones en lugar de objetos completos.</p>
 * 
 * Contiene:
 * <ul>
 *   <li>Datos generales de la comanda (folio, fecha, total, estado).</li>
 *   <li>Referencias a entidades relacionadas mediante IDs (mesa, cliente, mesero).</li>
 *   <li>Lista de detalles de productos asociados.</li>
 * </ul>
 * 
 * @author munos
 */
public class ComandaDTO {

    /**
     * Identificador único de la comanda.
     */
    private Long id;

    /**
     * Folio de la comanda.
     */
    private String folio;

    /**
     * Fecha y hora de creación de la comanda.
     */
    private LocalDateTime fechaHora;

    /**
     * Total de la venta de la comanda.
     */
    private Double totalVenta;

    /**
     * Estado actual de la comanda.
     */
    private EstadoComandas estado;

    /**
     * Lista de detalles de productos incluidos en la comanda.
     */
    private List<DetalleProductoDTO> detalles;

    /**
     * Identificador de la mesa asociada.
     */
    private Long idMesa;

    /**
     * Identificador del cliente asociado.
     */
    private Long idCliente;

    /**
     * Identificador del mesero que atiende la comanda.
     */
    private Long idMesero;

    /**
     * Constructor por defecto.
     */
    public ComandaDTO() {
    }

    /**
     * Constructor con todos los atributos de la comanda.
     * 
     * @param id identificador de la comanda
     * @param folio folio de la comanda
     * @param fechaHora fecha y hora de creación
     * @param totalVenta total de la venta
     * @param estado estado de la comanda
     * @param idMesa identificador de la mesa
     * @param idCliente identificador del cliente
     * @param idMesero identificador del mesero
     * @param detalles lista de detalles de productos
     */
    public ComandaDTO(Long id, String folio, LocalDateTime fechaHora, Double totalVenta, EstadoComandas estado, Long idMesa, Long idCliente, Long idMesero, List<DetalleProductoDTO> detalles) {
        this.id = id;
        this.folio = folio;
        this.fechaHora = fechaHora;
        this.totalVenta = totalVenta;
        this.estado = estado;
        this.idMesa = idMesa;
        this.idCliente = idCliente;
        this.idMesero = idMesero;
        this.detalles = detalles;
    }

    /**
     * @return lista de detalles de productos
     */
    public List<DetalleProductoDTO> getDetalles() {
        return detalles;
    }

    /**
     * @param detalles lista de detalles de productos
     */
    public void setDetalles(List<DetalleProductoDTO> detalles) {
        this.detalles = detalles;
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
     * @return estado de la comanda
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
     * @return identificador de la mesa
     */
    public Long getIdMesa() {
        return idMesa;
    }

    /**
     * @param idMesa identificador de la mesa
     */
    public void setIdMesa(Long idMesa) {
        this.idMesa = idMesa;
    }

    /**
     * @return identificador del cliente
     */
    public Long getIdCliente() {
        return idCliente;
    }

    /**
     * @param idCliente identificador del cliente
     */
    public void setIdCliente(Long idCliente) {
        this.idCliente = idCliente;
    }

    /**
     * @return identificador del mesero
     */
    public Long getIdMesero() {
        return idMesero;
    }

    /**
     * @param idMesero identificador del mesero
     */
    public void setIdMesero(Long idMesero) {
        this.idMesero = idMesero;
    }
}