package com.duoc.maipogrande.servicios;

import com.duoc.maipogrande.modelos.Cliente;
import com.duoc.maipogrande.modelos.ProductoSolicitado;
import com.duoc.maipogrande.modelos.Solicitud;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.StoredProcedureQuery;
import java.util.List;

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

    /**
     * Metodo encargado de crear solicitud hecha por el cliente externo
     * @param solicitud
     * @return
     */
    @Transactional(noRollbackFor = RuntimeException.class)
    public boolean crearSolicitud(Solicitud solicitud)
    {
        try {
            StoredProcedureQuery query = entityManager.createNamedStoredProcedureQuery("crearSolicitud");
            query.setParameter("descripcion", solicitud.getDescripcionSol());
            query.setParameter("direccion", solicitud.getDireccionDestinoSol());
            query.setParameter("estado", solicitud.getEstadoSol());
            query.setParameter("fechaEmision", solicitud.getFechaEmisionSol());
            query.setParameter("fechaLimite", solicitud.getFechalimiteSol());
            query.setParameter("paisDestino", solicitud.getPaisDestinoSol());
            query.setParameter("idCli", solicitud.getCliente().getIdCli());
            query.execute();
            return true;
        }
        catch (Throwable e)
        {
            System.out.println(e.getMessage());
            return false;
        }
    }

    @Transactional(noRollbackFor = RuntimeException.class)
    public boolean crearProductosSolicitados(List<ProductoSolicitado> productoSolicitados )
    {
        try {
            for (ProductoSolicitado productoSolicitado:productoSolicitados)
            {
                StoredProcedureQuery query = entityManager.createNamedStoredProcedureQuery("crearProductosSolicitados");
                query.setParameter("nombreProds",productoSolicitado.getNombreProdS());
                query.setParameter("cantidadProds",productoSolicitado.getCantidadProdS());
                query.setParameter("unidadProds",productoSolicitado.getUnidadProdS());
                query.execute();
            }
            return true;
        }
        catch (Throwable e)
        {
            System.out.println(e.getMessage());
            return false;
        }
    }

}
