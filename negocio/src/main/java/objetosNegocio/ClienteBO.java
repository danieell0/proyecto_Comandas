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
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
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

    /**
     * Registra un cliente frecuente en el sistema.
     * 
     * @param dto Contenedor con los datos del cliente.
     * @throws NegocioException Si los datos son inválidos o falla la persistencia.
     */
    }