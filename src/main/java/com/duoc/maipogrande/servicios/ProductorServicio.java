package com.duoc.maipogrande.servicios;

import com.duoc.maipogrande.modelos.OfertaProducto;
import com.duoc.maipogrande.modelos.Productor;
import com.duoc.maipogrande.modelos.Venta;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.StoredProcedureQuery;
import java.math.BigDecimal;
import java.util.List;

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
    @Transactional(noRollbackFor = RuntimeException.class)
    public boolean crearOfertaProducto(OfertaProducto ofertaProducto)
    {
        try {
            StoredProcedureQuery query  = entityManager.createNamedStoredProcedureQuery("crearOfertaProducto");
            query.setParameter("paisOrigen",ofertaProducto.getPaisOrigen());
            query.setParameter("precioOferta",ofertaProducto.getPrecioOferta());
            query.setParameter("unidadMasaOferta",ofertaProducto.getUnidadMasaOferta());
            query.setParameter("idProdu",ofertaProducto.getProducto().getIdProdu());
            query.setParameter("idVenta",ofertaProducto.getVenta().getIdVenta());
            query.execute();
            return true;
        }
        catch (Exception e)
        {
            return false;
        }
    }
    @Transactional(readOnly = true, noRollbackFor = RuntimeException.class)
    public List<Venta> ventasParaSubasta(Integer pagina) {
        try {
            StoredProcedureQuery query  = entityManager.createNamedStoredProcedureQuery("buscarVentasParaSubasta");
            query.setParameter("i",pagina);
            query.execute();
            return query.getResultList();
        }
        catch (Exception e)
        {
            return null;
        }
    }
    @Transactional(readOnly = true, noRollbackFor = RuntimeException.class)
    public List<Venta> ventasActivasProductor(Long id) {
        try {
            StoredProcedureQuery query  = entityManager.createNamedStoredProcedureQuery("buscarVentasActivasProductor");
            query.setParameter("id",id);
            query.execute();
            return query.getResultList();
        }
        catch (Exception e)
        {
            return null;
        }
    }
    @Transactional(readOnly = true, noRollbackFor = RuntimeException.class)
    public Venta buscarVentaPorIdParaSubasta(Integer id) {
        try {
            StoredProcedureQuery query  = entityManager.createNamedStoredProcedureQuery("buscarVentaParaIdParaSubasta");
            query.setParameter("id",id);
            query.execute();
            return (Venta) query.getSingleResult();
        }
        catch (Exception e)
        {
            return null;
        }
    }

    @Transactional(readOnly = true, noRollbackFor = RuntimeException.class)
    public Integer contarVentasSubasta() {
        try {
            Integer filas  = ((BigDecimal)entityManager.createNativeQuery("select CONTARVENTASUBASTA() from dual").
                    getSingleResult()).intValue();
            return filas;
        }
        catch (Exception e)
        {
            return 0;
        }
    }

}
