/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import conexion.ConexionBD;
import entidades.Cliente;
import entidades.ClienteFrecuente;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceException;

/**
 *
 * @author munos
 */
public class ClienteDAO {
    
    public ClienteFrecuente guardar(ClienteFrecuente cliente) {
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
            throw new PersistenceException("error al guardar");
        } finally {
            em.close();
        }
    }
    public boolean eliminar(Long id) {
        EntityManager em = ConexionBD.crearConexion();
        try {
            ClienteFrecuente cliente = em.find(ClienteFrecuente.class, id);
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
     public ClienteFrecuente editar(ClienteFrecuente cliente) {
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
            throw e;
        } finally {
            em.close();
        }
    }
     }
