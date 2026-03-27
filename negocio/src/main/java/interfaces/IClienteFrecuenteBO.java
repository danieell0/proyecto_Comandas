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
    void registrar(ClienteDTO cliente) throws NegocioExcepcion;

    boolean eliminar(Long id)throws NegocioExcepcion;

    void editar(ClienteDTO cliente)throws NegocioExcepcion;
    
    List<ClienteDTO> obtenerClientes()throws NegocioExcepcion;
}