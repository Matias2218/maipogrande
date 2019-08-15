package com.duoc.maipogrande.modelos;

import javax.persistence.*;
import java.util.Collection;
import java.util.Objects;

@Entity
@Table(name = "OFERTAS_PRODUCTOS", schema = "ROOT")
public class OfertasProductosEntity {
    private long idOferp;
    private String paisOrigen;
    private long precioOferta;
    private long ofertasProductosId;
    private Collection<FkProduOfertpEntity> fkProduOfertpsByOfertasProductosId;
    private Collection<FkVentasOferpEntity> fkVentasOferpsByOfertasProductosId;

    @Basic
    @Column(name = "ID_OFERP")
    public long getIdOferp() {
        return idOferp;
    }

    public void setIdOferp(long idOferp) {
        this.idOferp = idOferp;
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
    @Column(name = "PRECIO_OFERTA")
    public long getPrecioOferta() {
        return precioOferta;
    }

    public void setPrecioOferta(long precioOferta) {
        this.precioOferta = precioOferta;
    }

    @Id
    @Column(name = "OFERTAS_PRODUCTOS_ID")
    public long getOfertasProductosId() {
        return ofertasProductosId;
    }

    public void setOfertasProductosId(long ofertasProductosId) {
        this.ofertasProductosId = ofertasProductosId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        OfertasProductosEntity that = (OfertasProductosEntity) o;
        return idOferp == that.idOferp &&
                precioOferta == that.precioOferta &&
                ofertasProductosId == that.ofertasProductosId &&
                Objects.equals(paisOrigen, that.paisOrigen);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idOferp, paisOrigen, precioOferta, ofertasProductosId);
    }

    @OneToMany(mappedBy = "ofertasProductosByOferProOferProduId")
    public Collection<FkProduOfertpEntity> getFkProduOfertpsByOfertasProductosId() {
        return fkProduOfertpsByOfertasProductosId;
    }

    public void setFkProduOfertpsByOfertasProductosId(Collection<FkProduOfertpEntity> fkProduOfertpsByOfertasProductosId) {
        this.fkProduOfertpsByOfertasProductosId = fkProduOfertpsByOfertasProductosId;
    }

    @OneToMany(mappedBy = "ofertasProductosByOferProdOferProdId")
    public Collection<FkVentasOferpEntity> getFkVentasOferpsByOfertasProductosId() {
        return fkVentasOferpsByOfertasProductosId;
    }

    public void setFkVentasOferpsByOfertasProductosId(Collection<FkVentasOferpEntity> fkVentasOferpsByOfertasProductosId) {
        this.fkVentasOferpsByOfertasProductosId = fkVentasOferpsByOfertasProductosId;
    }
}
