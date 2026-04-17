package interfaces;

import Enums.EstadoMesa;
import dto.MesaDTO;
import excepciones.NegocioExcepcion;
import java.util.List;

/**
 * Interfaz que define las operaciones de la lógica de negocio relacionadas
 * con la gestión de mesas.
 * 
 * <p>Pertenece a la capa de negocio (Business Object - BO) y establece
 * los métodos necesarios para consultar y administrar el estado de las mesas
 * dentro del sistema.</p>
 * 
 * <p>Las implementaciones deben encargarse de validar reglas de negocio,
 * como la disponibilidad de mesas y la consistencia en los cambios de estado.</p>
 * 
 * @author munos
 */
public interface IMesaBO {

    /**
     * Obtiene la lista de todas las mesas registradas en el sistema.
     * 
     * @return lista de objetos {@link MesaDTO} que representan todas las mesas
     * 
     * @throws NegocioExcepcion si ocurre un error al recuperar las mesas
     */
    List<MesaDTO> obtenerMesas() throws NegocioExcepcion;

    /**
     * Obtiene la lista de mesas disponibles.
     * 
     * <p>Se consideran disponibles aquellas mesas cuyo estado permite
     * ser asignadas a una nueva comanda.</p>
     * 
     * @return lista de mesas disponibles como {@link MesaDTO}
     * 
     * @throws NegocioExcepcion si ocurre un error en la consulta
     */
    List<MesaDTO> obtenerDisponibles() throws NegocioExcepcion;

    /**
     * Cambia el estado de una mesa.
     * 
     * <p>Permite actualizar el estado de la mesa (por ejemplo: DISPONIBLE,
     * OCUPADA, etc.), respetando las reglas de negocio establecidas.</p>
     * 
     * @param idMesa identificador de la mesa
     * @param estado nuevo estado que se desea asignar
     * 
     * @throws NegocioExcepcion si el cambio de estado no es válido o ocurre un error
     */
    void cambiarEstado(Long idMesa, EstadoMesa estado) throws NegocioExcepcion;
}