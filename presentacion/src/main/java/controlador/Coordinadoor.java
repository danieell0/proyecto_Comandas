/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controlador;

import DAO.ClienteDAO;
import dto.ClienteDTO;
import entidades.ClienteFrecuente;
import excepciones.NegocioExcepcion;
import excepciones.PersistenciaException;
import java.util.List;
import javax.swing.JOptionPane;
import objetosNegocio.ClienteBO;
import pantallas.ClienteFrecuenteFrame;

/**
 *
 * @author munos
 */
public class Coordinadoor {

    private static Coordinadoor cordinador;

    private ClienteBO clienteBO;

    private ClienteFrecuenteFrame frameCliente;

    private Coordinadoor() {
        clienteBO = new ClienteBO();
    }

    public static Coordinadoor getCoordinador() {
        if (cordinador == null) {
            cordinador = new Coordinadoor();
        }
        return cordinador;
    }

    public void guardarCliente(ClienteDTO cliente) {

        try {
            if (cliente.getId() == null) {
                clienteBO.registrar(cliente);
                JOptionPane.showMessageDialog(null, "Cliente registrado ");
            } else {
                clienteBO.editar(cliente);
                JOptionPane.showMessageDialog(null, "Cliente actualizado ");
            }

        } catch (NegocioExcepcion e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "Error", JOptionPane.WARNING_MESSAGE);
        }
    }

    public List<ClienteDTO> obtenerClientes() throws NegocioExcepcion {
        return clienteBO.obtenerClientes();
    }

    public List<ClienteDTO> buscarProductos(String filtro) {
        try {
            return clienteBO.consultarPorFiltro(filtro);
        } catch (NegocioExcepcion e) {
            JOptionPane.showMessageDialog(null, "Error en la búsqueda: " + e.getMessage());
            return null;
        }
    }

    public void eliminarClientes(Long idCliente) throws NegocioExcepcion {
        clienteBO.eliminar(idCliente);
    }
}
