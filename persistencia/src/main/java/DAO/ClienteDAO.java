/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import InterfacesDAO.IClienteDAO;
import com.mysql.cj.QueryReturnType;
import conexion.ConexionBD;
import entidades.Cliente;
import entidades.ClienteFrecuente;
import excepciones.PersistenciaException;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceException;
import javax.persistence.TypedQuery;

/**
 *
 * @author munos
 */
public class ClienteDAO implements IClienteDAO {

    @Override
    public ClienteFrecuente editar(ClienteFrecuente cliente) throws PersistenciaException{
        EntityManager em = ConexionBD.crearConexion();

        try {
            em.getTransaction().begin();
            ClienteFrecuente actualizado = em.merge(cliente);
            em.getTransaction().commit();
            return actualizado;
        } catch (Exception e) {
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            throw new PersistenciaException("Error al editar el cliente");
        } finally {
            em.close();
        }
    }

    @Override
    public Cliente guardarCliente(Cliente cliente) throws PersistenciaException {
        EntityManager em = ConexionBD.crearConexion();
        try {
            em.getTransaction().begin();
            em.persist(cliente);
            em.getTransaction().commit();
            return cliente;
        } catch (Exception e) {
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            throw new PersistenciaException("error al guardar");
        } finally {
            em.close();
        }
    }

    @Override
    public boolean eliminarCliente(Long idCliente) throws PersistenciaException {
        EntityManager em = ConexionBD.crearConexion();
        try {
            ClienteFrecuente cliente = em.find(ClienteFrecuente.class, idCliente);
            if (cliente == null) {
                return false;
            }
            em.getTransaction().begin();
            em.remove(cliente);
            em.getTransaction().commit();
            return true;
        } catch (Exception e) {
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            throw new PersistenceException("error al eliminar");
        } finally {
            em.close();
        }
    }

    @Override
    public List<ClienteFrecuente> obtenerClientes() throws PersistenciaException {
        EntityManager em=ConexionBD.crearConexion();
        try {
            String comandoJPQL="SELECT p FROM ClienteFrecuente p";
            TypedQuery<ClienteFrecuente> query=em.createQuery(comandoJPQL,ClienteFrecuente.class);
            return query.getResultList();
        } catch (Exception e) {
            throw new PersistenciaException("Error al obtener la lista de clientes frecuentes");
        }finally{
            em.close();
        }
    }
    @Override
    public List<ClienteFrecuente> consultarPorFiltro(String filtro) throws PersistenciaException {
        EntityManager em = ConexionBD.crearConexion();
        try {
            String jpql = "SELECT c FROM Clientes c WHERE c.nombre LIKE :filtro "+ "OR c.apellido_paterno LIKE :filtro"
             + "OR c.apellido_materno LIKE :filtro"+ "OR c.telefono LIKE :filtro" + "OR c.correo_electronico LIKE :filtro" ;

            return em.createQuery(jpql, ClienteFrecuente.class)
                    .setParameter("filtro", "%" + filtro + "%") 
                    .getResultList();
        } catch (Exception e) {
            throw new PersistenciaException("Error al realizar la busqueda filtrada", e);
        } finally {
            em.close();
        }
    }


}
