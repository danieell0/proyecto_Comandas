/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import InterfacesDAO.IReportesClienteDAO;
import conexion.ConexionBD;
import dto.ReporteClienteDTO;
import excepciones.PersistenciaException;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

/**
 *
 * @author Jorge
 */
public class ReportesClienteDAO implements IReportesClienteDAO {

    
    
    @Override
    public List<ReporteClienteDTO> obtenerReporteClientes(String nombre, Integer minVisitas) throws PersistenciaException {
        
         EntityManager em = ConexionBD.crearConexion();
        try {
             String jpql="""
                    SELECT new dto.ReporteClienteDTO(c.nombre, COUNT(q.id), SUM(q.totalVenta), MAX(q.fechaHora))
                    FROM Cliente c
                    JOIN c.comandas q
                    WHERE (:nombre IS NULL OR c.nombre LIKE :nombre)
                    GROUP BY c.id, c.nombre
                    HAVING (:minVisitas IS NULL OR COUNT(q.id)>=:minVisitas)
                    """;
        
        
        TypedQuery<ReporteClienteDTO> query=em.createQuery(jpql,ReporteClienteDTO.class);
        
        if(nombre!=null && !nombre.isEmpty()){
            query.setParameter("nombre", "%" + nombre + "%");
        }else{
            query.setParameter("nombre", null); 
        }
        if(minVisitas!=null){
            query.setParameter("minVisitas", minVisitas);
        }else{
             query.setParameter("minVisitas", null);
        }
        return query.getResultList();
        } catch (Exception e) {
            e.printStackTrace();
            throw new PersistenciaException("Error al obtener el reporte de clientes: " + e.getMessage());
            //throw new PersistenciaException("Error al obtener el reporte de clientes");
        }finally{
            em.close();
        }
    }
    
    

}
