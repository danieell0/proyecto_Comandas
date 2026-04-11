/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package inicio;

import conexion.ConexionBD;
import entidades.Cliente;
import entidades.ClienteFrecuente;
import entidades.Comanda;
import entidades.DetalleProducto;
import entidades.EmpleadoMesero;
import entidades.Mesa;
import entidades.Producto;
import Enums.EstadoComandas;
import Enums.EstadoMesa;
import Enums.EstadoProducto;
import entidades.DetalleReceta;
import entidades.Ingrediente;
import Enums.TipoProducto;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import javax.persistence.EntityManager;


/**
 *
 * @author Jorge
 */
public class InsertMasivoComandas {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {

        EntityManager em = ConexionBD.crearConexion();
        Random random = new Random();

        try {
            em.getTransaction().begin();

            List<Mesa> mesas = new ArrayList<>();
            for (int i = 1; i <= 10; i++) {
                Mesa mesa = new Mesa("M-" + i, EstadoMesa.Disponible);
                em.persist(mesa);
                mesas.add(mesa);
            }

            List<EmpleadoMesero> meseros = new ArrayList<>();
            for (int i = 1; i <= 5; i++) {
                EmpleadoMesero m = new EmpleadoMesero(
                        (long) i,
                        "Mesero" + i,
                        "ApellidoP",
                        "ApellidoM",
                        "644123456" + i,
                        "Mesero",
                        8000L
                );
                em.persist(m);
                meseros.add(m);
            }

            List<Cliente> clientes = new ArrayList<>();
            for (int i = 1; i <= 5; i++) {
                Cliente c = new Cliente(
                        "Cliente" + i,
                        "ApellidoP",
                        "ApellidoM",
                        "64400000" + i,
                        LocalDate.now(),
                        "cliente" + i + "@mail.com"
                );
                em.persist(c);
                clientes.add(c);
            }

            ClienteFrecuente cf = new ClienteFrecuente(
                    100.0, 5, 2000.0,
                    "Frecuente",
                    "VIP",
                    "Cliente",
                    "6449999999",
                    LocalDate.now(),
                    "vip@mail.com"
            );
            em.persist(cf);
            clientes.add(cf);

            List<Ingrediente> ingredientes = new ArrayList<>();

            String[] nombresIng = {"Pan", "Carne", "Queso", "Lechuga", "Tomate", "Refresco", "Helado"};

            for (int i = 0; i < nombresIng.length; i++) {
                Ingrediente ing = new Ingrediente(
                        nombresIng[i],
                        "unidad",
                        100.0 
                );
                em.persist(ing);
                ingredientes.add(ing);
            }

            List<Producto> productos = new ArrayList<>();

            for (int i = 1; i <= 8; i++) {

                Producto p = new Producto(
                        "Producto" + i,
                        "Desc " + i,
                        50.0 + random.nextInt(100), 
                        TipoProducto.PLATILLO,
                        EstadoProducto.ACTIVO
                );

                int numIngredientes = 2 + random.nextInt(3);

                for (int j = 0; j < numIngredientes; j++) {

                    Ingrediente ing = ingredientes.get(random.nextInt(ingredientes.size()));

                    DetalleReceta dr = new DetalleReceta();
                    dr.setIngrediente(ing);
                    dr.setCantidadRequerida(1.0 + random.nextInt(3));

                    p.agregarIngrediente(dr);
                }

                em.persist(p);
                productos.add(p);
            }

            int folioCount = 1;

            for (int i = 0; i < 5; i++) {

                Mesa mesa = mesas.get(i);
                mesa.setEstado(EstadoMesa.Ocupada);

                Comanda comanda = new Comanda(
                        "OB-20260410-" + folioCount++,
                        LocalDateTime.now(),
                        0.0,
                        EstadoComandas.DISPONIBLE,
                        meseros.get(random.nextInt(meseros.size())),
                        clientes.get(random.nextInt(clientes.size())),
                        mesa
                );

                em.persist(comanda);

                double total = 0;

                int numDetalles = 1 + random.nextInt(3);

                for (int j = 0; j < numDetalles; j++) {

                    Producto p = productos.get(random.nextInt(productos.size()));
                    int cantidad = 1 + random.nextInt(3);

                    DetalleProducto det = new DetalleProducto(
                            cantidad,
                            p.getPrecio(), 
                            "Sin comentario",
                            comanda,
                            p
                    );

                    total += det.getSubtotal();

                    em.persist(det);
                }

                comanda.setTotalVenta(total);
            }

            em.getTransaction().commit();

            System.out.println("Insert masivo completado correctamente");

        } catch (Exception e) {
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            e.printStackTrace();
        } finally {
            em.close();
        }
    }
}

