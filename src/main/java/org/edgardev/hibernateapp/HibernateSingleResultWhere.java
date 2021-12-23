package org.edgardev.hibernateapp;

import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import org.edgardev.hibernateapp.entity.Cliente;
import org.edgardev.hibernateapp.util.JpaUtil;

import java.util.Scanner;

public class HibernateSingleResultWhere {
    public static void main(String[] args) {
        EntityManager em = JpaUtil.getEntityManager();

        Scanner scanner = new Scanner(System.in);
        System.out.print("Ingrese la forma de pago: ");
        String pago = scanner.next();

        Query query = em.createQuery("select c from Cliente c where c.formaPago = ?1", Cliente.class);
        query.setParameter(1, pago);
        query.setMaxResults(1);
        Cliente cliente = (Cliente) query.getSingleResult();
        System.out.println(cliente);

        em.close();
    }
}
