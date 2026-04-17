package controlador;

import dto.ProductoDTO;

/**
 * Interfaz que define el contrato para manejar la selección de un producto.
 * 
 * <p>Esta interfaz es utilizada para notificar cuando un {@link ProductoDTO}
 * ha sido seleccionado, permitiendo desacoplar la lógica de selección
 * de la lógica de procesamiento del producto.</p>
 * 
 * @author munos
 */
public interface ProductoSeleccionListener {

    /**
     * Método que se ejecuta cuando un producto es seleccionado.
     *
     * @param producto el objeto {@link ProductoDTO} que fue seleccionado
     */
    void productoSeleccionado(ProductoDTO producto);
}