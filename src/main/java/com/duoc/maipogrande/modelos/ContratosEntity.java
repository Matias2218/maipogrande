package com.duoc.maipogrande.modelos;

import javax.persistence.*;
import java.sql.Time;
import java.util.Arrays;
import java.util.Collection;
import java.util.Objects;

@Entity
@Table(name = "CONTRATOS", schema = "ROOT")
public class ContratosEntity {
    private long idContra;
    private Time fechaInicio;
    private Time fechaTermino;
    private byte[] pdfContra;
    private String tipoContra;
    private Collection<ProductoresEntity> productoresByIdContra;
    private Collection<TransportistasEntity> transportistasByIdContra;

    @Id
    @Column(name = "ID_CONTRA")
    public long getIdContra() {
        return idContra;
    }

    public void setIdContra(long idContra) {
        this.idContra = idContra;
    }

    @Basic
    @Column(name = "FECHA_INICIO")
    public Time getFechaInicio() {
        return fechaInicio;
    }

    public void setFechaInicio(Time fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    @Basic
    @Column(name = "FECHA_TERMINO")
    public Time getFechaTermino() {
        return fechaTermino;
    }

    public void setFechaTermino(Time fechaTermino) {
        this.fechaTermino = fechaTermino;
    }

    @Basic
    @Column(name = "PDF_CONTRA")
    public byte[] getPdfContra() {
        return pdfContra;
    }

    public void setPdfContra(byte[] pdfContra) {
        this.pdfContra = pdfContra;
    }

    @Basic
    @Column(name = "TIPO_CONTRA")
    public String getTipoContra() {
        return tipoContra;
    }

    public void setTipoContra(String tipoContra) {
        this.tipoContra = tipoContra;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ContratosEntity that = (ContratosEntity) o;
        return idContra == that.idContra &&
                Objects.equals(fechaInicio, that.fechaInicio) &&
                Objects.equals(fechaTermino, that.fechaTermino) &&
                Arrays.equals(pdfContra, that.pdfContra) &&
                Objects.equals(tipoContra, that.tipoContra);
    }

    @Override
    public int hashCode() {
        int result = Objects.hash(idContra, fechaInicio, fechaTermino, tipoContra);
        result = 31 * result + Arrays.hashCode(pdfContra);
        return result;
    }

    @OneToMany(mappedBy = "contratosByContratosIdContra")
    public Collection<ProductoresEntity> getProductoresByIdContra() {
        return productoresByIdContra;
    }

    public void setProductoresByIdContra(Collection<ProductoresEntity> productoresByIdContra) {
        this.productoresByIdContra = productoresByIdContra;
    }

    @OneToMany(mappedBy = "contratosByContratosIdContra")
    public Collection<TransportistasEntity> getTransportistasByIdContra() {
        return transportistasByIdContra;
    }

    public void setTransportistasByIdContra(Collection<TransportistasEntity> transportistasByIdContra) {
        this.transportistasByIdContra = transportistasByIdContra;
    }
}
