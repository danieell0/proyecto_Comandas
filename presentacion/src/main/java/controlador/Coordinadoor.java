/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controlador;

import DAO.ClienteDAO;
import entidades.ClienteFrecuente;
import excepciones.PersistenciaException;
import java.util.List;

/**
 *
 * @author munos
 */
public class Coordinadoor {
    private static Coordinadoor coordinador;


    private ClienteDAO clienteDAO;

    private Coordinadoor() {
        clienteDAO = new ClienteDAO(); 
    }

    public static Coordinadoor getCoordinador() {
        if (coordinador == null) {
            coordinador = new Coordinadoor();
        }
        return coordinador;
    }


    public List<ClienteFrecuente> obtenerClientes() throws PersistenciaException {
        return clienteDAO.obtenerClientes();
    }

    public ClienteFrecuente editarCliente(ClienteFrecuente cliente) throws PersistenciaException {
        return clienteDAO.editar(cliente);
    }
}
