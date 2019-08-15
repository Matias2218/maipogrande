package com.duoc.maipogrande.modelos;

import javax.persistence.*;
import java.sql.Time;
import java.util.Collection;
import java.util.Objects;

@Entity
@Table(name = "ADMINISTRADORES", schema = "ROOT")
public class AdministradoresEntity {
    private long idAdm;
    private String nombreAdm;
    private String apellidosAdm;
    private String rutAdm;
    private String emailAdm;
    private String contrasenaAdm;
    private Time fechaCreacion;
    private String telefonoAdm;
    private Collection<ConsultoresEntity> consultoresByIdAdm;
    private Collection<SolicitudesEntity> solicitudesByIdAdm;

    @Id
    @Column(name = "ID_ADM")
    public long getIdAdm() {
        return idAdm;
    }

    public void setIdAdm(long idAdm) {
        this.idAdm = idAdm;
    }

    @Basic
    @Column(name = "NOMBRE_ADM")
    public String getNombreAdm() {
        return nombreAdm;
    }

    public void setNombreAdm(String nombreAdm) {
        this.nombreAdm = nombreAdm;
    }

    @Basic
    @Column(name = "APELLIDOS_ADM")
    public String getApellidosAdm() {
        return apellidosAdm;
    }

    public void setApellidosAdm(String apellidosAdm) {
        this.apellidosAdm = apellidosAdm;
    }

    @Basic
    @Column(name = "RUT_ADM")
    public String getRutAdm() {
        return rutAdm;
    }

    public void setRutAdm(String rutAdm) {
        this.rutAdm = rutAdm;
    }

    @Basic
    @Column(name = "EMAIL_ADM")
    public String getEmailAdm() {
        return emailAdm;
    }

    public void setEmailAdm(String emailAdm) {
        this.emailAdm = emailAdm;
    }

    @Basic
    @Column(name = "CONTRASENA_ADM")
    public String getContrasenaAdm() {
        return contrasenaAdm;
    }

    public void setContrasenaAdm(String contrasenaAdm) {
        this.contrasenaAdm = contrasenaAdm;
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
    @Column(name = "TELEFONO_ADM")
    public String getTelefonoAdm() {
        return telefonoAdm;
    }

    public void setTelefonoAdm(String telefonoAdm) {
        this.telefonoAdm = telefonoAdm;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AdministradoresEntity that = (AdministradoresEntity) o;
        return idAdm == that.idAdm &&
                Objects.equals(nombreAdm, that.nombreAdm) &&
                Objects.equals(apellidosAdm, that.apellidosAdm) &&
                Objects.equals(rutAdm, that.rutAdm) &&
                Objects.equals(emailAdm, that.emailAdm) &&
                Objects.equals(contrasenaAdm, that.contrasenaAdm) &&
                Objects.equals(fechaCreacion, that.fechaCreacion) &&
                Objects.equals(telefonoAdm, that.telefonoAdm);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idAdm, nombreAdm, apellidosAdm, rutAdm, emailAdm, contrasenaAdm, fechaCreacion, telefonoAdm);
    }

    @OneToMany(mappedBy = "administradoresByAdministradoresIdAdm")
    public Collection<ConsultoresEntity> getConsultoresByIdAdm() {
        return consultoresByIdAdm;
    }

    public void setConsultoresByIdAdm(Collection<ConsultoresEntity> consultoresByIdAdm) {
        this.consultoresByIdAdm = consultoresByIdAdm;
    }

    @OneToMany(mappedBy = "administradoresByAdministradoresIdAdm")
    public Collection<SolicitudesEntity> getSolicitudesByIdAdm() {
        return solicitudesByIdAdm;
    }

    public void setSolicitudesByIdAdm(Collection<SolicitudesEntity> solicitudesByIdAdm) {
        this.solicitudesByIdAdm = solicitudesByIdAdm;
    }
}
