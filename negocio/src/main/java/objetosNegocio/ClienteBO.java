/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package objetosNegocio;

import DAO.ClienteDAO;
import Validadores.ValidadoresClientes;
import adaptadores.ClienteAdapter;
import dto.ClienteDTO;
import entidades.Cliente;
import entidades.ClienteFrecuente;
import excepciones.NegocioExcepcion;
import excepciones.PersistenciaException;
import interfaces.IClienteFrecuenteBO;
import java.time.LocalDate;
import java.util.List;

/**
 * Objeto de negocio para la gestion de clientes frecuentes 
 * toda la logica de negocio en relazion a clientes, actua como intermediario
 * entre la presentacion y la persistencia 
 * @author munos
 */
public class ClienteBO implements IClienteFrecuenteBO {

    private ClienteDAO clienteDAO = new ClienteDAO();

    /**
     * Registra un nuevo cliente frecuente en el sistema aplicando las reglas de validación.
     * Si el cliente es nuevo, se le asigna automáticamente la fecha actual y 0 puntos iniciales.
     * * @param clienteDTO El objeto con los datos capturados en la pantalla.
     * @return El ClienteDTO guardado, ya con su ID generado por la base de datos.
     * @throws NegocioExcepcion Si el cliente no cumple con las reglas (ej. teléfono vacío).
     */
    @Override
    public void registrar(ClienteDTO cliente) throws NegocioExcepcion {
        try {
            validarDatos(cliente);

            ClienteFrecuente entidad = ClienteAdapter.dtoAEntidad(cliente);
            entidad.setFechaRegistro(LocalDate.now());
            entidad.setPuntosFidelidad(0.0);
            entidad.setNumeroVisitas(0);

            clienteDAO.guardarCliente(entidad);

        } catch (PersistenciaException e) {
            throw new NegocioExcepcion("Error al registrar cliente");
        }
    }
   
    
    /**
     * NO IMPLEMENTADO AUN
     * 
     * Calcula y actualiza los puntos de fidelidad de un cliente basado en el total de su comanda.
     * La regla de negocio estipula que cada 20 pesos de gasto generan 1 punto de fidelidad.
     * * @param idCliente El identificador único del cliente frecuente.
     * @param totalGasto El monto total en pesos de la comanda actual.
     * @throws NegocioExcepcion Si ocurre un error al actualizar los datos.
     */
    public void agregarPuntosPorCompra(Long idCliente, Double totalGasto) throws NegocioExcepcion {
        try {
            // Lógica de Negocio: Calcular puntos (20 pesos = 1 punto)
            Double puntosGanados = totalGasto / 20.0;
            
            // Aquí iría el código para buscar al cliente en el DAO, 
            // sumarle los puntosGanados a sus puntosFidelidad actuales, 
            // aumentarle el numeroVisitas + 1, y mandarlo a editar() al DAO.
            
        } catch (Exception e) {
             throw new NegocioExcepcion("No se pudieron actualizar los puntos del cliente.");
        }
    }

    /**
     * Elimina un cliente frecuente del sistema basándose en su identificador único.
     * * @param id El ID numérico del cliente a eliminar.
     * @return true si el cliente fue eliminado correctamente.
     * @throws NegocioExcepcion Si el ID es nulo, el cliente no existe, o hay un error de conexión.
     */
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

    /**
     * Actualiza la información personal de un cliente existente.
     * Verifica que el DTO contenga un ID válido antes de intentar la sobreescritura.
     * * @param cliente Objeto DTO con los datos modificados.
     * @throws NegocioExcepcion Si el cliente es nulo, no tiene ID, o falla la validación de formato.
     */
    @Override
    public void editar(ClienteDTO cliente) throws NegocioExcepcion {
        try {
            if (cliente == null) {
                throw new NegocioExcepcion("Error el cliente esta vacio");
            }
            if (cliente.getId() == null) {
                throw new NegocioExcepcion("No se puede actualizar un cliente sin ID");
            }
            validarDatos(cliente);
            ClienteFrecuente entidad = ClienteAdapter.dtoAEntidad(cliente);
            clienteDAO.editar(entidad);
        } catch (PersistenciaException e) {
            throw new NegocioExcepcion("Error al actualizar el cliente");
        }

    }

    /**
     * Recupera el catálogo completo de clientes frecuentes registrados.
     * * @return Una lista de {@link ClienteDTO} lista para ser mostrada en tablas.
     * @throws NegocioExcepcion Si la consulta a la base de datos falla.
     */
    @Override
    public List<ClienteDTO> obtenerClientes() throws NegocioExcepcion {
        try {
            List<ClienteFrecuente> clientes = clienteDAO.obtenerClientes();
            return ClienteAdapter.listaEntidadDTO(clientes);
        } catch (PersistenciaException e) {
            throw new NegocioExcepcion("Error al obtener el clientes");
        }
    }
    
    /**
     * Busca clientes que coincidan con un criterio de texto específico (nombre, teléfono, etc.).
     * Si el filtro es nulo, realiza una búsqueda limpia.
     * * @param filtro Texto introducido por el usuario en la barra de búsqueda.
     * @return Lista de DTOs que coinciden con el filtro.
     * @throws NegocioExcepcion Si ocurre un error durante la búsqueda en la BD.
     */
    public List<ClienteDTO> consultarPorFiltro(String textoBusqueda) throws NegocioExcepcion {
        try {
            List<entidades.ClienteFrecuente> listaFrecuentes = clienteDAO.obtenerClientes();
            List<ClienteDTO> resultados = new java.util.ArrayList<>();
            
            // 1. Limpiamos el texto que escribió el usuario (todo a minúsculas)
            String filtro = (textoBusqueda == null) ? "" : textoBusqueda.toLowerCase().trim();

            for (entidades.ClienteFrecuente c : listaFrecuentes) {
                
                // 2. Comparamos los campos (¡Revisa que tengas la línea del correo!)
                boolean coincideNombre = c.getNombre().toLowerCase().contains(filtro);
                boolean coincideTelefono = c.getTelefono() != null && c.getTelefono().contains(filtro);
                
                // ---> LA PIEZA FALTANTE: Validar el correo a minúsculas <---
                boolean coincideCorreo = c.getCorreoElectronico() != null && c.getCorreoElectronico().toLowerCase().contains(filtro);

                // 3. EL IF MÁGICO: Debe incluir coincideCorreo
                if (coincideNombre || coincideTelefono || coincideCorreo) {
                    
                    resultados.add(new ClienteDTO(
                        c.getId(),
                        c.getNombre(),
                        c.getApellidoPaterno(),
                        c.getApellidoMaterno(),
                        c.getTelefono(),
                        c.getCorreoElectronico(),
                        c.getFechaRegistro(),
                        c.getPuntosFidelidad(),
                        c.getNumeroVisitas()
                    ));
                }
            }
            return resultados;
            
        } catch (Exception ex) {
            throw new NegocioExcepcion("Error al filtrar clientes: " + ex.getMessage());
        }
    }


    public void validarDatos(ClienteDTO cliente) throws NegocioExcepcion {
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
