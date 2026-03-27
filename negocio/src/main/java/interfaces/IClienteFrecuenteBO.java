/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package interfaces;

import dto.ClienteDTO;
import java.util.List;

/**
 *
 * @author munos
 */
public interface IClienteFrecuenteBO {
    ClienteDTO registrar(ClienteDTO cliente);

    boolean eliminar(Long id);

    ClienteDTO editar(ClienteDTO cliente);
    
    List<ClienteDTO> obtenerClientes();
}