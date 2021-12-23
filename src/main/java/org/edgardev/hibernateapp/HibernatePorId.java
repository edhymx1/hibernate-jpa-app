package org.edgardev.hibernateapp;

import jakarta.persistence.EntityManager;
import org.edgardev.hibernateapp.entity.Cliente;
import org.edgardev.hibernateapp.util.JpaUtil;

import java.util.Scanner;

public class HibernatePorId {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Ingrese el id: ");
        Long id = scanner.nextLong();

        EntityManager em = JpaUtil.getEntityManager();
        Cliente cliente = em.find(Cliente.class, id);
        System.out.println(cliente);

        em.close();
    }
}
