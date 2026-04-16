/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package validadores;

/**
 *
 * @author Jorge
 */
public class ValidarCamposProductos {

    private static final String Nombre = "^[a-zA-ZáéíóúÁÉÍÓÚñÑ ]{3,50}$";
    private static final String Descripcion = "^[a-zA-Z0-9áéíóúÁÉÍÓÚñÑ., ]{5,200}$";
    private static final String precio = "^(\\d+)(\\.\\d{1,2})?$";

    public static boolean nombre(String nombre){
        return nombre !=null && nombre.matches(Nombre);
    }
    
    public static boolean descripcion(String descripcion){
        return descripcion != null && descripcion.matches(Descripcion);
    }
    
    public static boolean precio(Double precio){
        return precio != null && precio > 0;
    }
            

}
