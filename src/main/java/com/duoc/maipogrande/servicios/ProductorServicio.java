package com.duoc.maipogrande.servicios;

import com.duoc.maipogrande.modelos.Productor;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

@Service
public class ProductorServicio {

    @PersistenceContext
    EntityManager entityManager;

    public Productor buscarProductorPorEmail(String email) {
        Query query  = entityManager.createNamedStoredProcedureQuery("buscarProdPorEmail");
        query.setParameter("email", email);
        return (Productor) query.getSingleResult();
    }
}
