/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controlador;

import Componentes.Sidebar;
import Componentes.barraBusqueda;
import Componentes.formClienteFrecuente;
import Componentes.panelSuperior;
import Componentes.tablaClientes;
import Enums.TipoAgregar;
import dto.ProductoDTO;
import javax.swing.JFrame;
import pantallas.ActualizarProductoFrame;
import pantallas.AgregarProductoFrame;
import pantallas.AgrergarFrame;
import pantallas.ClienteFrecuenteFrame;
import pantallas.ComandasFrame;
import pantallas.MenuPrincipalFrame;
import pantallas.MenuReportesFrame;
import pantallas.ProductosFrames;
import pantallas.ReporteClientesFrame;
import pantallas.ReportesComandasFrame;

/**
 *
 * @author Jorge
 */
public class controlDeNavegacion {

    private static controlDeNavegacion controlNavegacion;
    private JFrame frameActual;

    public void abrirMenuPrincipal() {
        Sidebar sidebar = new Sidebar();
        panelSuperior pa = new panelSuperior("Menú Principal");
        cambiarPantalla(new MenuPrincipalFrame(sidebar, pa));
    }

    public static controlDeNavegacion getcontrolNavegacion() {
        if (controlNavegacion == null) {
            controlNavegacion = new controlDeNavegacion();
        }
        return controlNavegacion;
    }

    public void abrirFrameBase() {
        Sidebar sliede = new Sidebar();
        formClienteFrecuente form = new formClienteFrecuente();
        panelSuperior pa = new panelSuperior("Clientes Frecuentes");
        tablaClientes tabla = new tablaClientes();
        barraBusqueda barrab = new barraBusqueda();
        cambiarPantalla(new ClienteFrecuenteFrame(sliede, form, pa, tabla, barrab));
    }

    public void cambiarPantalla(JFrame nuevoFrame) {
        if (frameActual != null) {
            frameActual.dispose();
        }

        frameActual = nuevoFrame;

        nuevoFrame.setVisible(true);
        nuevoFrame.setLocationRelativeTo(null);
    }

    public void abrirReportesComandas() {
        Sidebar sliede = new Sidebar();
        panelSuperior pa = new panelSuperior("Reportes Comandas");
        cambiarPantalla(new ReportesComandasFrame(pa, sliede));
    }

    public void abrirMenuReportes() {
        Sidebar sliede = new Sidebar();
        panelSuperior pa = new panelSuperior("Reportes");
        cambiarPantalla(new MenuReportesFrame(pa, sliede));
    }

    public void abrirReportesClientes() {
        Sidebar sliede = new Sidebar();
        panelSuperior pa = new panelSuperior("Reportes Clientes");
        cambiarPantalla(new ReporteClientesFrame(pa, sliede));
    }

    public void abrirComandas() {
        Sidebar sidebar = new Sidebar();
        panelSuperior pa = new panelSuperior("Comandas");
        cambiarPantalla(new ComandasFrame(sidebar, pa));
    }

    public void abrirProductos() {
        Sidebar sidebar = new Sidebar();
        panelSuperior pa = new panelSuperior("Productos");
        cambiarPantalla(new ProductosFrames(pa, sidebar));
    }

    public void abrirAgregarProductos() {
        Sidebar sidebar = new Sidebar();
        panelSuperior pa = new panelSuperior("Agregar Producto");
        cambiarPantalla(new AgregarProductoFrame(pa, sidebar));
    }

    public void abrirAgregarGenerico(String titulo, TipoAgregar tipo) {
        AgrergarFrame frame = new AgrergarFrame(titulo,tipo);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
    
    public void abrirActualizarProducto(ProductoDTO productoDTO){
        ActualizarProductoFrame apf=new ActualizarProductoFrame(productoDTO);
        apf.setLocationRelativeTo(null);
        apf.setVisible(true);
    }

}
