package com.duoc.maipogrande.modelos;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "FK_VENTAS_OFERP", schema = "ROOT")
@IdClass(FkVentasOferpEntityPK.class)
public class FkVentasOferpEntity {
    private long ventasIdVen;
    private long oferProdOferProdId;
    private VentasEntity ventasByVentasIdVen;
    private OfertasProductosEntity ofertasProductosByOferProdOferProdId;

    @Id
    @Column(name = "VENTAS_ID_VEN")
    public long getVentasIdVen() {
        return ventasIdVen;
    }

    public void setVentasIdVen(long ventasIdVen) {
        this.ventasIdVen = ventasIdVen;
    }

    @Id
    @Column(name = "OFER_PROD_OFER_PROD_ID")
    public long getOferProdOferProdId() {
        return oferProdOferProdId;
    }

    public void setOferProdOferProdId(long oferProdOferProdId) {
        this.oferProdOferProdId = oferProdOferProdId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        FkVentasOferpEntity that = (FkVentasOferpEntity) o;
        return ventasIdVen == that.ventasIdVen &&
                oferProdOferProdId == that.oferProdOferProdId;
    }

    @Override
    public int hashCode() {
        return Objects.hash(ventasIdVen, oferProdOferProdId);
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
    @JoinColumn(name = "OFER_PROD_OFER_PROD_ID", referencedColumnName = "OFERTAS_PRODUCTOS_ID", nullable = false)
    public OfertasProductosEntity getOfertasProductosByOferProdOferProdId() {
        return ofertasProductosByOferProdOferProdId;
    }

    public void setOfertasProductosByOferProdOferProdId(OfertasProductosEntity ofertasProductosByOferProdOferProdId) {
        this.ofertasProductosByOferProdOferProdId = ofertasProductosByOferProdOferProdId;
    }
}
