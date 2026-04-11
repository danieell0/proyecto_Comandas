/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import InterfacesDAO.IReporteComandaDAO;
import conexion.ConexionBD;
import dto.ReporteComandaDTO;
import entidades.Comanda;
import excepciones.PersistenciaException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

/**
 *
 * @author munos
 */
public class ReporteComandaDAO implements IReporteComandaDAO{

    @Override
    public List<ReporteComandaDTO> obtenerReporteComandas(LocalDate fechaInicio, LocalDate fechaFin) throws PersistenciaException{
                
    EntityManager em = ConexionBD.crearConexion();

    try {
        LocalDateTime inicio = fechaInicio.atStartOfDay();
        LocalDateTime fin = fechaFin.atTime(23, 59, 59);

        String jpql = "SELECT c FROM Comanda c WHERE c.fechaHora BETWEEN :inicio AND :fin";

        TypedQuery<Comanda> query = em.createQuery(jpql, Comanda.class);
        query.setParameter("inicio", inicio);
        query.setParameter("fin", fin);

        List<Comanda> listaComandas = query.getResultList();

        List<ReporteComandaDTO> listaDTO = new ArrayList<>();
        for (Comanda c : listaComandas) {
            String nombreCliente;
            if (c.getCliente() != null) {
                nombreCliente = c.getCliente().getNombre();
            } else {
                nombreCliente = "No cliente";
            }
            ReporteComandaDTO dto = new ReporteComandaDTO(
                    c.getFechaHora().toLocalDate(),
                    c.getFechaHora().toLocalTime(),
                    c.getMesa().getNumero(),
                    c.getTotalVenta(),
                    c.getEstado().name(),
                    nombreCliente
            );
            listaDTO.add(dto);
        }
        return listaDTO;
    } catch (Exception e) {
        throw new PersistenciaException("Error al obtener el reporte de comandas", e);
    } finally {
        em.close();
    }
}
    
}
