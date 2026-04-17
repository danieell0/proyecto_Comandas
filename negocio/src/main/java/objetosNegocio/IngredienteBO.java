/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package objetosNegocio;

import DAO.IngredienteDAO;
import InterfacesDAO.IIngredienteDAO;
import dto.IngredienteDTO;
import entidades.Ingrediente;
import excepciones.NegocioExcepcion;
import excepciones.PersistenciaException;
import interfaces.IIngredienteBO;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Benjamin
 */
public class IngredienteBO implements IIngredienteBO{
    
    // DESACOPLAMIENTO ponemos tipo de interfaz para que no dependa de la clase dao
    private IIngredienteDAO ingredienteDAO;
    
    public IngredienteBO(){
        this.ingredienteDAO = new IngredienteDAO();
    }
    
    /**
     * Valida y envía el DTO para ser guardado en la Base de Datos.
     */
    @Override
    public void agregarIngrediente(IngredienteDTO dto) throws NegocioExcepcion {
        // 1. Validaciones extra de negocio (por si se pasaron de la vista)
        if (dto.getNombre() == null || dto.getNombre().trim().isEmpty()) {
            throw new NegocioExcepcion("El nombre del ingrediente no puede estar vacío.");
        }
        if (dto.getCantidadActual() < 0) {
            throw new NegocioExcepcion("El stock no puede ser negativo.");
        }

        // 2. Transformar DTO a Entidad
        Ingrediente entidad = new Ingrediente(
                dto.getNombre(), 
                dto.getUnidadMedida(), 
                dto.getCantidadActual(), 
                dto.getRutaImagen()
        );

        try{
        // 3. Mandar al DAO (El DAO lanzará error si está duplicado)
        ingredienteDAO.agregarIngrediente(entidad);
        }catch(PersistenciaException ex){
            throw new NegocioExcepcion(ex.getMessage());
        }
    }

    /**
     * Obtiene los ingredientes del DAO y los empaqueta como DTOs para la vista.
     */
    @Override
    public List<IngredienteDTO> obtenerTodosLosIngredientes() throws NegocioExcepcion {
        try {
            List<Ingrediente> entidades = ingredienteDAO.consultarTodos();
            List<IngredienteDTO> dtos = new ArrayList<>();
            
            // Transformar cada Entidad en DTO
            for (Ingrediente entidad : entidades) {
                dtos.add(new IngredienteDTO(
                        entidad.getId(),
                        entidad.getNombre(),
                        entidad.getUnidadMedida(),
                        entidad.getCantidadActual(),
                        entidad.getRutaImagen()
                ));
            }
            return dtos;
        } catch (PersistenciaException e) {
            throw new NegocioExcepcion("Error al obtener los ingredientes: " + e.getMessage());
        }
    }
    
    /**
     * Metodo que elimina al ingrediente con las reglas de negocio apropiadas y llama 
     * al metodo dao responsable de quitarlo de la bd
     * @param id
     * @throws NegocioExcepcion 
     */
    @Override
    public void eliminarIngrediente(Long id) throws NegocioExcepcion {
        try {
            ingredienteDAO.borrarIngrediente(id);
        } catch (PersistenciaException ex) {
            throw new NegocioExcepcion("Error de negocio al intentar eliminar: " + ex.getMessage());
        }
    }
    
    @Override
    public java.util.List<IngredienteDTO> buscarIngredientes(String nombre, String unidad) throws NegocioExcepcion {
        try {
            java.util.List<Ingrediente> entidades = ingredienteDAO.buscarIngredientes(nombre, unidad);
            java.util.List<IngredienteDTO> dtos = new java.util.ArrayList<>();
            
            for (Ingrediente entidad : entidades) {
                dtos.add(new IngredienteDTO(
                        entidad.getId(),
                        entidad.getNombre(),
                        entidad.getUnidadMedida(),
                        entidad.getCantidadActual(),
                        entidad.getRutaImagen()
                ));
            }
            return dtos;
        } catch (PersistenciaException e) {
            throw new NegocioExcepcion("Error en la búsqueda: " + e.getMessage());
        }
    }
    
    @Override
    public void actualizarStock(Long id, Double nuevoStock) throws NegocioExcepcion {
        // Regla de negocio: El inventario no puede ser menor a cero
        if (nuevoStock < 0) {
            throw new NegocioExcepcion("El stock no puede ser negativo.");
        }
        
        try {
            // El DAO ya tiene el método que programamos anteriormente
            ingredienteDAO.actualizarStock(id, nuevoStock);
        } catch (PersistenciaException ex) {
            throw new NegocioExcepcion("No se pudo actualizar el inventario: " + ex.getMessage());
        }
    }
}
