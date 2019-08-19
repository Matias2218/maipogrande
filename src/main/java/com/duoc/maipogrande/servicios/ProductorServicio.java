package com.duoc.maipogrande.servicios;

import com.duoc.maipogrande.modelos.Productor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.StoredProcedureQuery;

@Service
public class ProductorServicio {

    @PersistenceContext
    EntityManager entityManager;

    @Transactional(readOnly = true)
    public Productor buscarProductorPorEmail(String email) {
        try {
        StoredProcedureQuery query  = entityManager.createNamedStoredProcedureQuery("buscarProdPorEmail");
        query.setParameter("email", email);
        query.execute();
        return (Productor) query.getSingleResult();
        }
        catch (Exception e)
        {
            return null;
        }
    }
    @Transactional(readOnly = true)
    public Productor buscarProdPorId(Long id) {
        try {
            StoredProcedureQuery query  = entityManager.createNamedStoredProcedureQuery("buscarProdPorId");
            query.setParameter("id", id);
            query.execute();
            return (Productor) query.getSingleResult();
        }
        catch (Exception e)
        {
            return null;
        }
    }
}
