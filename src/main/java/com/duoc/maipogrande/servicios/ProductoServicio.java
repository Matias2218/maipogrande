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
    public List<Producto> buscarProductosPorId(Long id, short i) {
        try {
            StoredProcedureQuery query  = entityManager.createNamedStoredProcedureQuery("buscarProductosPorIdProd");
            query.setParameter("id",id);
            query.setParameter("i",i);
            query.execute();
            return query.getResultList();
        }
        catch (Exception e)
        {
            return null;
        }
    }
    @Transactional(readOnly = true, noRollbackFor = RuntimeException.class)
    public Producto buscarProductosPorIdProducto(Long id) {
        try {
            StoredProcedureQuery query  = entityManager.createNamedStoredProcedureQuery("buscarProductosPorId");
            query.setParameter("id",id);
            query.execute();
            return (Producto) query.getSingleResult();
        }
        catch (Exception e)
        {
            return null;
        }
    }
    @Transactional(readOnly = true, noRollbackFor = RuntimeException.class)
    public List<Producto> buscarProductosPorNombre(String nombre,Long id, short i) {
        try {
            StoredProcedureQuery query  = entityManager.createNamedStoredProcedureQuery("buscarProductosPorNombre");
            query.setParameter("nombre",nombre);
            query.setParameter("id",id);
            query.setParameter("i",i);
            query.execute();
            return query.getResultList();
        }
        catch (Exception e)
        {
            return null;
        }
    }
    @Transactional(noRollbackFor = RuntimeException.class)
    public boolean eliminarProducto(Long id){
        try{
            StoredProcedureQuery query = entityManager.createNamedStoredProcedureQuery("eliminarProductoPorId");
            query.setParameter("id", id);
            query.execute();
            return true;
        }catch(Exception e){
            return false;
        }
    }
    @Transactional(noRollbackFor = RuntimeException.class)
    public boolean crearProducto(Producto productoActualizado, Blob imagen, Long idProd)
    {
        try {
            StoredProcedureQuery query  = entityManager.createNamedStoredProcedureQuery("crearProducto");
            query.setParameter("nombre",productoActualizado.getNombreProdu());
            query.setParameter("precio",productoActualizado.getPrecioProdu());
            query.setParameter("imagen",imagen);
            query.setParameter("stock",productoActualizado.getStockProdu());
            query.setParameter("tipo",productoActualizado.getTipoComercializacionProdu());
            query.setParameter("calidad",productoActualizado.getCalidadProdu());
            query.setParameter("fechaIngreso",LocalDateTime.now());
            query.setParameter("idProd",idProd);
            query.execute();
            return true;
        }
        catch (Exception e)
        {
            return false;
        }
    }
    @Transactional(noRollbackFor = RuntimeException.class)
    public boolean actualizarProducto(Producto productoActualizado,Blob imagen)
    {
        try {
            StoredProcedureQuery query  = entityManager.createNamedStoredProcedureQuery("actualizarProducto");
            query.setParameter("nombre",productoActualizado.getNombreProdu());
            query.setParameter("precio",productoActualizado.getPrecioProdu());
            query.setParameter("imagen",imagen);
            query.setParameter("stock",productoActualizado.getStockProdu());
            query.setParameter("tipo",productoActualizado.getTipoComercializacionProdu());
            query.setParameter("calidad",productoActualizado.getCalidadProdu());
            query.setParameter("idProdu",productoActualizado.getIdProdu());
            query.execute();
            return true;
        }
        catch (Exception e)
        {
            return false;
        }
    }
}
