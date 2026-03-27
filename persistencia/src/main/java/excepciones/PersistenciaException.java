package excepciones;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 * 'PersistenciaException' es una excepción personalizada que utilizamos para 
 * envolver cualquier error técnico que ocurra al interactuar con la base de datos.
 * * Su propósito es ocultar la complejidad de JPA/SQL y entregar un mensaje 
 * más limpio y controlado a las capas superiores del sistema.
 * @author Benjamin
 */
public class PersistenciaException extends Exception{
    
    public PersistenciaException(String message){
        super(message);
    }
    
    public PersistenciaException(String message, Throwable cause){
        super(message, cause);
    }
    
}
