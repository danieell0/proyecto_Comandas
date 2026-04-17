/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entidades;

import java.security.Key;
import java.util.Base64;
import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;

/**
 * Clase utilitaria que sirve para encriptar y desencriptar cadenas de texto usando AES (Advanced Ecnryption Stadard)
 * Es una api que soporta claves de varios bits hasta 256 igual que el sha y se utiliza para seguridad y gestion de claves y datos
 * @author Benjamin
 */
public class EncriptadorAES {
    
    //Algoritmo estandar de cifrado
    private static final String ALGORITMO = "AES";
    
    //la llave para el restaurante, de 16 caracteres 128 bits
    private static final String LLAVE_SECRETA = "RestauranteClave";
    
    /**
     * Toma un texto normal y devuelve una cadena encriptada y segura para la BD.
     */
    public static String encriptar(String textoNormal) throws Exception {
        // 1. Preparamos la llave y la cerradura (Cipher)
        Key key = new SecretKeySpec(LLAVE_SECRETA.getBytes(), ALGORITMO);
        Cipher cipher = Cipher.getInstance(ALGORITMO);
        cipher.init(Cipher.ENCRYPT_MODE, key);
        
        // 2. Encriptamos el texto (esto devuelve un arreglo de bytes binarios)
        byte[] bytesEncriptados = cipher.doFinal(textoNormal.getBytes());
        
        // 3. Convertimos los bytes binarios a texto seguro Base64
        return Base64.getEncoder().encodeToString(bytesEncriptados);
    }

    /**
     * Toma una cadena encriptada de la BD y la devuelve a su formato original.
     */
    public static String desencriptar(String textoBase64) throws Exception {
        // 1. Preparamos la llave y la cerradura en modo "abrir"
        Key key = new SecretKeySpec(LLAVE_SECRETA.getBytes(), ALGORITMO);
        Cipher cipher = Cipher.getInstance(ALGORITMO);
        cipher.init(Cipher.DECRYPT_MODE, key);
        
        // 2. Decodificamos el texto seguro para recuperar los bytes binarios originales
        byte[] bytesDecodificados = Base64.getDecoder().decode(textoBase64);
        
        // 3. Desencriptamos los bytes usando la llave
        byte[] bytesDesencriptados = cipher.doFinal(bytesDecodificados);
        
        // 4. Lo convertimos de vuelta a un String normal
        return new String(bytesDesencriptados);
    }
}
