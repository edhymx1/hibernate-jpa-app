package org.edgardev.hibernateapp.repositories.impl;

import jakarta.persistence.EntityManager;
import org.edgardev.hibernateapp.entity.Cliente;
import org.edgardev.hibernateapp.repositories.CrudRepository;

import java.util.List;

public class CLienteRepositoryImpl implements CrudRepository<Cliente> {
    private EntityManager em;

    public CLienteRepositoryImpl(EntityManager em) {
        this.em = em;
    }

    @Override
    public List<Cliente> listar() {
        return em.createQuery("select c from Cliente c", Cliente.class).getResultList();
    }

    @Override
    public Cliente porId(Long id) {
        return em.find(Cliente.class, id);
    }

    @Override
    public void guardar(Cliente cliente) {
        if (cliente.getId() != null && cliente.getId() > 0) {
            em.merge(cliente);
        } else {
            em.persist(cliente);
        }
    }

    @Override
    public void eliminar(Long id) {
        em.remove(porId(id));
    }
}
