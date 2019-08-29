package com.duoc.maipogrande.servicios;

import com.duoc.maipogrande.modelos.Producto;
import com.duoc.maipogrande.modelos.Productor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.StoredProcedureQuery;
import java.util.List;

@Service
public class ProductoServicio {

    @PersistenceContext
    EntityManager entityManager;

    @Transactional(readOnly = true, noRollbackFor = RuntimeException.class)
    public List<Producto> buscarProductosPorId(Long id) {
        try {
            StoredProcedureQuery query  = entityManager.createNamedStoredProcedureQuery("buscarProductosPorIdProd");
            query.setParameter("id",id);
            query.execute();
            return query.getResultList();
        }
        catch (Exception e)
        {
            return null;
        }
    }
}
