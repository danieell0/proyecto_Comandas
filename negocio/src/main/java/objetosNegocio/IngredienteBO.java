/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package objetosNegocio;

import InterfacesDAO.IIngredienteDAO;
import Mappers.IngredienteMapper;
import dto.IngredienteDTO;
import entidades.Ingrediente;
import excepciones.NegocioExcepcion;
import excepciones.PersistenciaException;
import interfaces.IIngredienteBO;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Jorge
 */
public class IngredienteBO implements IIngredienteBO {

    private IIngredienteDAO ingredienteDAO;

    private static final Logger logger = Logger.getLogger(IngredienteBO.class.getName());

    public IngredienteBO(IIngredienteDAO ingredienteDAO) {
        this.ingredienteDAO = ingredienteDAO;
    }

    @Override
    public List<IngredienteDTO> obtenerIngredientes() throws NegocioExcepcion {
        try {
            List<Ingrediente> lista = ingredienteDAO.obtenerIngredientes();
            List<IngredienteDTO> listaDTO = new ArrayList<>();
            for (Ingrediente i : lista) {
                IngredienteDTO e = IngredienteMapper.toDTO(i);
                listaDTO.add(e);
            }
            return listaDTO;
        } catch (PersistenciaException e) {
            logger.log(Level.SEVERE, "Error al obtener los ingredientes en la BO", e);
            throw new NegocioExcepcion("Error al obtener la lista de ingredientes en la BO", e);
        }
    }

    @Override
    public List<IngredienteDTO> buscarPorNombre(String nombre) throws NegocioExcepcion {
        try {
            List<Ingrediente> lista = ingredienteDAO.buscarPorNombre(nombre);
            List<IngredienteDTO> dtos = new ArrayList<>();
            for (Ingrediente i : lista) {
                dtos.add(IngredienteMapper.toDTO(i));
            }
            return dtos;
        } catch (PersistenciaException e) {
            logger.log(Level.SEVERE, "Error al consultar por nombre en la BO", e);
            throw new NegocioExcepcion("Error al consultar por nombre en la BO", e);
        }
    }

}
