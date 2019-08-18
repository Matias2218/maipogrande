package com.duoc.maipogrande.modelos;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Entity
@Table(name = "Administradores")
public class Administrador {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idAdm;
    @NotNull
    @Column(length = 50, nullable = false)
    private String nombreAdm;
    @NotNull
    @Column(length = 100, nullable = false)
    private String apellidosAdm;
    @NotNull
    @Column(length = 11, nullable = false)
    private String rutAdm;
    @NotNull
    @Column(length = 100, nullable = false,  unique = true)
    private String emailAdm;
    @NotNull
    @Column(length = 50, nullable = false)
    private String contraseñaAdm;
    @Column(nullable = false, columnDefinition = "DATE")
    private LocalDateTime fechaCreacionAdm;
    @NotNull
    @Column(length = 12, nullable = false)
    private String telefonoAdm;

    public Administrador() {
    }

    public Long getIdAdm() {
        return idAdm;
    }

    public void setIdAdm(Long idAdm) {
        this.idAdm = idAdm;
    }

    public String getNombreAdm() {
        return nombreAdm;
    }

    public void setNombreAdm(String nombreAdm) {
        this.nombreAdm = nombreAdm;
    }

    public String getApellidosAdm() {
        return apellidosAdm;
    }

    public void setApellidosAdm(String apellidosAdm) {
        this.apellidosAdm = apellidosAdm;
    }

    public String getRutAdm() {
        return rutAdm;
    }

    public void setRutAdm(String rutAdm) {
        this.rutAdm = rutAdm;
    }

    public String getEmailAdm() {
        return emailAdm;
    }

    public void setEmailAdm(String emailAdm) {
        this.emailAdm = emailAdm;
    }

    public String getContraseñaAdm() {
        return contraseñaAdm;
    }

    public void setContraseñaAdm(String contraseñaAdm) {
        this.contraseñaAdm = contraseñaAdm;
    }

    public LocalDateTime getFechaCreacionAdm() {
        return fechaCreacionAdm;
    }

    public void setFechaCreacionAdm(LocalDateTime fechaCreacionAdm) {
        this.fechaCreacionAdm = fechaCreacionAdm;
    }

    public String getTelefonoAdm() {
        return telefonoAdm;
    }

    public void setTelefonoAdm(String telefonoAdm) {
        this.telefonoAdm = telefonoAdm;
    }
}
