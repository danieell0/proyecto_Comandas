package interfaces;

import Enums.EstadoComandas;
import dto.ComandaDTO;
import excepciones.NegocioExcepcion;

/**
 * Interfaz que define las operaciones de la lógica de negocio relacionadas
 * con la gestión de comandas.
 * 
 * <p>Pertenece a la capa de negocio (Business Object - BO) y establece
 * los métodos necesarios para crear, consultar y cerrar comandas,
 * aplicando las reglas del sistema.</p>
 * 
 * <p>Las implementaciones de esta interfaz deben encargarse de validar
 * los datos y garantizar la consistencia de la información antes de
 * interactuar con la capa de persistencia.</p>
 * 
 * @author munos
 */
public interface IComandaBO {
    
    /**
     * Crea una nueva comanda en el sistema.
     * 
     * <p>Este método recibe un {@link ComandaDTO} con la información necesaria
     * y aplica las validaciones de negocio correspondientes antes de registrar
     * la comanda.</p>
     * 
     * @param dto datos de la comanda a crear
     * @return objeto {@link ComandaDTO} con la información de la comanda creada
     * 
     * @throws NegocioExcepcion si ocurre un error de validación o en la lógica de negocio
     */
    ComandaDTO crearComanda(ComandaDTO dto) throws NegocioExcepcion;
                
    /**
     * Obtiene la comanda activa asociada a una mesa específica.
     * 
     * <p>Generalmente se utiliza para verificar si una mesa ya tiene una
     * comanda abierta.</p>
     * 
     * @param idMesa identificador de la mesa
     * @return objeto {@link ComandaDTO} asociado a la mesa
     * 
     * @throws NegocioExcepcion si no se encuentra la comanda o ocurre un error
     */
    ComandaDTO obtenerPorMesa(Long idMesa) throws NegocioExcepcion;

    /**
     * Cierra una comanda cambiando su estado.
     * 
     * <p>Este método permite finalizar o cancelar una comanda, dependiendo
     * del estado proporcionado.</p>
     * 
     * @param idComanda identificador de la comanda
     * @param estado nuevo estado de la comanda (ENTREGADA o CANCELADA)
     * 
     * @throws NegocioExcepcion si ocurre un error en la operación o el cambio
     * de estado no es válido
     */
    void cerrarComanda(Long idComanda, EstadoComandas estado) throws NegocioExcepcion;
}
