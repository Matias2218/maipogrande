package com.duoc.maipogrande.modelos;

import javax.persistence.Column;
import javax.persistence.Id;
import java.io.Serializable;
import java.util.Objects;

public class FkProduOfertpEntityPK implements Serializable {
    private long productosIdProdu;
    private long oferProOferProduId;

    @Column(name = "PRODUCTOS_ID_PRODU")
    @Id
    public long getProductosIdProdu() {
        return productosIdProdu;
    }

    public void setProductosIdProdu(long productosIdProdu) {
        this.productosIdProdu = productosIdProdu;
    }

    @Column(name = "OFER_PRO_OFER_PRODU_ID")
    @Id
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
        FkProduOfertpEntityPK that = (FkProduOfertpEntityPK) o;
        return productosIdProdu == that.productosIdProdu &&
                oferProOferProduId == that.oferProOferProduId;
    }

    @Override
    public int hashCode() {
        return Objects.hash(productosIdProdu, oferProOferProduId);
    }
}
