/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package excepciones;

/**
 * Excepcion personalizada para la capa de negocio
 * @author munos
 */
public class NegocioExcepcion extends Exception {
    
    public NegocioExcepcion(String message) {
        super(message);
    }

    public NegocioExcepcion(String message, Throwable cause) {
        super(message, cause);
    }

}
