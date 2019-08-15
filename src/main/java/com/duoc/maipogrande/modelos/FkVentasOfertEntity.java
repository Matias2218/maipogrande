package com.duoc.maipogrande.modelos;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "FK_VENTAS_OFERT", schema = "ROOT")
@IdClass(FkVentasOfertEntityPK.class)
public class FkVentasOfertEntity {
    private long ventasIdVen;
    private long oferTranIdOfert;
    private VentasEntity ventasByVentasIdVen;
    private OfertasTransportistasEntity ofertasTransportistasByOferTranIdOfert;

    @Id
    @Column(name = "VENTAS_ID_VEN")
    public long getVentasIdVen() {
        return ventasIdVen;
    }

    public void setVentasIdVen(long ventasIdVen) {
        this.ventasIdVen = ventasIdVen;
    }

    @Id
    @Column(name = "OFER_TRAN_ID_OFERT")
    public long getOferTranIdOfert() {
        return oferTranIdOfert;
    }

    public void setOferTranIdOfert(long oferTranIdOfert) {
        this.oferTranIdOfert = oferTranIdOfert;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        FkVentasOfertEntity that = (FkVentasOfertEntity) o;
        return ventasIdVen == that.ventasIdVen &&
                oferTranIdOfert == that.oferTranIdOfert;
    }

    @Override
    public int hashCode() {
        return Objects.hash(ventasIdVen, oferTranIdOfert);
    }

    @ManyToOne
    @JoinColumn(name = "VENTAS_ID_VEN", referencedColumnName = "ID_VEN", nullable = false)
    public VentasEntity getVentasByVentasIdVen() {
        return ventasByVentasIdVen;
    }

    public void setVentasByVentasIdVen(VentasEntity ventasByVentasIdVen) {
        this.ventasByVentasIdVen = ventasByVentasIdVen;
    }

    @ManyToOne
    @JoinColumn(name = "OFER_TRAN_ID_OFERT", referencedColumnName = "ID_OFERT", nullable = false)
    public OfertasTransportistasEntity getOfertasTransportistasByOferTranIdOfert() {
        return ofertasTransportistasByOferTranIdOfert;
    }

    public void setOfertasTransportistasByOferTranIdOfert(OfertasTransportistasEntity ofertasTransportistasByOferTranIdOfert) {
        this.ofertasTransportistasByOferTranIdOfert = ofertasTransportistasByOferTranIdOfert;
    }
}
