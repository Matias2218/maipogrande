package com.duoc.maipogrande.servicios;

import com.duoc.maipogrande.modelos.Cliente;
import org.springframework.stereotype.Service;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

@Service
public class ClienteServicio{


    @PersistenceContext
    EntityManager entityManager;

    public Cliente buscarClientePorEmail(String email) {
        Query query  = entityManager.createNamedStoredProcedureQuery("buscarCliPorEmail");
        query.setParameter("email", email);
        return (Cliente) query.getSingleResult();
    }
}
