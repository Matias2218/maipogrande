package com.duoc.maipogrande.servicios;

import com.duoc.maipogrande.modelos.Cliente;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.StoredProcedureQuery;

@Service
public class ClienteServicio{

    @PersistenceContext
    EntityManager entityManager;

    @Transactional(readOnly = true)
    public Cliente buscarClientePorEmail(String email) {
        try {
            StoredProcedureQuery query  = entityManager.createNamedStoredProcedureQuery("buscarCliPorEmail");
            query.setParameter("email", email);
            query.execute();
            return (Cliente) query.getSingleResult();
        }
        catch (Exception e)
        {
            return null;
        }
    }

    @Transactional(readOnly = true)
    public Cliente buscarClientePorId(Long id) {
        try {
            StoredProcedureQuery query  = entityManager.createNamedStoredProcedureQuery("buscarCliPorId");
            query.setParameter("id", id);
            query.execute();
            return (Cliente) query.getSingleResult();
        }
        catch (Exception e)
        {
            return null;
        }
    }
}
