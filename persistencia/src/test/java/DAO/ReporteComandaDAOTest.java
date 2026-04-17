/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package DAO;

import Enums.EstadoComandas;
import dto.ReporteComandaDTO;
import entidades.Cliente;
import entidades.Comanda;
import entidades.EmpleadoMesero;
import entidades.Mesa;
import excepciones.PersistenciaException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;

/**
 *
 * @author Benjamin
 */
public class ReporteComandaDAOTest {
    
    private ReporteComandaDAO reporteDAO;
    private ComandaDAO comandaDAO;
    private ClienteDAO clienteDAO;

    @BeforeEach
    public void setUp() {
        reporteDAO = new ReporteComandaDAO();
        comandaDAO = new ComandaDAO();
        clienteDAO = new ClienteDAO();
    }


    private Mesa obtenerMesaExistente() {
        Mesa mesa = new Mesa();
        mesa.setId(1L); 
        return mesa;
    }

    private Cliente obtenerClienteExistente() throws PersistenciaException {
        return clienteDAO.buscarPorId(1L); 
    }

    private EmpleadoMesero obtenerMeseroExistente() {
        EmpleadoMesero mesero = new EmpleadoMesero();
        mesero.setId(1L); 
        return mesero;
    }

    private Comanda crearComandaValida() throws PersistenciaException {
        Comanda c = new Comanda();

        c.setEstado(EstadoComandas.ABIERTA);
        c.setFechaHora(LocalDateTime.now());
        c.setFolio("REP-" + System.currentTimeMillis());
        c.setTotalVenta(100.0);

        c.setMesa(obtenerMesaExistente());
        c.setCliente(obtenerClienteExistente());
        c.setMesero(obtenerMeseroExistente()); 

        return c;
    }


    /**
     * 
     * Debe regresar al menos una comanda en el rango
     */
    @Test
    public void testObtenerReporteComandas_OK() throws PersistenciaException {

        Comanda comanda = crearComandaValida();
        comandaDAO.guardar(comanda);

        LocalDate hoy = LocalDate.now();

        List<ReporteComandaDTO> resultado =
                reporteDAO.obtenerReporteComandas(hoy.minusDays(1), hoy.plusDays(1));

        assertNotNull(resultado);
        assertFalse(resultado.isEmpty());

        ReporteComandaDTO dto = resultado.get(0);

        assertNotNull(dto.getFecha());
        assertNotNull(dto.getHora());
        assertTrue(dto.getTotalVenta() >= 0);
        assertNotNull(dto.getEstado());
    }

    /**
     * 
     * No debe regresar resultados si no hay comandas
     */
    @Test
    public void testObtenerReporteComandas_SinResultados() throws PersistenciaException {

        LocalDate fechaInicio = LocalDate.of(2000, 1, 1);
        LocalDate fechaFin = LocalDate.of(2000, 1, 2);

        List<ReporteComandaDTO> resultado =
                reporteDAO.obtenerReporteComandas(fechaInicio, fechaFin);

        assertNotNull(resultado);
        assertTrue(resultado.isEmpty());
    }
    
}
