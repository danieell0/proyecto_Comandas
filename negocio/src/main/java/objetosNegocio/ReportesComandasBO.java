/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package objetosNegocio;

import DAO.ReporteComandaDAO;
import dto.ReporteComandaDTO;
import excepciones.NegocioExcepcion;
import excepciones.PersistenciaException;
import interfaces.IReporteComandasBO;
import java.time.LocalDate;
import java.util.List;

/**
 *
 * @author munos
 */
public class ReportesComandasBO implements IReporteComandasBO{
    
    private ReporteComandaDAO reporteDAO = new ReporteComandaDAO();

    
    @Override
    public List<ReporteComandaDTO> obtenerReporteComandas(LocalDate fechaInicio, LocalDate fechaFin) throws NegocioExcepcion {
        try {
            validarFechas(fechaInicio, fechaFin);
            return reporteDAO.obtenerReporteComandas(fechaInicio, fechaFin);
        } catch (PersistenciaException e) {
            throw new NegocioExcepcion("Error al obtener el reporte de comandas");
        }
    }

    private void validarFechas(LocalDate fechaInicio, LocalDate fechaFin) throws NegocioExcepcion {

        if (fechaInicio == null || fechaFin == null) {
            throw new NegocioExcepcion("Las fechas no pueden estar vacías");
        }

        if (fechaInicio.isAfter(fechaFin)) {
            throw new NegocioExcepcion("La fecha inicio no puede ser mayor que la fecha fin");
        }
    }
}

