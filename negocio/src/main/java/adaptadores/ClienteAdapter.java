/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package adaptadores;

import dto.ClienteDTO;
import entidades.Cliente;
import entidades.ClienteFrecuente;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author munos
 */
public class ClienteAdapter {
    
    public static ClienteDTO entidadADTO(ClienteFrecuente cliente) {
        if (cliente == null) {
            return null;
        }
        return new ClienteDTO(
                cliente.getId(),
                cliente.getNombre(),
                cliente.getApellidoPaterno(),
                cliente.getApellidoMaterno(),
                cliente.getTelefono(),
                cliente.getCorreoElectronico(),
                cliente.getFechaRegistro(),
                cliente.getPuntosFidelidad(),
                cliente.getNumeroVisitas()
        );
    }
    public static ClienteFrecuente dtoAEntidad(ClienteDTO dto) {
        if (dto == null) {
            return null;
        }
        ClienteFrecuente cliente = new ClienteFrecuente();
        cliente.setId(dto.getId());
        cliente.setNombre(dto.getNombre());
        cliente.setApellidoPaterno(dto.getApellidoPaterno());
        cliente.setApellidoMaterno(dto.getApellidoMaterno());
        cliente.setTelefono(dto.getTelefono());
        cliente.setCorreoElectronico(dto.getCorreoElectronico());
        return cliente;
    }
    public static List<ClienteDTO> listaEntidadDTO(List<ClienteFrecuente> clientes) {
        List<ClienteDTO> dtos = new ArrayList<>();
        for (ClienteFrecuente p : clientes) {
            dtos.add(entidadADTO(p));
        }
        return dtos;
    }
}
