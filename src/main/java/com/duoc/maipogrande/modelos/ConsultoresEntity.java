package com.duoc.maipogrande.modelos;

import javax.persistence.*;
import java.sql.Time;
import java.util.Objects;

@Entity
@Table(name = "CONSULTORES", schema = "ROOT")
public class ConsultoresEntity {
    private long id;
    private String nombreCon;
    private String apellidosCon;
    private String rutCon;
    private String emailCon;
    private String contraseñaCon;
    private Time fechaCreacion;
    private String telefonoCon;
    private long administradoresIdAdm;
    private AdministradoresEntity administradoresByAdministradoresIdAdm;

    @Id
    @Column(name = "ID")
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    @Basic
    @Column(name = "NOMBRE_CON")
    public String getNombreCon() {
        return nombreCon;
    }

    public void setNombreCon(String nombreCon) {
        this.nombreCon = nombreCon;
    }

    @Basic
    @Column(name = "APELLIDOS_CON")
    public String getApellidosCon() {
        return apellidosCon;
    }

    public void setApellidosCon(String apellidosCon) {
        this.apellidosCon = apellidosCon;
    }

    @Basic
    @Column(name = "RUT_CON")
    public String getRutCon() {
        return rutCon;
    }

    public void setRutCon(String rutCon) {
        this.rutCon = rutCon;
    }

    @Basic
    @Column(name = "EMAIL_CON")
    public String getEmailCon() {
        return emailCon;
    }

    public void setEmailCon(String emailCon) {
        this.emailCon = emailCon;
    }

    @Basic
    @Column(name = "CONTRASEÑA_CON")
    public String getContraseñaCon() {
        return contraseñaCon;
    }

    public void setContraseñaCon(String contraseñaCon) {
        this.contraseñaCon = contraseñaCon;
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
    @Column(name = "TELEFONO_CON")
    public String getTelefonoCon() {
        return telefonoCon;
    }

    public void setTelefonoCon(String telefonoCon) {
        this.telefonoCon = telefonoCon;
    }

    @Basic
    @Column(name = "ADMINISTRADORES_ID_ADM")
    public long getAdministradoresIdAdm() {
        return administradoresIdAdm;
    }

    public void setAdministradoresIdAdm(long administradoresIdAdm) {
        this.administradoresIdAdm = administradoresIdAdm;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ConsultoresEntity that = (ConsultoresEntity) o;
        return id == that.id &&
                administradoresIdAdm == that.administradoresIdAdm &&
                Objects.equals(nombreCon, that.nombreCon) &&
                Objects.equals(apellidosCon, that.apellidosCon) &&
                Objects.equals(rutCon, that.rutCon) &&
                Objects.equals(emailCon, that.emailCon) &&
                Objects.equals(contraseñaCon, that.contraseñaCon) &&
                Objects.equals(fechaCreacion, that.fechaCreacion) &&
                Objects.equals(telefonoCon, that.telefonoCon);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, nombreCon, apellidosCon, rutCon, emailCon, contraseñaCon, fechaCreacion, telefonoCon, administradoresIdAdm);
    }

    @ManyToOne
    @JoinColumn(name = "ADMINISTRADORES_ID_ADM", referencedColumnName = "ID_ADM", nullable = false)
    public AdministradoresEntity getAdministradoresByAdministradoresIdAdm() {
        return administradoresByAdministradoresIdAdm;
    }

    public void setAdministradoresByAdministradoresIdAdm(AdministradoresEntity administradoresByAdministradoresIdAdm) {
        this.administradoresByAdministradoresIdAdm = administradoresByAdministradoresIdAdm;
    }
}
