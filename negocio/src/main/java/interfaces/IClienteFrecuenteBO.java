/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package interfaces;

import dto.ClienteDTO;
import excepciones.NegocioExcepcion;
import java.util.List;

/**
 *
 * @author munos
 */
public interface IClienteFrecuenteBO {
    /**
     * Registra un nuevo cliente aplicando las reglas de validacion
     */
    void registrar(ClienteDTO cliente) throws NegocioExcepcion;

    /**
     * Elimina un cliente aplicando las reglas de validacion
     */
    boolean eliminar(Long id)throws NegocioExcepcion;

    /**
     * Edita un cliente aplicando las reglas de validacion
     */
    void editar(ClienteDTO cliente)throws NegocioExcepcion;
    
    /**
     * Obtiene una lista de clientes 
     */
    List<ClienteDTO> obtenerClientes()throws NegocioExcepcion;
}