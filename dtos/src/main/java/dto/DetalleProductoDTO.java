package dto;

/**
 * Objeto de Transferencia de Datos (DTO) que representa el detalle de un producto
 * dentro de una comanda.
 * 
 * <p>Esta clase encapsula la información de cada producto incluido en una comanda,
 * como cantidad, precio, subtotal y observaciones, permitiendo transportar estos
 * datos entre las capas del sistema sin exponer la entidad correspondiente.</p>
 * 
 * <p>Incluye lógica interna para recalcular automáticamente el subtotal cuando
 * cambian la cantidad o el precio.</p>
 * 
 * @author munos
 */
public class DetalleProductoDTO {

    /**
     * Identificador único del detalle.
     */
    private Long id;

    /**
     * Identificador del producto asociado.
     */
    private Long idProducto;

    /**
     * Nombre del producto (útil para presentación).
     */
    private String nombreProducto;

    /**
     * Cantidad del producto solicitada.
     */
    private Integer cantidad;

    /**
     * Comentario u observación adicional del producto.
     */
    private String comentario;

    /**
     * Precio unitario del producto.
     */
    private Double precio;

    /**
     * Subtotal calculado (cantidad * precio).
     */
    private Double subtotal;

    /**
     * Constructor por defecto.
     */
    public DetalleProductoDTO() {
    }

    /**
     * Constructor con parámetros principales del detalle.
     * 
     * <p>El subtotal se calcula automáticamente al crear la instancia.</p>
     * 
     * @param idProducto identificador del producto
     * @param cantidad cantidad solicitada
     * @param comentario comentario adicional
     * @param precio precio unitario
     * @param nombreProducto nombre del producto
     */
    public DetalleProductoDTO(Long idProducto, Integer cantidad, String comentario, Double precio, String nombreProducto) {
        this.idProducto = idProducto;
        this.cantidad = cantidad;
        this.nombreProducto = nombreProducto;
        this.comentario = comentario;
        this.precio = precio;
        this.subtotal = cantidad * precio;
    }

    /**
     * @return nombre del producto
     */
    public String getNombreProducto() {
        return nombreProducto;
    }

    /**
     * @param nombreProducto nombre del producto
     */
    public void setNombreProducto(String nombreProducto) {
        this.nombreProducto = nombreProducto;
    }

    /**
     * @return identificador del detalle
     */
    public Long getId() {
        return id;
    }

    /**
     * @param id identificador del detalle
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * @return identificador del producto
     */
    public Long getIdProducto() {
        return idProducto;
    }

    /**
     * @param idProducto identificador del producto
     */
    public void setIdProducto(Long idProducto) {
        this.idProducto = idProducto;
    }

    /**
     * @return cantidad del producto
     */
    public Integer getCantidad() {
        return cantidad;
    }

    /**
     * Establece la cantidad del producto y recalcula el subtotal.
     * 
     * @param cantidad cantidad del producto
     */
    public void setCantidad(Integer cantidad) {
        this.cantidad = cantidad;
        recalcularSubtotal();
    }

    /**
     * @return comentario del producto
     */
    public String getComentario() {
        return comentario;
    }

    /**
     * @param comentario comentario del producto
     */
    public void setComentario(String comentario) {
        this.comentario = comentario;
    }

    /**
     * @return precio unitario del producto
     */
    public Double getPrecio() {
        return precio;
    }

    /**
     * Establece el precio del producto y recalcula el subtotal.
     * 
     * @param precio precio unitario
     */
    public void setPrecio(Double precio) {
        this.precio = precio;
        recalcularSubtotal();
    }

    /**
     * @return subtotal del producto
     */
    public Double getSubtotal() {
        return subtotal;
    }

    /**
     * @param subtotal subtotal del producto
     */
    public void setSubtotal(Double subtotal) {
        this.subtotal = subtotal;
    }

    /**
     * Recalcula el subtotal en función de la cantidad y el precio.
     * 
     * <p>Este método se ejecuta automáticamente cuando se modifican
     * la cantidad o el precio.</p>
     */
    private void recalcularSubtotal() {
        if (cantidad != null && precio != null) {
            this.subtotal = cantidad * precio;
        }
    }
}