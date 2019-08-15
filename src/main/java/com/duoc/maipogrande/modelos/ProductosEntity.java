package com.duoc.maipogrande.modelos;

import javax.persistence.*;
import java.sql.Time;
import java.util.Collection;
import java.util.Objects;

@Entity
@Table(name = "PRODUCTOS", schema = "ROOT")
public class ProductosEntity {
    private long idProdu;
    private String nombreProdu;
    private Time fechaIngreso;
    private long calidadProdu;
    private long precioProdu;
    private String tipoComercializacion;
    private long stock;
    private long productoresIdProd;
    private Collection<FkProduOfertpEntity> fkProduOfertpsByIdProdu;
    private ProductoresEntity productoresByProductoresIdProd;

    @Id
    @Column(name = "ID_PRODU")
    public long getIdProdu() {
        return idProdu;
    }

    public void setIdProdu(long idProdu) {
        this.idProdu = idProdu;
    }

    @Basic
    @Column(name = "NOMBRE_PRODU")
    public String getNombreProdu() {
        return nombreProdu;
    }

    public void setNombreProdu(String nombreProdu) {
        this.nombreProdu = nombreProdu;
    }

    @Basic
    @Column(name = "FECHA_INGRESO")
    public Time getFechaIngreso() {
        return fechaIngreso;
    }

    public void setFechaIngreso(Time fechaIngreso) {
        this.fechaIngreso = fechaIngreso;
    }

    @Basic
    @Column(name = "CALIDAD_PRODU")
    public long getCalidadProdu() {
        return calidadProdu;
    }

    public void setCalidadProdu(long calidadProdu) {
        this.calidadProdu = calidadProdu;
    }

    @Basic
    @Column(name = "PRECIO_PRODU")
    public long getPrecioProdu() {
        return precioProdu;
    }

    public void setPrecioProdu(long precioProdu) {
        this.precioProdu = precioProdu;
    }

    @Basic
    @Column(name = "TIPO_COMERCIALIZACION")
    public String getTipoComercializacion() {
        return tipoComercializacion;
    }

    public void setTipoComercializacion(String tipoComercializacion) {
        this.tipoComercializacion = tipoComercializacion;
    }

    @Basic
    @Column(name = "STOCK")
    public long getStock() {
        return stock;
    }

    public void setStock(long stock) {
        this.stock = stock;
    }

    @Basic
    @Column(name = "PRODUCTORES_ID_PROD")
    public long getProductoresIdProd() {
        return productoresIdProd;
    }

    public void setProductoresIdProd(long productoresIdProd) {
        this.productoresIdProd = productoresIdProd;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ProductosEntity that = (ProductosEntity) o;
        return idProdu == that.idProdu &&
                calidadProdu == that.calidadProdu &&
                precioProdu == that.precioProdu &&
                stock == that.stock &&
                productoresIdProd == that.productoresIdProd &&
                Objects.equals(nombreProdu, that.nombreProdu) &&
                Objects.equals(fechaIngreso, that.fechaIngreso) &&
                Objects.equals(tipoComercializacion, that.tipoComercializacion);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idProdu, nombreProdu, fechaIngreso, calidadProdu, precioProdu, tipoComercializacion, stock, productoresIdProd);
    }

    @OneToMany(mappedBy = "productosByProductosIdProdu")
    public Collection<FkProduOfertpEntity> getFkProduOfertpsByIdProdu() {
        return fkProduOfertpsByIdProdu;
    }

    public void setFkProduOfertpsByIdProdu(Collection<FkProduOfertpEntity> fkProduOfertpsByIdProdu) {
        this.fkProduOfertpsByIdProdu = fkProduOfertpsByIdProdu;
    }

    @ManyToOne
    @JoinColumn(name = "PRODUCTORES_ID_PROD", referencedColumnName = "ID_PROD", nullable = false)
    public ProductoresEntity getProductoresByProductoresIdProd() {
        return productoresByProductoresIdProd;
    }

    public void setProductoresByProductoresIdProd(ProductoresEntity productoresByProductoresIdProd) {
        this.productoresByProductoresIdProd = productoresByProductoresIdProd;
    }
}
