package com.duoc.maipogrande.modelos;

import javax.persistence.Column;
import javax.persistence.Id;
import java.io.Serializable;
import java.util.Objects;

public class FkVentasOferpEntityPK implements Serializable {
    private long ventasIdVen;
    private long oferProdOferProdId;

    @Column(name = "VENTAS_ID_VEN")
    @Id
    public long getVentasIdVen() {
        return ventasIdVen;
    }

    public void setVentasIdVen(long ventasIdVen) {
        this.ventasIdVen = ventasIdVen;
    }

    @Column(name = "OFER_PROD_OFER_PROD_ID")
    @Id
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
        FkVentasOferpEntityPK that = (FkVentasOferpEntityPK) o;
        return ventasIdVen == that.ventasIdVen &&
                oferProdOferProdId == that.oferProdOferProdId;
    }

    @Override
    public int hashCode() {
        return Objects.hash(ventasIdVen, oferProdOferProdId);
    }
}
