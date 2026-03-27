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
import javax.swing.JFrame;
import pantallas.ClienteFrecuenteFrame;

/**
 *
 * @author Jorge
 */
public class controlDeNavegacion {

    private static controlDeNavegacion controlNavegacion;
    private JFrame frameActual;

    public static controlDeNavegacion getcontrolNavegacion() {
        if (controlNavegacion == null) {
            controlNavegacion = new controlDeNavegacion();
        }
        return controlNavegacion;
    }

    public void abrirFrameBase() {
        Sidebar sliede=new Sidebar();
        formClienteFrecuente form=new formClienteFrecuente();
        panelSuperior pa=new panelSuperior("Clientes Frecuentes");
        tablaClientes tabla=new tablaClientes();
        barraBusqueda barrab=new barraBusqueda();
        cambiarPantalla(new ClienteFrecuenteFrame(sliede,form,pa,tabla,barrab));
    }

    public void cambiarPantalla(JFrame nuevoFrame) {
        if (frameActual != null) {
            frameActual.dispose();
        }

        frameActual = nuevoFrame;

        nuevoFrame.setVisible(true);
        nuevoFrame.setLocationRelativeTo(null);
    }

}
