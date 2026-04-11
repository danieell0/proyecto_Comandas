/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package InterfacesDAO;

import dto.ReporteComandaDTO;
import excepciones.PersistenciaException;
import java.time.LocalDate;
import java.util.List;

/**
 *
 * @author munos
 */
public interface IReporteComandaDAO {
    
        List<ReporteComandaDTO> obtenerReporteComandas(LocalDate fechaInicio, LocalDate fechaFin) throws PersistenciaException;

}
