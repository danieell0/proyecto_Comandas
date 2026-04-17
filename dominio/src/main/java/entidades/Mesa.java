package entidades;

import Enums.EstadoMesa;
import java.io.Serializable;
import java.util.List;
import javax.persistence.*;

/**
 * Entidad que representa una mesa dentro del sistema.
 * 
 * <p>Una mesa es un recurso físico del establecimiento que puede estar en
 * distintos estados (disponible, ocupada, etc.) y puede tener asociadas
 * múltiples comandas a lo largo del tiempo.</p>
 * 
 * <p>Esta entidad está mapeada a la tabla <b>mesas</b> en la base de datos.</p>
 * 
 * Relaciones:
 * <ul>
 *   <li>Uno a muchos con {@link Comanda} (comandas asociadas a la mesa).</li>
 * </ul>
 * 
 * @author Jorge
 */
@Entity
@Table(name = "mesas")
public class Mesa implements Serializable {

    /**
     * Identificador único de la mesa.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_mesa")
    private Long id;

    /**
     * Número identificador de la mesa (único).
     */
    @Column(name = "numero", nullable = false, unique = true)
    private String numero;

    /**
     * Estado actual de la mesa (por ejemplo: DISPONIBLE, OCUPADA, etc.).
     */
    @Enumerated(EnumType.STRING)
    @Column(name = "estado", nullable = false)
    private EstadoMesa estado;
    
    /**
     * Lista de comandas asociadas a la mesa.
     */
    @OneToMany(mappedBy = "mesa")
    private List<Comanda> comandas;

    /**
     * Constructor por defecto.
     */
    public Mesa() {
    }

    /**
     * Constructor con parámetros principales de la mesa.
     * 
     * @param numero número identificador de la mesa
     * @param estado estado inicial de la mesa
     */
    public Mesa(String numero, EstadoMesa estado) {
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
     * @return estado actual de la mesa
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
     * @return lista de comandas asociadas a la mesa
     */
    public List<Comanda> getComandas() {
        return comandas;
    }

    /**
     * @param comandas lista de comandas asociadas a la mesa
     */
    public void setComandas(List<Comanda> comandas) {
        this.comandas = comandas;
    }    
}