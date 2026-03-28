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
 *Clase utilitaria que implementa el patrón de diseño Adaptador (Mapper).
 * Provee métodos estáticos para transformar de manera bidireccional los datos 
 * entre la capa de Presentación (DTOs) y la capa de Dominio (Entidades JPA).
 * Al usar métodos estáticos, se optimiza la memoria ya que no es necesario 
 * instanciar esta clase para utilizar sus funciones de traducción.
 * @author munos
 */
public class ClienteAdapter {
    
    /**
     * Convierte una entidad cliente sacada de la base de datos a un
     * dto para su traslado a traves del sistema 
     * @param cliente entidad cliente frecuente a convertir a dto
     * @return regresa el dto si el cliente del parametro no es null, regresa null en caso contrario
     */
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
    
    /**
     * Convierte un dto a una entidad cliente frecuente
     * @param dto 
     * @return 
     */
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
    
    /**
     * convierte una lista de entidades cliente a una lista de dtos 
     * poblados con los datos de esas entidades dadas
     * @param clientes lista de entidades cliente frecuente
     * @return lista de dtos de los clientes 
     */
    public static List<ClienteDTO> listaEntidadDTO(List<ClienteFrecuente> clientes) {
        List<ClienteDTO> dtos = new ArrayList<>();
        for (ClienteFrecuente p : clientes) { //que pasa si la lista es null?, no deberiamos validar eso aqui?
            dtos.add(entidadADTO(p));
        }
        return dtos;
    }
}
