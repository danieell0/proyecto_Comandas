/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package pruebas;

import conexion.ConexionBD;
import entidades.ClienteFrecuente;
import java.time.LocalDate;
import javax.persistence.EntityManager;

/**
 *
 * @author Benjamin
 */
public class PruebaConexion {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        System.out.println("Iniciando prueba de conexión...");
        
        // 1. Obtenemos el EntityManager usando tu clase de conexión
        EntityManager em = ConexionBD.crearConexion();
        //asd
        try {
            // 2. Iniciamos una transacción (Obligatorio para guardar/modificar/borrar)
            em.getTransaction().begin();
            
            // 3. Creamos un objeto de tu clase hija
            ClienteFrecuente cliente = new ClienteFrecuente(
                    "Juan", 
                    "Perez", 
                    "Gomez", 
                    "6441234567", 
                    LocalDate.now(), 
                    150.5
            );
            
            // 4. Le decimos a JPA que lo guarde en la BD
            em.persist(cliente);
            
            // 5. Confirmamos la transacción (Hacemos el commit)
            em.getTransaction().commit();
            
            System.out.println("¡Éxito! El cliente se guardó correctamente.");
            
        } catch (Exception e) {
            System.err.println("Hubo un error al guardar: " + e.getMessage());
            // Si algo sale mal, deshacemos los cambios
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
        } finally {
            // 6. Siempre cerramos el EntityManager al terminar
            em.close();
            System.out.println("Conexión cerrada.");
        }
    }
}
