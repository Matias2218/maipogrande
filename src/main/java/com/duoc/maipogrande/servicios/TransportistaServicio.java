package com.duoc.maipogrande.servicios;

import com.duoc.maipogrande.modelos.Transportista;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.StoredProcedureQuery;

@Service
public class TransportistaServicio {
    @PersistenceContext
    EntityManager entityManager;

    public Transportista buscarTransportistaPorEmail(String email) {
        try {
        StoredProcedureQuery query  = entityManager.createNamedStoredProcedureQuery("buscarTranPorEmail");
        query.setParameter("email", email);
        query.execute();
        return (Transportista) query.getSingleResult();
        }
        catch (Exception e)
        {
            return null;
        }
    }
}
