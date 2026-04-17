/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package objetosNegocio;

import DAO.EmpleadoDAO;
import dto.EmpleadoMeseroDTO;
import excepciones.NegocioExcepcion;

/**
 * Clase de business object para validar las reglas de negocio relacionadas a un empleado
 * @author Benjamin
 */
public class EmpleadoBO {
    
    //1. Declaramos la variable para nuestra herramienta DAO
    private EmpleadoDAO empleadoDAO;
    
    //2. Inicializamos el DAO en el constructor del BO
    public EmpleadoBO(){
        this.empleadoDAO = new EmpleadoDAO();
    }
    
    // Metodo para que inicie sesion un mesero y se guarden las comandas con el
    public EmpleadoMeseroDTO iniciarSesionMesero(Long codigoIngresado) throws NegocioExcepcion {
        
        try {
            
            entidades.EmpleadoMesero meseroEntity = empleadoDAO.buscarMeseroPorCodigo(codigoIngresado);

            // 2. Si existe, lo convierte a DTO y lo devuelve
            if (meseroEntity != null) {
                return new EmpleadoMeseroDTO(
                        meseroEntity.getId(),
                        meseroEntity.getNombre(),
                        meseroEntity.getApellidoPaterno(),
                        meseroEntity.getApellidoMaterno(),
                        meseroEntity.getTelefono(), // (Este ya vendría desencriptado por el @PostLoad) y por que el postload usa el encriptador AES
                        meseroEntity.getPuesto(),
                        meseroEntity.getCodigoMesero()
                );
            } else {
                // 3. Si no existe, lanzas un error que la pantalla mostrará
                throw new NegocioExcepcion("Código de mesero incorrecto o no registrado.");
            }
            //Se atrapa la excepcion de la persistencia si se llega a tirar
        }catch (Exception ex){
            throw new NegocioExcepcion(ex.getMessage());
        }
    }
    
    
}
