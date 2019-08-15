package com.duoc.maipogrande.modelos;

import javax.persistence.*;
import java.util.Collection;
import java.util.Objects;

@Entity
@Table(name = "OFERTAS_TRANSPORTISTAS", schema = "ROOT")
public class OfertasTransportistasEntity {
    private long idOfert;
    private long precioOfert;
    private Collection<FkVentasOfertEntity> fkVentasOfertsByIdOfert;
    private TransportistasEntity transportistasByTransportistasIdTran;

    @Id
    @Column(name = "ID_OFERT")
    public long getIdOfert() {
        return idOfert;
    }

    public void setIdOfert(long idOfert) {
        this.idOfert = idOfert;
    }

    @Basic
    @Column(name = "PRECIO_OFERT")
    public long getPrecioOfert() {
        return precioOfert;
    }

    public void setPrecioOfert(long precioOfert) {
        this.precioOfert = precioOfert;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        OfertasTransportistasEntity that = (OfertasTransportistasEntity) o;
        return idOfert == that.idOfert &&
                precioOfert == that.precioOfert;
    }

    @Override
    public int hashCode() {
        return Objects.hash(idOfert, precioOfert);
    }

    @OneToMany(mappedBy = "ofertasTransportistasByOferTranIdOfert")
    public Collection<FkVentasOfertEntity> getFkVentasOfertsByIdOfert() {
        return fkVentasOfertsByIdOfert;
    }

    public void setFkVentasOfertsByIdOfert(Collection<FkVentasOfertEntity> fkVentasOfertsByIdOfert) {
        this.fkVentasOfertsByIdOfert = fkVentasOfertsByIdOfert;
    }

    @ManyToOne
    @JoinColumn(name = "TRANSPORTISTAS_ID_TRAN", referencedColumnName = "ID_TRAN", nullable = false)
    public TransportistasEntity getTransportistasByTransportistasIdTran() {
        return transportistasByTransportistasIdTran;
    }

    public void setTransportistasByTransportistasIdTran(TransportistasEntity transportistasByTransportistasIdTran) {
        this.transportistasByTransportistasIdTran = transportistasByTransportistasIdTran;
    }
}
