package com.duoc.maipogrande.modelos;

import javax.persistence.*;
import java.sql.Time;
import java.util.Objects;

@Entity
@Table(name = "BOLETAS", schema = "ROOT")
public class BoletasEntity {
    private long idBol;
    private Time fechaEmision;
    private long ventasIdVen;
    private VentasEntity ventasByVentasIdVen;

    @Id
    @Column(name = "ID_BOL")
    public long getIdBol() {
        return idBol;
    }

    public void setIdBol(long idBol) {
        this.idBol = idBol;
    }

    @Basic
    @Column(name = "FECHA_EMISION")
    public Time getFechaEmision() {
        return fechaEmision;
    }

    public void setFechaEmision(Time fechaEmision) {
        this.fechaEmision = fechaEmision;
    }

    @Basic
    @Column(name = "VENTAS_ID_VEN")
    public long getVentasIdVen() {
        return ventasIdVen;
    }

    public void setVentasIdVen(long ventasIdVen) {
        this.ventasIdVen = ventasIdVen;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BoletasEntity that = (BoletasEntity) o;
        return idBol == that.idBol &&
                ventasIdVen == that.ventasIdVen &&
                Objects.equals(fechaEmision, that.fechaEmision);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idBol, fechaEmision, ventasIdVen);
    }

    @ManyToOne
    @JoinColumn(name = "VENTAS_ID_VEN", referencedColumnName = "ID_VEN", nullable = false)
    public VentasEntity getVentasByVentasIdVen() {
        return ventasByVentasIdVen;
    }

    public void setVentasByVentasIdVen(VentasEntity ventasByVentasIdVen) {
        this.ventasByVentasIdVen = ventasByVentasIdVen;
    }
}
