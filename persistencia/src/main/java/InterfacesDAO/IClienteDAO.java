/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package InterfacesDAO;

import entidades.Cliente;
import entidades.ClienteFrecuente;
import excepciones.PersistenciaException;
import java.util.List;

/**
 *
 * @author Jorge
 */
public interface IClienteDAO {
    
    public Cliente guardarCliente(Cliente cliente) throws PersistenciaException;
    
    public boolean eliminarCliente(int idCliente) throws PersistenciaException;
    
    public List<ClienteFrecuente> obtenerClientes() throws PersistenciaException;
    
    public ClienteFrecuente editar(ClienteFrecuente Cliente) throws PersistenciaException;
    
}
