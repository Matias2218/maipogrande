package com.duoc.maipogrande.modelos;

import javax.persistence.*;
import java.sql.Time;
import java.util.Collection;
import java.util.Objects;

@Entity
@Table(name = "TRANSPORTISTAS", schema = "ROOT")
public class TransportistasEntity {
    private long idTran;
    private String nombreTran;
    private String apellidosTran;
    private String rutTran;
    private String emailTran;
    private String contrasenaTran;
    private Time fechaCreacion;
    private String telefonoTran;
    private String patente;
    private String tamaño;
    private long capacidadCarga;
    private String refrigeracion;
    private long contratosIdContra;
    private Collection<OfertasTransportistasEntity> ofertasTransportistasByIdTran;
    private ContratosEntity contratosByContratosIdContra;

    @Id
    @Column(name = "ID_TRAN")
    public long getIdTran() {
        return idTran;
    }

    public void setIdTran(long idTran) {
        this.idTran = idTran;
    }

    @Basic
    @Column(name = "NOMBRE_TRAN")
    public String getNombreTran() {
        return nombreTran;
    }

    public void setNombreTran(String nombreTran) {
        this.nombreTran = nombreTran;
    }

    @Basic
    @Column(name = "APELLIDOS_TRAN")
    public String getApellidosTran() {
        return apellidosTran;
    }

    public void setApellidosTran(String apellidosTran) {
        this.apellidosTran = apellidosTran;
    }

    @Basic
    @Column(name = "RUT_TRAN")
    public String getRutTran() {
        return rutTran;
    }

    public void setRutTran(String rutTran) {
        this.rutTran = rutTran;
    }

    @Basic
    @Column(name = "EMAIL_TRAN")
    public String getEmailTran() {
        return emailTran;
    }

    public void setEmailTran(String emailTran) {
        this.emailTran = emailTran;
    }

    @Basic
    @Column(name = "CONTRASENA_TRAN")
    public String getContrasenaTran() {
        return contrasenaTran;
    }

    public void setContrasenaTran(String contrasenaTran) {
        this.contrasenaTran = contrasenaTran;
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
    @Column(name = "TELEFONO_TRAN")
    public String getTelefonoTran() {
        return telefonoTran;
    }

    public void setTelefonoTran(String telefonoTran) {
        this.telefonoTran = telefonoTran;
    }

    @Basic
    @Column(name = "PATENTE")
    public String getPatente() {
        return patente;
    }

    public void setPatente(String patente) {
        this.patente = patente;
    }

    @Basic
    @Column(name = "TAMAÑO")
    public String getTamaño() {
        return tamaño;
    }

    public void setTamaño(String tamaño) {
        this.tamaño = tamaño;
    }

    @Basic
    @Column(name = "CAPACIDAD_CARGA")
    public long getCapacidadCarga() {
        return capacidadCarga;
    }

    public void setCapacidadCarga(long capacidadCarga) {
        this.capacidadCarga = capacidadCarga;
    }

    @Basic
    @Column(name = "REFRIGERACION")
    public String getRefrigeracion() {
        return refrigeracion;
    }

    public void setRefrigeracion(String refrigeracion) {
        this.refrigeracion = refrigeracion;
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
        TransportistasEntity that = (TransportistasEntity) o;
        return idTran == that.idTran &&
                capacidadCarga == that.capacidadCarga &&
                contratosIdContra == that.contratosIdContra &&
                Objects.equals(nombreTran, that.nombreTran) &&
                Objects.equals(apellidosTran, that.apellidosTran) &&
                Objects.equals(rutTran, that.rutTran) &&
                Objects.equals(emailTran, that.emailTran) &&
                Objects.equals(contrasenaTran, that.contrasenaTran) &&
                Objects.equals(fechaCreacion, that.fechaCreacion) &&
                Objects.equals(telefonoTran, that.telefonoTran) &&
                Objects.equals(patente, that.patente) &&
                Objects.equals(tamaño, that.tamaño) &&
                Objects.equals(refrigeracion, that.refrigeracion);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idTran, nombreTran, apellidosTran, rutTran, emailTran, contrasenaTran, fechaCreacion, telefonoTran, patente, tamaño, capacidadCarga, refrigeracion, contratosIdContra);
    }

    @OneToMany(mappedBy = "transportistasByTransportistasIdTran")
    public Collection<OfertasTransportistasEntity> getOfertasTransportistasByIdTran() {
        return ofertasTransportistasByIdTran;
    }

    public void setOfertasTransportistasByIdTran(Collection<OfertasTransportistasEntity> ofertasTransportistasByIdTran) {
        this.ofertasTransportistasByIdTran = ofertasTransportistasByIdTran;
    }

    @ManyToOne
    @JoinColumn(name = "CONTRATOS_ID_CONTRA", referencedColumnName = "ID_CONTRA", nullable = false)
    public ContratosEntity getContratosByContratosIdContra() {
        return contratosByContratosIdContra;
    }

    public void setContratosByContratosIdContra(ContratosEntity contratosByContratosIdContra) {
        this.contratosByContratosIdContra = contratosByContratosIdContra;
    }
}
