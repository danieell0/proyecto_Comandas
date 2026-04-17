/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entidades;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

/**
 * Clase entity que representa un empleado del tipo administrador y 
 * sus atributos
 * @author Benjamin
 */
@Entity
@Table(name = "empleados_administradores")
@DiscriminatorValue("ADMINISTRADOR")
@PrimaryKeyJoinColumn(name = "id_empleado")
public class EmpleadoAdministrador extends Empleado implements Serializable{
    
    @Column(name = "codigo_administrador", unique = true, nullable = false)
    private Long codigoAdministrador;
    
    public EmpleadoAdministrador(){}
    
    public EmpleadoAdministrador(Long codigoAdministrador, String nombre, String apellidoPaterno, String apellidoMaterno, String telefono, String puesto, Long sueldo){
        super(nombre, apellidoPaterno, apellidoMaterno, telefono, puesto, sueldo);
        this.codigoAdministrador = codigoAdministrador;
    }

    public Long getCodigoAdministrador() {
        return codigoAdministrador;
    }

    public void setCodigoAdministrador(Long codigoAdministrador) {
        this.codigoAdministrador = codigoAdministrador;
    }
    
    
    
}
