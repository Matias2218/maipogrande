package com.duoc.maipogrande.servicios;

import com.duoc.maipogrande.modelos.Transportista;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.StoredProcedureQuery;

@Service
public class TransportistaServicio {
    @PersistenceContext
    EntityManager entityManager;

    @Transactional(readOnly = true)
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
    @Transactional(readOnly = true)
    public Transportista buscarTranPorId(Long id) {
        try {
            StoredProcedureQuery query  = entityManager.createNamedStoredProcedureQuery("buscarTranPorId");
            query.setParameter("id", id);
            query.execute();
            return (Transportista) query.getSingleResult();
        }
        catch (Exception e)
        {
            return null;
        }
    }
}
