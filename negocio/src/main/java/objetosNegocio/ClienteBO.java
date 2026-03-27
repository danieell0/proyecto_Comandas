/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package objetosNegocio;

import DAO.ClienteDAO;
import Validadores.ValidadoresClientes;
import adaptadores.ClienteAdapter;
import dto.ClienteDTO;
import entidades.ClienteFrecuente;
import excepciones.NegocioExcepcion;
import excepciones.PersistenciaException;
import interfaces.IClienteFrecuenteBO;
import java.time.LocalDate;
import java.util.List;

/**
 *
 * @author munos
 */
public class ClienteBO implements IClienteFrecuenteBO {

    private ClienteDAO clienteDAO = new ClienteDAO();

    @Override
    public void registrar(ClienteDTO cliente) throws NegocioExcepcion {
        try {
            validadrDatos(cliente);

            ClienteFrecuente entidad = ClienteAdapter.dtoAEntidad(cliente);
            entidad.setFechaRegistro(LocalDate.now());
            entidad.setPuntosFidelidad(0.0);
            entidad.setNumeroVisitas(0);

            clienteDAO.guardarCliente(entidad);

        } catch (PersistenciaException e) {
            throw new NegocioExcepcion("Error al registrar cliente");
        }
    }

    @Override
    public boolean eliminar(Long id) throws NegocioExcepcion {
        try {
            if (id == null) {
                throw new NegocioExcepcion("Error el id esta vacio");
            }
            boolean eliminado = clienteDAO.eliminarCliente(id);
            if (!eliminado) {
                throw new NegocioExcepcion("Error, no se pudo eliminar el cliente");
            }
            return true;
        } catch (PersistenciaException e) {
            throw new NegocioExcepcion("Error al eliminar el  cliente");
        }
    }

    @Override
    public void editar(ClienteDTO cliente) throws NegocioExcepcion {
        try {
            if (cliente == null) {
                throw new NegocioExcepcion("Error el cliente esta vacio");
            }
            if (cliente.getId() == null) {
                throw new NegocioExcepcion("No se puede actualizar un cliente sin ID");
            }
            validadrDatos(cliente);
            ClienteFrecuente entidad = ClienteAdapter.dtoAEntidad(cliente);
            clienteDAO.editar(entidad);
        } catch (PersistenciaException e) {
            throw new NegocioExcepcion("Error al actualizar el cliente");
        }

    }

    @Override
    public List<ClienteDTO> obtenerClientes() throws NegocioExcepcion {
        try {
            List<ClienteFrecuente> clientes = clienteDAO.obtenerClientes();
            return ClienteAdapter.listaEntidadADTO(clientes);
        } catch (PersistenciaException e) {
            throw new NegocioExcepcion("Error al obtener el clientes");
        }
    }

    public void validadrDatos(ClienteDTO cliente) throws NegocioExcepcion {
        if (cliente == null) {
            throw new NegocioExcepcion("El cliente no puede ser nulo");
        }
        if (!ValidadoresClientes.nombre(cliente.getNombre())) {
            throw new NegocioExcepcion("Nombre inválido");
        }
        if (!ValidadoresClientes.apellidoPaterno(cliente.getApellidoPaterno())) {
            throw new NegocioExcepcion("Apellido paterno invalido");
        }
        if (!ValidadoresClientes.apellidoMaterno(cliente.getApellidoMaterno())) {
            throw new NegocioExcepcion("Apellido Materno invalido");
        }
        if (!ValidadoresClientes.telefono(cliente.getTelefono())) {
            throw new NegocioExcepcion("Teléfono inválido");
        }
        if (!ValidadoresClientes.correo(cliente.getCorreoElectronico())) {
            throw new NegocioExcepcion("Correo invalido");
        }
    }

}
