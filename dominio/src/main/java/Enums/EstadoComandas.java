package Enums;

/**
 * Enum que representa los posibles estados de una comanda dentro del sistema.
 * 
 * <p>Este enumerador se utiliza para controlar el flujo de una comanda
 * desde su creación hasta su finalización o cancelación.</p>
 * 
 * <ul>
 *   <li><b>ABIERTA:</b> La comanda ha sido creada y aún está en proceso.</li>
 *   <li><b>ENTREGADA:</b> La comanda ha sido completada y entregada al cliente.</li>
 *   <li><b>CANCELADA:</b> La comanda fue cancelada antes de completarse.</li>
 * </ul>
 * 
 * @author munos
 */
public enum EstadoComandas {
    
    /**
     * Indica que la comanda está activa y en proceso.
     */
    ABIERTA,

    /**
     * Indica que la comanda ha sido finalizada y entregada.
     */
    ENTREGADA,

    /**
     * Indica que la comanda fue cancelada.
     */
    CANCELADA
}
