package com.duoc.maipogrande.modelos;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Entity
@Table(name = "Consultores")
public class Consultor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idCon;
    @NotNull
    @Column(length = 50, nullable = false)
    private String nombreCon;
    @NotNull
    @Column(length = 100, nullable = false)
    private String apellidosCon;
    @NotNull
    @Column(length = 11, nullable = false)
    private String rutCon;
    @NotNull
    @Column(length = 100, nullable = false,  unique = true)
    private String emailCon;
    @NotNull
    @Column(length = 50, nullable = false)
    private String contraseñaCon;
    @Column(nullable = false, columnDefinition = "DATE")
    private LocalDateTime fechaCreacionCon;
    @NotNull
    @Column(length = 12, nullable = false)
    private String telefonoCon;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "ID_ADM")
    private Administrador administrador;

    public Consultor() {
    }

    public Long getIdCon() {
        return idCon;
    }

    public void setIdCon(Long idCon) {
        this.idCon = idCon;
    }

    public String getNombreCon() {
        return nombreCon;
    }

    public void setNombreCon(String nombreCon) {
        this.nombreCon = nombreCon;
    }

    public String getApellidosCon() {
        return apellidosCon;
    }

    public void setApellidosCon(String apellidosCon) {
        this.apellidosCon = apellidosCon;
    }

    public String getRutCon() {
        return rutCon;
    }

    public void setRutCon(String rutCon) {
        this.rutCon = rutCon;
    }

    public String getEmailCon() {
        return emailCon;
    }

    public void setEmailCon(String emailCon) {
        this.emailCon = emailCon;
    }

    public String getContraseñaCon() {
        return contraseñaCon;
    }

    public void setContraseñaCon(String contraseñaCon) {
        this.contraseñaCon = contraseñaCon;
    }

    public LocalDateTime getFechaCreacionCon() {
        return fechaCreacionCon;
    }

    public void setFechaCreacionCon(LocalDateTime fechaCreacionCon) {
        this.fechaCreacionCon = fechaCreacionCon;
    }

    public String getTelefonoCon() {
        return telefonoCon;
    }

    public void setTelefonoCon(String telefonoCon) {
        this.telefonoCon = telefonoCon;
    }

    public Administrador getAdministrador() {
        return administrador;
    }

    public void setAdministrador(Administrador administrador) {
        this.administrador = administrador;
    }
}
