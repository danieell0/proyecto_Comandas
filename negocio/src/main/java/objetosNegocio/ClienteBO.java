/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package objetosNegocio;

import DAO.ClienteDAO;
import Validadores.ValidadoresClientes;
import dto.ClienteDTO;
import interfaces.IClienteFrecuenteBO;
import java.util.List;


/**
 *
 * @author munos
 */
public class ClienteBO implements IClienteFrecuenteBO{
    private ClienteDAO clienteFrecuenteDAO = new ClienteDAO();

    @Override
    public ClienteDTO registrar(ClienteDTO cliente) {
        if (!ValidadoresClientes.nombre(cliente.getNombre())) {
            throw new RuntimeException("Nombre inválido");
        }

        if (!ValidadoresClientes.apellidoPaterno(cliente.getApellidoPaterno())) {
            throw new RuntimeException("Apellido paterno inválido");
        }

        if (!ValidadoresClientes.apellidoMaterno(cliente.getApellidoMaterno())) {
            throw new RuntimeException("Apellido materno inválido");
        }

        if (!ValidadoresClientes.telefono(cliente.getTelefono())) {
            throw new RuntimeException("Teléfono inválido");
        }

        if (!ValidadoresClientes.correo(cliente.getCorreoElectronico())) {
            throw new RuntimeException("Correo inválido");
        }
        return cliente;
    }

    @Override
    public boolean eliminar(Long id) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public ClienteDTO editar(ClienteDTO cliente) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public List<ClienteDTO> obtenerClientes() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
}

