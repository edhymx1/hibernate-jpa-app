package org.edgardev.hibernateapp;

import jakarta.persistence.EntityManager;
import org.edgardev.hibernateapp.dominio.ClienteDTO;
import org.edgardev.hibernateapp.entity.Cliente;
import org.edgardev.hibernateapp.util.JpaUtil;

import java.util.List;

public class HibernateQL {
    public static void main(String[] args) {
        EntityManager em = JpaUtil.getEntityManager();
        System.out.println("========== consultar todos ==========");
        List<Cliente> clientes = em.createQuery("select c from Cliente c", Cliente.class)
                .getResultList();
        clientes.forEach(System.out::println);

        System.out.println("========== consultar por id ==========");
        Cliente cliente = em.createQuery("select c from Cliente c where c.id = :id", Cliente.class)
                .setParameter("id", 1L)
                .getSingleResult();
        System.out.println(cliente);

        System.out.println("========== consulta solo el nombre por el id ==========");
        String nombreCliente = em.createQuery("select c.nombre from Cliente c where c.id = :id", String.class)
                .setParameter("id", 2L)
                .getSingleResult();
        System.out.println("nombre: " + nombreCliente);

        System.out.println("========== consulta por campos personalizados ==========");
        Object[] objetoCliente = em.createQuery("select c.id, c.nombre, c.apellido from Cliente c where c.id = :id", Object[].class)
                .setParameter("id", 2L)
                .getSingleResult();
        Long id = (Long) objetoCliente[0];
        String nombre = (String) objetoCliente[1];
        String apellido = (String) objetoCliente[1];
        System.out.println("id=" + id + ", nombre=" + nombre + ", apellido=" + apellido);

        System.out.println("========== consulta por campos personalizados lista ==========");
        List<Object[]> registros = em.createQuery("select c.id, c.nombre, c.apellido from Cliente c", Object[].class)
                .getResultList();
        for (Object[] registro : registros) {
            id = (Long) registro[0];
            nombre = (String) registro[1];
            apellido = (String) registro[1];
            System.out.println("id=" + id + ", nombre=" + nombre + ", apellido=" + apellido);
        }

        System.out.println("========== consulta cliente y forma de pago ==========");
        registros = em.createQuery("select c, c.formaPago from Cliente c", Object[].class)
                .getResultList();
        registros.forEach(reg -> {
            Cliente c = (Cliente) reg[0];
            String formaPago = (String) reg[1];
            System.out.println("formaPago=" + formaPago + "," + c);
        });

        System.out.println("========== consulta que puebla y devuelve objeto entity de una clase personalizada ==========");
        clientes = em.createQuery("select new Cliente(c.nombre, c.apellido) from Cliente c", Cliente.class)
                .getResultList();
        clientes.forEach(System.out::println);

        System.out.println("========== consulta que puebla y devuelve objeto DTO de una clase personalizada ==========");
        List<ClienteDTO> clientesDTO = em.createQuery("select new org.edgardev.hibernateapp.dominio.ClienteDTO(c.nombre, c.apellido) from Cliente c", ClienteDTO.class)
                .getResultList();
        clientesDTO.forEach(System.out::println);

        System.out.println("========== consulta con nombre de clientes ==========");
        List<String> nombres = em.createQuery("select c.nombre from Cliente  c", String.class)
                .getResultList();
        nombres.forEach(System.out::println);

        System.out.println("========== consulta con nombres unicos de clientes ==========");
        nombres = em.createQuery("select distinct(c.nombre) from Cliente  c", String.class)
                .getResultList();
        nombres.forEach(System.out::println);

        System.out.println("========== consulta con formas de pago unicos ==========");
        List<String> formasPago = em.createQuery("select distinct(c.formaPago) from Cliente  c", String.class)
                .getResultList();
        formasPago.forEach(System.out::println);

        System.out.println("========== consulta con cantidad de formas de pago unicos ==========");
        Long totalFormasPago = em.createQuery("select count(distinct(c.formaPago)) from Cliente  c", Long.class)
                .getSingleResult();
        System.out.println(totalFormasPago);

        System.out.println("========== consulta con nombre y apellido concatenados ==========");
        /*nombres = em.createQuery("select concat(c.nombre, ' ', c.apellido) from Cliente  c", String.class)
                .getResultList();*/
        nombres = em.createQuery("select c.nombre || ' ' || c.apellido from Cliente  c", String.class)
                .getResultList();
        nombres.forEach(System.out::println);

        System.out.println("========== consulta con nombre y apellido concatenados mayúsculas ==========");
        nombres = em.createQuery("select upper(concat(c.nombre, ' ', c.apellido)) from Cliente  c", String.class)
                .getResultList();
        nombres.forEach(System.out::println);

        System.out.println("========== consulta con nombre y apellido concatenados minúsculas ==========");
        nombres = em.createQuery("select lower(concat(c.nombre, ' ', c.apellido)) from Cliente  c", String.class)
                .getResultList();
        nombres.forEach(System.out::println);

        System.out.println("========== consulta para buscar por nombre con like ==========");
        clientes = em.createQuery("select c from Cliente c where c.nombre like :parametro", Cliente.class)
                .setParameter("parametro", "%" + "en" + "%")
                .getResultList();
        clientes.forEach(System.out::println);


        em.close();
    }
}
