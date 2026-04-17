/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package InterfacesDAO;

import dto.ReporteClienteDTO;
import excepciones.PersistenciaException;
import java.util.List;

/**
 *
 * @author Jorge
 */
public interface IReportesClienteDAO {

    // metodo que obtiene todos los reportes de clientes
    List<ReporteClienteDTO> obtenerReporteClientes(String nombre, Integer minVisitas) throws PersistenciaException;

}
