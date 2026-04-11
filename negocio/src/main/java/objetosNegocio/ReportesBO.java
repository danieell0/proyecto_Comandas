/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package objetosNegocio;

import DAO.ReportesClienteDAO;
import InterfacesDAO.IReportesClienteDAO;
import dto.ReporteClienteDTO;
import excepciones.NegocioExcepcion;
import excepciones.PersistenciaException;
import java.util.List;

/**
 * Objeto de negocio encargado de la logica y la validacion para los reportes 
 * @author Benjamin
 */
public class ReportesBO {
    private IReportesClienteDAO reportesDAO;

    public ReportesBO() {
        // Inicializamos la conexion con la capa de datos
        this.reportesDAO = new ReportesClienteDAO();
    }

    /**
     * Valida los filtros y solicita el reporte de clientes a la base de datos.
     * * @param nombre Parte del nombre del cliente (opcional).
     * @param minVisitas Número mínimo de visitas (opcional).
     * @return Lista de DTOs con los datos calculados listos para la tabla.
     * @throws NegocioExcepcion Si hay errores de validación o de base de datos.
     */
    public List<ReporteClienteDTO> obtenerReporteClientes(String nombre, Integer minVisitas) throws NegocioExcepcion {
        try {
            // Regla de negocio básica: Las visitas no pueden ser negativas
            if (minVisitas != null && minVisitas < 0) {
                throw new NegocioExcepcion("El número mínimo de visitas no puede ser menor a cero.");
            }

            // Como el DAO ya nos devuelve DTOs listos, ¡es un pase directo!
            return reportesDAO.obtenerReporteClientes(nombre, minVisitas);

        } catch (PersistenciaException e) {
            // Enmascaramos el error de base de datos para la pantalla
            throw new NegocioExcepcion("Hubo un error al generar el reporte: " + e.getMessage());
        }
    }
}
