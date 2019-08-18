package com.duoc.maipogrande.servicios;

import com.duoc.maipogrande.modelos.Transportista;
import org.springframework.stereotype.Service;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

@Service
public class TransportistaServicio {
    @PersistenceContext
    EntityManager entityManager;

    public Transportista buscarTransportistaPorEmail(String email) {
        Query query  = entityManager.createNamedStoredProcedureQuery("buscarTranPorEmail");
        query.setParameter("email", email);
        return (Transportista) query.getSingleResult();
    }
}
