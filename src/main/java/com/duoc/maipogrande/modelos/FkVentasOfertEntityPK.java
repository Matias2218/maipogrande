package com.duoc.maipogrande.modelos;

import javax.persistence.Column;
import javax.persistence.Id;
import java.io.Serializable;
import java.util.Objects;

public class FkVentasOfertEntityPK implements Serializable {
    private long ventasIdVen;
    private long oferTranIdOfert;

    @Column(name = "VENTAS_ID_VEN")
    @Id
    public long getVentasIdVen() {
        return ventasIdVen;
    }

    public void setVentasIdVen(long ventasIdVen) {
        this.ventasIdVen = ventasIdVen;
    }

    @Column(name = "OFER_TRAN_ID_OFERT")
    @Id
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
        FkVentasOfertEntityPK that = (FkVentasOfertEntityPK) o;
        return ventasIdVen == that.ventasIdVen &&
                oferTranIdOfert == that.oferTranIdOfert;
    }

    @Override
    public int hashCode() {
        return Objects.hash(ventasIdVen, oferTranIdOfert);
    }
}
