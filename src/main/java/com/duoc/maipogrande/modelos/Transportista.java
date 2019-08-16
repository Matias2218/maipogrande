package com.duoc.maipogrande.modelos;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Entity
@Table(name = "Transportistas")
public class Transportista {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idTran;
    @NotNull
    @Column(length = 50, nullable = false)
    private String nombreTran;
    @Column(length = 100)
    private String apellidosTran;
    @NotNull
    @Column(length = 11, nullable = false)
    private String rutTran;
    @NotNull
    @Column(length = 80, nullable = false)
    private String emailTran;
    @NotNull
    @Column(length = 60, nullable = false)
    private String contrasñaTran;
    @NotNull
    @Column(nullable = false)
    private LocalDateTime fechaCreacion;
    @NotNull
    @Column(length = 12, nullable = false)
    private String telefonoTran;
    @NotNull
    @Column(length = 6, nullable = false)
    private String patente;
    @NotNull
    @Column(length = 30, nullable = false)
    private String tamaño;
    @NotNull
    @Column(nullable = false)
    private Double capacidadCarga;
    @NotNull
    @Column(length = 1, nullable = false)
    private Character refrigeracion;
    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "ID_CONTRA")
    private Contrato contrato;

    public Transportista() {
    }

    public Long getIdTran() {
        return idTran;
    }

    public void setIdTran(Long idTran) {
        this.idTran = idTran;
    }

    public String getNombreTran() {
        return nombreTran;
    }

    public void setNombreTran(String nombreTran) {
        this.nombreTran = nombreTran;
    }

    public String getApellidosTran() {
        return apellidosTran;
    }

    public void setApellidosTran(String apellidosTran) {
        this.apellidosTran = apellidosTran;
    }

    public String getRutTran() {
        return rutTran;
    }

    public void setRutTran(String rutTran) {
        this.rutTran = rutTran;
    }

    public String getEmailTran() {
        return emailTran;
    }

    public void setEmailTran(String emailTran) {
        this.emailTran = emailTran;
    }

    public String getContrasñaTran() {
        return contrasñaTran;
    }

    public void setContrasñaTran(String contrasñaTran) {
        this.contrasñaTran = contrasñaTran;
    }

    public LocalDateTime getFechaCreacion() {
        return fechaCreacion;
    }

    public void setFechaCreacion(LocalDateTime fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }

    public String getTelefonoTran() {
        return telefonoTran;
    }

    public void setTelefonoTran(String telefonoTran) {
        this.telefonoTran = telefonoTran;
    }

    public String getPatente() {
        return patente;
    }

    public void setPatente(String patente) {
        this.patente = patente;
    }

    public String getTamaño() {
        return tamaño;
    }

    public void setTamaño(String tamaño) {
        this.tamaño = tamaño;
    }

    public Double getCapacidadCarga() {
        return capacidadCarga;
    }

    public void setCapacidadCarga(Double capacidadCarga) {
        this.capacidadCarga = capacidadCarga;
    }

    public Character getRefrigeracion() {
        return refrigeracion;
    }

    public void setRefrigeracion(Character refrigeracion) {
        this.refrigeracion = refrigeracion;
    }

    public Contrato getContrato() {
        return contrato;
    }

    public void setContrato(Contrato contrato) {
        this.contrato = contrato;
    }
}
