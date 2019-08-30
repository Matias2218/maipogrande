package com.duoc.maipogrande.servicios;

import com.duoc.maipogrande.modelos.Producto;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.StoredProcedureQuery;
import java.sql.Blob;
import java.time.LocalDateTime;
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
    @Transactional()
    public boolean crearProducto(String nombre, Integer precio, Blob imagen, Integer stock, Character tipo, Byte calidad, LocalDateTime fechaIngreso,Long idProd)
    {
        try {
            StoredProcedureQuery query  = entityManager.createNamedStoredProcedureQuery("crearProducto");
            query.setParameter("nombre",nombre);
            query.setParameter("precio",precio);
            query.setParameter("imagen",imagen);
            query.setParameter("stock",stock);
            query.setParameter("tipo",tipo);
            query.setParameter("calidad",calidad);
            query.setParameter("fechaIngreso",fechaIngreso);
            query.setParameter("idProd",idProd);
            query.execute();
            return true;
        }
        catch (Exception e)
        {
            return false;
        }
    }
}
