package com.duoc.maipogrande.modelos;

import javax.persistence.*;
import java.sql.Time;
import java.util.Collection;
import java.util.Objects;

@Entity
@Table(name = "CLIENTES", schema = "ROOT")
public class ClientesEntity {
    private long idCli;
    private String nombreCli;
    private String apellidosCli;
    private String rutCli;
    private String emailCli;
    private String contraseñaCli;
    private Time fechaCreacion;
    private String tipoCli;
    private String telefonoCli;
    private Collection<SolicitudesEntity> solicitudesByIdCli;

    @Id
    @Column(name = "ID_CLI")
    public long getIdCli() {
        return idCli;
    }

    public void setIdCli(long idCli) {
        this.idCli = idCli;
    }

    @Basic
    @Column(name = "NOMBRE_CLI")
    public String getNombreCli() {
        return nombreCli;
    }

    public void setNombreCli(String nombreCli) {
        this.nombreCli = nombreCli;
    }

    @Basic
    @Column(name = "APELLIDOS_CLI")
    public String getApellidosCli() {
        return apellidosCli;
    }

    public void setApellidosCli(String apellidosCli) {
        this.apellidosCli = apellidosCli;
    }

    @Basic
    @Column(name = "RUT_CLI")
    public String getRutCli() {
        return rutCli;
    }

    public void setRutCli(String rutCli) {
        this.rutCli = rutCli;
    }

    @Basic
    @Column(name = "EMAIL_CLI")
    public String getEmailCli() {
        return emailCli;
    }

    public void setEmailCli(String emailCli) {
        this.emailCli = emailCli;
    }

    @Basic
    @Column(name = "CONTRASEÑA_CLI")
    public String getContraseñaCli() {
        return contraseñaCli;
    }

    public void setContraseñaCli(String contraseñaCli) {
        this.contraseñaCli = contraseñaCli;
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
    @Column(name = "TIPO_CLI")
    public String getTipoCli() {
        return tipoCli;
    }

    public void setTipoCli(String tipoCli) {
        this.tipoCli = tipoCli;
    }

    @Basic
    @Column(name = "TELEFONO_CLI")
    public String getTelefonoCli() {
        return telefonoCli;
    }

    public void setTelefonoCli(String telefonoCli) {
        this.telefonoCli = telefonoCli;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ClientesEntity that = (ClientesEntity) o;
        return idCli == that.idCli &&
                Objects.equals(nombreCli, that.nombreCli) &&
                Objects.equals(apellidosCli, that.apellidosCli) &&
                Objects.equals(rutCli, that.rutCli) &&
                Objects.equals(emailCli, that.emailCli) &&
                Objects.equals(contraseñaCli, that.contraseñaCli) &&
                Objects.equals(fechaCreacion, that.fechaCreacion) &&
                Objects.equals(tipoCli, that.tipoCli) &&
                Objects.equals(telefonoCli, that.telefonoCli);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idCli, nombreCli, apellidosCli, rutCli, emailCli, contraseñaCli, fechaCreacion, tipoCli, telefonoCli);
    }

    @OneToMany(mappedBy = "clientesByClientesIdCli")
    public Collection<SolicitudesEntity> getSolicitudesByIdCli() {
        return solicitudesByIdCli;
    }

    public void setSolicitudesByIdCli(Collection<SolicitudesEntity> solicitudesByIdCli) {
        this.solicitudesByIdCli = solicitudesByIdCli;
    }
}
