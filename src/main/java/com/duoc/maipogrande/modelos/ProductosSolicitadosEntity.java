package com.duoc.maipogrande.modelos;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "PRODUCTOS_SOLICITADOS", schema = "ROOT")
public class ProductosSolicitadosEntity {
    private long idProd;
    private String nombreProd;
    private String unidadProd;
    private long cantidad;
    private long solicitudesIdSol;
    private SolicitudesEntity solicitudesBySolicitudesIdSol;

    @Id
    @Column(name = "ID_PROD")
    public long getIdProd() {
        return idProd;
    }

    public void setIdProd(long idProd) {
        this.idProd = idProd;
    }

    @Basic
    @Column(name = "NOMBRE_PROD")
    public String getNombreProd() {
        return nombreProd;
    }

    public void setNombreProd(String nombreProd) {
        this.nombreProd = nombreProd;
    }

    @Basic
    @Column(name = "UNIDAD_PROD")
    public String getUnidadProd() {
        return unidadProd;
    }

    public void setUnidadProd(String unidadProd) {
        this.unidadProd = unidadProd;
    }

    @Basic
    @Column(name = "CANTIDAD")
    public long getCantidad() {
        return cantidad;
    }

    public void setCantidad(long cantidad) {
        this.cantidad = cantidad;
    }

    @Basic
    @Column(name = "SOLICITUDES_ID_SOL")
    public long getSolicitudesIdSol() {
        return solicitudesIdSol;
    }

    public void setSolicitudesIdSol(long solicitudesIdSol) {
        this.solicitudesIdSol = solicitudesIdSol;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ProductosSolicitadosEntity that = (ProductosSolicitadosEntity) o;
        return idProd == that.idProd &&
                cantidad == that.cantidad &&
                solicitudesIdSol == that.solicitudesIdSol &&
                Objects.equals(nombreProd, that.nombreProd) &&
                Objects.equals(unidadProd, that.unidadProd);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idProd, nombreProd, unidadProd, cantidad, solicitudesIdSol);
    }

    @ManyToOne
    @JoinColumn(name = "SOLICITUDES_ID_SOL", referencedColumnName = "ID_SOL", nullable = false)
    public SolicitudesEntity getSolicitudesBySolicitudesIdSol() {
        return solicitudesBySolicitudesIdSol;
    }

    public void setSolicitudesBySolicitudesIdSol(SolicitudesEntity solicitudesBySolicitudesIdSol) {
        this.solicitudesBySolicitudesIdSol = solicitudesBySolicitudesIdSol;
    }
}
