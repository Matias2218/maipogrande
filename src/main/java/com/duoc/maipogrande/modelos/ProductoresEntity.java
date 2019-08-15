package com.duoc.maipogrande.modelos;

import javax.persistence.*;
import java.sql.Time;
import java.util.Collection;
import java.util.Objects;

@Entity
@Table(name = "PRODUCTORES", schema = "ROOT")
public class ProductoresEntity {
    private long idProd;
    private String rutProd;
    private String nombreProd;
    private String apellidoProd;
    private String emailProd;
    private String contrasenaProd;
    private Time fechaCreacion;
    private String telefonoProd;
    private long contratosIdContra;
    private ContratosEntity contratosByContratosIdContra;
    private Collection<ProductosEntity> productosByIdProd;

    @Id
    @Column(name = "ID_PROD")
    public long getIdProd() {
        return idProd;
    }

    public void setIdProd(long idProd) {
        this.idProd = idProd;
    }

    @Basic
    @Column(name = "RUT_PROD")
    public String getRutProd() {
        return rutProd;
    }

    public void setRutProd(String rutProd) {
        this.rutProd = rutProd;
    }

    @Basic
    @Column(name = "NOMBRE_PROD")
    public String getNombreProd() {
        return nombreProd;
    }

    public void setNombreProd(String nombreProd) {
        this.nombreProd = nombreProd;
    }

    @Basic
    @Column(name = "APELLIDO_PROD")
    public String getApellidoProd() {
        return apellidoProd;
    }

    public void setApellidoProd(String apellidoProd) {
        this.apellidoProd = apellidoProd;
    }

    @Basic
    @Column(name = "EMAIL_PROD")
    public String getEmailProd() {
        return emailProd;
    }

    public void setEmailProd(String emailProd) {
        this.emailProd = emailProd;
    }

    @Basic
    @Column(name = "CONTRASENA_PROD")
    public String getContrasenaProd() {
        return contrasenaProd;
    }

    public void setContrasenaProd(String contrasenaProd) {
        this.contrasenaProd = contrasenaProd;
    }

    @Basic
    @Column(name = "FECHA_CREACION")
    public Time getFechaCreacion() {
        return fechaCreacion;
    }

    public void setFechaCreacion(Time fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }

    @Basic
    @Column(name = "TELEFONO_PROD")
    public String getTelefonoProd() {
        return telefonoProd;
    }

    public void setTelefonoProd(String telefonoProd) {
        this.telefonoProd = telefonoProd;
    }

    @Basic
    @Column(name = "CONTRATOS_ID_CONTRA")
    public long getContratosIdContra() {
        return contratosIdContra;
    }

    public void setContratosIdContra(long contratosIdContra) {
        this.contratosIdContra = contratosIdContra;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ProductoresEntity that = (ProductoresEntity) o;
        return idProd == that.idProd &&
                contratosIdContra == that.contratosIdContra &&
                Objects.equals(rutProd, that.rutProd) &&
                Objects.equals(nombreProd, that.nombreProd) &&
                Objects.equals(apellidoProd, that.apellidoProd) &&
                Objects.equals(emailProd, that.emailProd) &&
                Objects.equals(contrasenaProd, that.contrasenaProd) &&
                Objects.equals(fechaCreacion, that.fechaCreacion) &&
                Objects.equals(telefonoProd, that.telefonoProd);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idProd, rutProd, nombreProd, apellidoProd, emailProd, contrasenaProd, fechaCreacion, telefonoProd, contratosIdContra);
    }

    @ManyToOne
    @JoinColumn(name = "CONTRATOS_ID_CONTRA", referencedColumnName = "ID_CONTRA", nullable = false)
    public ContratosEntity getContratosByContratosIdContra() {
        return contratosByContratosIdContra;
    }

    public void setContratosByContratosIdContra(ContratosEntity contratosByContratosIdContra) {
        this.contratosByContratosIdContra = contratosByContratosIdContra;
    }

    @OneToMany(mappedBy = "productoresByProductoresIdProd")
    public Collection<ProductosEntity> getProductosByIdProd() {
        return productosByIdProd;
    }

    public void setProductosByIdProd(Collection<ProductosEntity> productosByIdProd) {
        this.productosByIdProd = productosByIdProd;
    }
}
