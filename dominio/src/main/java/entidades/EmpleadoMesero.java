/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entidades;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

/**
 *
 * @author Jorge
 */
@Entity
@Table(name = "empleados_meseros")
@DiscriminatorValue("MESERO")
@PrimaryKeyJoinColumn(name = "id_empleado")
public class EmpleadoMesero extends Empleado implements Serializable{
    
    @Column(name = "codigo_mesero")
    private Long codigoMesero;
    
    //@OneToMany(mappedBy = "mesero")
    //private List<Comanda> comandas;

    public EmpleadoMesero(Long codigoMesero) {
        this.codigoMesero = codigoMesero;
    }

    public Long getCodigoMesero() {
        return codigoMesero;
    }

    public void setCodigoMesero(Long codigoMesero) {
        this.codigoMesero = codigoMesero;
    }
    
}
