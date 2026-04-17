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
 * Clase de Objeto de Negocio (Business Object) encargada de procesar la lógica 
 * para la generación de reportes de comandas.
 * Valida las reglas de negocio antes de consultar la capa de acceso a datos.
 * 
 */
public class ReportesComandasBO implements IReporteComandasBO{
    
    private ReporteComandaDAO reporteDAO = new ReporteComandaDAO();

    /**
     * Obtiene una lista de reportes de comandas dentro de un rango de fechas.
     * * @param fechaInicio Fecha inicial del periodo a consultar.
     * @param fechaFin Fecha final del periodo a consultar.
     * @return Lista de objetos {@link ReporteComandaDTO} con los datos del reporte.
     * @throws NegocioExcepcion Si las fechas son inválidas o ocurre un error en la BD.
     */
    @Override
    public List<ReporteComandaDTO> obtenerReporteComandas(LocalDate fechaInicio, LocalDate fechaFin) throws NegocioExcepcion {
        try {
            validarFechas(fechaInicio, fechaFin);
            return reporteDAO.obtenerReporteComandas(fechaInicio, fechaFin);
        } catch (PersistenciaException e) {
            throw new NegocioExcepcion("Error al obtener el reporte de comandas");
        }
    }
    
    /**
     * Verifica que el rango de fechas sea lógico y no contenga valores nulos.
     * * @param fechaInicio Fecha de inicio a evaluar.
     * @param fechaFin Fecha final a evaluar.
     * @throws NegocioExcepcion Si alguna fecha es nula o la fecha de inicio es posterior a la de fin.
     */
    private void validarFechas(LocalDate fechaInicio, LocalDate fechaFin) throws NegocioExcepcion {

        if (fechaInicio == null || fechaFin == null) {
            throw new NegocioExcepcion("Las fechas no pueden estar vacías");
        }

        if (fechaInicio.isAfter(fechaFin)) {
            throw new NegocioExcepcion("La fecha inicio no puede ser mayor que la fecha fin");
        }
    }
}

