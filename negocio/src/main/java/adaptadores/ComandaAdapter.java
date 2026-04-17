package adaptadores;

import dto.ComandaDTO;
import dto.DetalleProductoDTO;
import entidades.Comanda;
import entidades.DetalleProducto;
import java.util.ArrayList;
import java.util.List;

/**
 * Clase adaptadora encargada de convertir objetos de tipo {@link Comanda}
 * a {@link ComandaDTO}.
 * 
 * <p>Forma parte de la capa de adaptación (Adapter), cuyo propósito es desacoplar
 * las entidades de persistencia de los objetos utilizados en otras capas del sistema,
 * como la capa de presentación o negocio.</p>
 * 
 * <p>También se encarga de transformar los objetos relacionados, como
 * {@link DetalleProducto}, a sus respectivos DTOs.</p>
 * 
 * @author munos
 */
public class ComandaAdapter {

    /**
     * Convierte una entidad {@link Comanda} a su equivalente {@link ComandaDTO}.
     * 
     * <p>Este método transforma todos los atributos principales de la comanda,
     * incluyendo la conversión de la lista de {@link DetalleProducto} a
     * {@link DetalleProductoDTO}.</p>
     * 
     * <p>Las relaciones con otras entidades (mesa, cliente, mesero) se representan
     * mediante sus identificadores.</p>
     * 
     * @param c objeto {@link Comanda} a convertir
     * @return objeto {@link ComandaDTO} con los datos de la comanda
     */
    public static ComandaDTO entidadADTO(Comanda c) {

        /**
         * Lista que almacenará los detalles convertidos a DTO.
         */
        List<DetalleProductoDTO> detallesDTO = new ArrayList<>();

        /**
         * Conversión de cada DetalleProducto a DetalleProductoDTO.
         */
        if (c.getDetalles() != null) {
            for (DetalleProducto d : c.getDetalles()) {
                DetalleProductoDTO dto = new DetalleProductoDTO();
                dto.setId(d.getId());
                dto.setIdProducto(d.getProducto().getId());
                dto.setNombreProducto(d.getProducto().getNombre());
                dto.setCantidad(d.getCantidad());
                dto.setComentario(d.getComentarioComanda());
                dto.setPrecio(d.getPrecio());
                dto.setSubtotal(d.getSubtotal());
                detallesDTO.add(dto);
            }
        }

        /**
         * Construcción del objeto ComandaDTO con los datos convertidos.
         */
        return new ComandaDTO(
                c.getId(),
                c.getFolio(),
                c.getFechaHora(),
                c.getTotalVenta(),
                c.getEstado(),
                c.getMesa().getId(),
                c.getCliente().getId(),
                c.getMesero().getId(),
                detallesDTO
        );
    }
}