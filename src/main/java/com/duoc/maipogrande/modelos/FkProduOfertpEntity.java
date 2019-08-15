package com.duoc.maipogrande.modelos;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "FK_PRODU_OFERTP", schema = "ROOT")
@IdClass(FkProduOfertpEntityPK.class)
public class FkProduOfertpEntity {
    private long productosIdProdu;
    private long oferProOferProduId;
    private ProductosEntity productosByProductosIdProdu;
    private OfertasProductosEntity ofertasProductosByOferProOferProduId;

    @Id
    @Column(name = "PRODUCTOS_ID_PRODU")
    public long getProductosIdProdu() {
        return productosIdProdu;
    }

    public void setProductosIdProdu(long productosIdProdu) {
        this.productosIdProdu = productosIdProdu;
    }

    @Id
    @Column(name = "OFER_PRO_OFER_PRODU_ID")
    public long getOferProOferProduId() {
        return oferProOferProduId;
    }

    public void setOferProOferProduId(long oferProOferProduId) {
        this.oferProOferProduId = oferProOferProduId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        FkProduOfertpEntity that = (FkProduOfertpEntity) o;
        return productosIdProdu == that.productosIdProdu &&
                oferProOferProduId == that.oferProOferProduId;
    }

    @Override
    public int hashCode() {
        return Objects.hash(productosIdProdu, oferProOferProduId);
    }

    @ManyToOne
    @JoinColumn(name = "PRODUCTOS_ID_PRODU", referencedColumnName = "ID_PRODU", nullable = false)
    public ProductosEntity getProductosByProductosIdProdu() {
        return productosByProductosIdProdu;
    }

    public void setProductosByProductosIdProdu(ProductosEntity productosByProductosIdProdu) {
        this.productosByProductosIdProdu = productosByProductosIdProdu;
    }

    @ManyToOne
    @JoinColumn(name = "OFER_PRO_OFER_PRODU_ID", referencedColumnName = "OFERTAS_PRODUCTOS_ID", nullable = false)
    public OfertasProductosEntity getOfertasProductosByOferProOferProduId() {
        return ofertasProductosByOferProOferProduId;
    }

    public void setOfertasProductosByOferProOferProduId(OfertasProductosEntity ofertasProductosByOferProOferProduId) {
        this.ofertasProductosByOferProOferProduId = ofertasProductosByOferProOferProduId;
    }
}
