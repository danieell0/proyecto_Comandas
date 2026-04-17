/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package interfaces;

import dto.ReporteClienteDTO;
import excepciones.NegocioExcepcion;
import java.util.List;

/**
 *
 * @author Benjamin
 */
public interface IReportesBO {
    
    public List<ReporteClienteDTO> obtenerReporteClientes(String nombre, Integer minVisitas) throws NegocioExcepcion;
    
    
    
    
    
}
