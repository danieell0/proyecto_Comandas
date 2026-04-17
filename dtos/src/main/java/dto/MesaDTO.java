package dto;

import Enums.EstadoMesa;

/**
 * Objeto de Transferencia de Datos (DTO) que representa una mesa.
 * 
 * <p>Esta clase se utiliza para transportar la información de una mesa entre
 * las diferentes capas del sistema sin exponer directamente la entidad
 * {@link entidades.Mesa}.</p>
 * 
 * <p>Contiene únicamente los datos esenciales de una mesa, como su identificador,
 * número y estado actual.</p>
 * 
 * @author munos
 */
public class MesaDTO {

    /**
     * Identificador único de la mesa.
     */
    private Long id;

    /**
     * Número identificador de la mesa.
     */
    private String numero;

    /**
     * Estado actual de la mesa (por ejemplo: DISPONIBLE, OCUPADA, etc.).
     */
    private EstadoMesa estado;

    /**
     * Constructor por defecto.
     */
    public MesaDTO() {}

    /**
     * Constructor con parámetros de la mesa.
     * 
     * @param id identificador de la mesa
     * @param numero número de la mesa
     * @param estado estado actual de la mesa
     */
    public MesaDTO(Long id, String numero, EstadoMesa estado) {
        this.id = id;
        this.numero = numero;
        this.estado = estado;
    }

    /**
     * @return identificador de la mesa
     */
    public Long getId() {
        return id;
    }

    /**
     * @param id identificador de la mesa
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * @return número de la mesa
     */
    public String getNumero() {
        return numero;
    }

    /**
     * @param numero número de la mesa
     */
    public void setNumero(String numero) {
        this.numero = numero;
    }

    /**
     * @return estado de la mesa
     */
    public EstadoMesa getEstado() {
        return estado;
    }

    /**
     * @param estado estado de la mesa
     */
    public void setEstado(EstadoMesa estado) {
        this.estado = estado;
    }

    /**
     * Representación en texto de la mesa.
     * 
     * <p>Se utiliza principalmente en componentes de interfaz gráfica
     * (como JComboBox) para mostrar el número de la mesa.</p>
     * 
     * @return número de la mesa como cadena
     */
    @Override
    public String toString() {
        return numero; 
    }
}