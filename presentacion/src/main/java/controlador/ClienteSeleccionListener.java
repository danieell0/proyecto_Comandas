package controlador;

import dto.ClienteDTO;

/**
 * Interfaz que define un listener para la selección de clientes.
 * 
 * <p>Se utiliza principalmente en la capa de presentación para manejar
 * eventos cuando un cliente es seleccionado en componentes de interfaz
 * gráfica (por ejemplo: tablas, listas o formularios).</p>
 * 
 * <p>Permite desacoplar la lógica de selección del cliente de la lógica
 * que debe ejecutarse posteriormente.</p>
 * 
 * @author munos
 */
public interface ClienteSeleccionListener {

    /**
     * Método que se ejecuta cuando un cliente es seleccionado.
     * 
     * @param cliente objeto {@link ClienteDTO} seleccionado
     */
    void clienteSeleccionado(ClienteDTO cliente);
}
