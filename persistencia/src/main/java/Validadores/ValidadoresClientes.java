/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Validadores;

/**
 *
 * @author munos
 */
public class ValidadoresClientes {
    public static boolean nombre(String nombre) {
        return nombre != null && nombre.matches("^[A-Za-zÁÉÍÓÚáéíóúÑñ ]{5,30}$");
    }

    public static boolean apellidoPaterno(String apellido) {
        return apellido != null && apellido.matches("^[A-Za-zÁÉÍÓÚáéíóúÑñ ]{5,30}$");
    }

    public static boolean apellidoMaterno(String apellido) {
        return apellido != null && apellido.matches("^[A-Za-zÁÉÍÓÚáéíóúÑñ ]{5,30}$");
    }

    public static boolean telefono(String telefono) {
        return telefono != null && telefono.matches("^\\d{10}$");
    }

    public static boolean correo(String correo) {
        return correo != null && correo.matches("^[^@\\s]+@[^@\\s]+\\.[^@\\s]+$");
    }

    public static boolean clienteValido(String nombre, String apPaterno, String apMaterno, String telefono, String correo) {
        return nombre(nombre) && apellidoPaterno(apPaterno) && apellidoMaterno(apMaterno) && telefono(telefono) && correo(correo);
    }
}