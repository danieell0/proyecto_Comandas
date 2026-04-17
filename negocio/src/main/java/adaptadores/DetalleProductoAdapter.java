package adaptadores;

import dto.DetalleProductoDTO;
import entidades.DetalleProducto;
import entidades.Producto;

/**
 * Clase adaptadora encargada de convertir objetos de tipo
 * {@link DetalleProductoDTO} a {@link DetalleProducto}.
 * 
 * <p>Forma parte de la capa de adaptación (Adapter), permitiendo transformar
 * los datos provenientes de la capa de presentación o negocio en entidades
 * listas para ser persistidas.</p>
 * 
 * <p>Este adaptador también reconstruye la relación con {@link Producto}
 * utilizando únicamente el identificador proporcionado en el DTO.</p>
 * 
 * @author munos
 */
public class DetalleProductoAdapter {

    /**
     * Convierte un objeto {@link DetalleProductoDTO} a su equivalente
     * entidad {@link DetalleProducto}.
     * 
     * <p>Se asignan los valores básicos (cantidad, comentario, precio) y se
     * reconstruye la relación con {@link Producto} usando su ID. Además,
     * se calcula el subtotal en base a la cantidad y el precio.</p>
     * 
     * @param dto objeto {@link DetalleProductoDTO} a convertir
     * @return objeto {@link DetalleProducto} listo para persistencia
     */
    public static DetalleProducto dtoAEntidad(DetalleProductoDTO dto) {

        /**
         * Creación de la entidad DetalleProducto.
         */
        DetalleProducto det = new DetalleProducto();

        /**
         * Asignación de atributos básicos.
         */
        det.setCantidad(dto.getCantidad());
        det.setComentarioComanda(dto.getComentario());
        det.setPrecio(dto.getPrecio());

        /**
         * Reconstrucción de la relación con Producto usando su ID.
         */
        Producto producto = new Producto();
        producto.setId(dto.getIdProducto());

        det.setProducto(producto);

        /**
         * Cálculo del subtotal (cantidad * precio).
         */
        det.setSubtotal(dto.getCantidad() * dto.getPrecio());

        return det;
    }
}
