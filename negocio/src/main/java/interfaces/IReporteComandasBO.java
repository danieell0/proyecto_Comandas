/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package interfaces;

import dto.ReporteComandaDTO;
import excepciones.NegocioExcepcion;
import java.time.LocalDate;
import java.util.List;

/**
 *
 * @author munos
 */
public interface IReporteComandasBO {    
        List<ReporteComandaDTO> obtenerReporteComandas(LocalDate fechaInicio, LocalDate fechaFin) throws NegocioExcepcion;

}
