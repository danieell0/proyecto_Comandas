package adaptadores;

import dto.MesaDTO;
import entidades.Mesa;
import java.util.List;

/**
 * Clase adaptadora encargada de convertir objetos entre
 * {@link Mesa} y {@link MesaDTO}.
 * 
 * <p>Forma parte de la capa de adaptación (Adapter), permitiendo desacoplar
 * las entidades de persistencia de los objetos utilizados en las capas
 * de presentación y negocio.</p>
 * 
 * <p>Incluye métodos para conversiones individuales y para listas de objetos.</p>
 * 
 * @author munos
 */
public class MesaAdapter {

    /**
     * Convierte un objeto {@link MesaDTO} a su equivalente entidad {@link Mesa}.
     * 
     * <p>Se copian los atributos básicos como ID, número y estado.</p>
     * 
     * @param dto objeto {@link MesaDTO} a convertir
     * @return objeto {@link Mesa} listo para persistencia
     * 
     * @throws NullPointerException si el DTO es nulo
     */
    public static Mesa dtoAEntidad(MesaDTO dto) {

        if (dto == null) {
            throw new NullPointerException("El MesaDTO no puede ser nulo");
        }

        Mesa mesa = new Mesa();
        mesa.setId(dto.getId());
        mesa.setNumero(dto.getNumero());
        mesa.setEstado(dto.getEstado());

        return mesa;
    }

    /**
     * Convierte una entidad {@link Mesa} a su equivalente {@link MesaDTO}.
     * 
     * @param mesa objeto {@link Mesa} a convertir
     * @return objeto {@link MesaDTO} con los datos de la mesa
     * 
     * @throws NullPointerException si la entidad es nula
     */
    public static MesaDTO entidadADTO(Mesa mesa) {

        if (mesa == null) {
            throw new NullPointerException("La entidad Mesa no puede ser nula");
        }

        return new MesaDTO(
                mesa.getId(),
                mesa.getNumero(),
                mesa.getEstado()
        );
    }

    /**
     * Convierte una lista de entidades {@link Mesa} a una lista de
     * {@link MesaDTO}.
     * 
     * <p>Utiliza programación funcional (Streams) para transformar cada
     * elemento de la lista.</p>
     * 
     * @param lista lista de entidades {@link Mesa}
     * @return lista de objetos {@link MesaDTO}
     * 
     * @implNote Este método utiliza {@code stream().map()} para realizar
     * la conversión de forma eficiente y legible.
     */
    public static List<MesaDTO> listaEntidadDTO(List<Mesa> lista) {

        return lista.stream()
                .map(MesaAdapter::entidadADTO)
                .toList();
    }
}