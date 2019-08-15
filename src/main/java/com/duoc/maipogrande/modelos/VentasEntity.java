package com.duoc.maipogrande.modelos;

import javax.persistence.*;
import java.util.Collection;
import java.util.Objects;

@Entity
@Table(name = "VENTAS", schema = "ROOT")
public class VentasEntity {
    private long idVen;
    private String paisOrigen;
    private long precioTotal;
    private long solicitudesIdSol;
    private Collection<BoletasEntity> boletasByIdVen;
    private Collection<FkVentasOferpEntity> fkVentasOferpsByIdVen;
    private Collection<FkVentasOfertEntity> fkVentasOfertsByIdVen;
    private Collection<ReportesEntity> reportesByIdVen;
    private SolicitudesEntity solicitudesBySolicitudesIdSol;

    @Id
    @Column(name = "ID_VEN")
    public long getIdVen() {
        return idVen;
    }

    public void setIdVen(long idVen) {
        this.idVen = idVen;
    }

    @Basic
    @Column(name = "PAIS_ORIGEN")
    public String getPaisOrigen() {
        return paisOrigen;
    }

    public void setPaisOrigen(String paisOrigen) {
        this.paisOrigen = paisOrigen;
    }

    @Basic
    @Column(name = "PRECIO_TOTAL")
    public long getPrecioTotal() {
        return precioTotal;
    }

    public void setPrecioTotal(long precioTotal) {
        this.precioTotal = precioTotal;
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
        VentasEntity that = (VentasEntity) o;
        return idVen == that.idVen &&
                precioTotal == that.precioTotal &&
                solicitudesIdSol == that.solicitudesIdSol &&
                Objects.equals(paisOrigen, that.paisOrigen);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idVen, paisOrigen, precioTotal, solicitudesIdSol);
    }

    @OneToMany(mappedBy = "ventasByVentasIdVen")
    public Collection<BoletasEntity> getBoletasByIdVen() {
        return boletasByIdVen;
    }

    public void setBoletasByIdVen(Collection<BoletasEntity> boletasByIdVen) {
        this.boletasByIdVen = boletasByIdVen;
    }

    @OneToMany(mappedBy = "ventasByVentasIdVen")
    public Collection<FkVentasOferpEntity> getFkVentasOferpsByIdVen() {
        return fkVentasOferpsByIdVen;
    }

    public void setFkVentasOferpsByIdVen(Collection<FkVentasOferpEntity> fkVentasOferpsByIdVen) {
        this.fkVentasOferpsByIdVen = fkVentasOferpsByIdVen;
    }

    @OneToMany(mappedBy = "ventasByVentasIdVen")
    public Collection<FkVentasOfertEntity> getFkVentasOfertsByIdVen() {
        return fkVentasOfertsByIdVen;
    }

    public void setFkVentasOfertsByIdVen(Collection<FkVentasOfertEntity> fkVentasOfertsByIdVen) {
        this.fkVentasOfertsByIdVen = fkVentasOfertsByIdVen;
    }

    @OneToMany(mappedBy = "ventasByVentasIdVen")
    public Collection<ReportesEntity> getReportesByIdVen() {
        return reportesByIdVen;
    }

    public void setReportesByIdVen(Collection<ReportesEntity> reportesByIdVen) {
        this.reportesByIdVen = reportesByIdVen;
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
