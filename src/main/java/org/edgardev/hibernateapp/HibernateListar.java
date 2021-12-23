package org.edgardev.hibernateapp;

import jakarta.persistence.EntityManager;
import org.edgardev.hibernateapp.entity.Cliente;
import org.edgardev.hibernateapp.util.JpaUtil;

import java.util.List;

public class HibernateListar {
    public static void main(String[] args) {
        EntityManager em = JpaUtil.getEntityManager();
        List<Cliente> clientes = em.createQuery("select c from Cliente c").getResultList();
        clientes.forEach(System.out::println);
        em.close();
    }
}
