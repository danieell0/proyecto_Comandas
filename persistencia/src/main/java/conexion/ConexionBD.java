/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package conexion;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 * Clase para hacer la conexion a la BD instanciando al entity manager factory
 * @author Benjamin
 */
public class ConexionBD {    
    
    private static final EntityManagerFactory entityManagerFactory = 
            Persistence.createEntityManagerFactory("ConexionPU");

    private ConexionBD(){}
    
    public static EntityManager crearConexion(){
        return entityManagerFactory.createEntityManager();
    }
}
