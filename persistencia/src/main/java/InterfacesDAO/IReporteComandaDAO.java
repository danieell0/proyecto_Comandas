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
        // metodo que obtiene todos los reportes individuales (lineas de informacion) y los regresa en una lista listos para mostrarlos
        List<ReporteComandaDTO> obtenerReporteComandas(LocalDate fechaInicio, LocalDate fechaFin) throws PersistenciaException;

}
