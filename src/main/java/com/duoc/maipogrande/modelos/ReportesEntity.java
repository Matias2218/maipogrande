package com.duoc.maipogrande.modelos;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "REPORTES", schema = "ROOT")
public class ReportesEntity {
    private long idRepor;
    private String asuntoRepor;
    private String descripcionRepor;
    private long ventasIdVen;
    private VentasEntity ventasByVentasIdVen;

    @Id
    @Column(name = "ID_REPOR")
    public long getIdRepor() {
        return idRepor;
    }

    public void setIdRepor(long idRepor) {
        this.idRepor = idRepor;
    }

    @Basic
    @Column(name = "ASUNTO_REPOR")
    public String getAsuntoRepor() {
        return asuntoRepor;
    }

    public void setAsuntoRepor(String asuntoRepor) {
        this.asuntoRepor = asuntoRepor;
    }

    @Basic
    @Column(name = "DESCRIPCION_REPOR")
    public String getDescripcionRepor() {
        return descripcionRepor;
    }

    public void setDescripcionRepor(String descripcionRepor) {
        this.descripcionRepor = descripcionRepor;
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
        ReportesEntity that = (ReportesEntity) o;
        return idRepor == that.idRepor &&
                ventasIdVen == that.ventasIdVen &&
                Objects.equals(asuntoRepor, that.asuntoRepor) &&
                Objects.equals(descripcionRepor, that.descripcionRepor);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idRepor, asuntoRepor, descripcionRepor, ventasIdVen);
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
