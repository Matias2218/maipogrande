package com.duoc.maipogrande.modelos;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Entity
@Table(name = "Clientes")
@NamedStoredProcedureQueries({
        @NamedStoredProcedureQuery(
                name = "buscarCliPorEmail",
                procedureName = "BUSCARCLIENTEPOREMAIL",
                resultClasses = {Cliente.class},
                parameters = {
                        @StoredProcedureParameter(
                                mode = ParameterMode.IN,
                                name = "email",
                                type = String.class
                        ),
                        @StoredProcedureParameter(
                                mode = ParameterMode.REF_CURSOR,
                                name = "q",
                                type = void.class
                        ),
                }
        )
}
)
public class Cliente {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idCli;
    @NotNull
    @Column(length = 50, nullable = false)
    private String nombreCli;
    @NotNull
    @Column(length = 100, nullable = false)
    private String apellidosCli;
    @NotNull
    @Column(length = 11, nullable = false)
    private String rutCli;
    @NotNull
    @Column(length = 100, nullable = false, unique = true)
    private String emailCli;
    @NotNull
    @Column(length = 50, nullable = false)
    private String contraseñaCli;
    @Column(nullable = false, columnDefinition = "DATE")
    private LocalDateTime fechaCreacionCli;
    @NotNull
    @Column(length = 12, nullable = false)
    private String telefonoCli;
    @NotNull
    @Column(length = 1, nullable = false)
    private Character tipoCli;

    public Cliente() {
    }

    public Long getIdCli() {
        return idCli;
    }

    public void setIdCli(Long idCli) {
        this.idCli = idCli;
    }

    public String getNombreCli() {
        return nombreCli;
    }

    public void setNombreCli(String nombreCli) {
        this.nombreCli = nombreCli;
    }

    public String getApellidosCli() {
        return apellidosCli;
    }

    public void setApellidosCli(String apellidosCli) {
        this.apellidosCli = apellidosCli;
    }

    public String getRutCli() {
        return rutCli;
    }

    public void setRutCli(String rutCli) {
        this.rutCli = rutCli;
    }

    public String getEmailCli() {
        return emailCli;
    }

    public void setEmailCli(String emailCli) {
        this.emailCli = emailCli;
    }

    public String getContraseñaCli() {
        return contraseñaCli;
    }

    public void setContraseñaCli(String contraseñaCli) {
        this.contraseñaCli = contraseñaCli;
    }

    public LocalDateTime getFechaCreacionCli() {
        return fechaCreacionCli;
    }

    public void setFechaCreacionCli(LocalDateTime fechaCreacionCli) {
        this.fechaCreacionCli = fechaCreacionCli;
    }

    public String getTelefonoCli() {
        return telefonoCli;
    }

    public void setTelefonoCli(String telefonoCli) {
        this.telefonoCli = telefonoCli;
    }

    public Character getTipoCli() {
        return tipoCli;
    }

    public void setTipoCli(Character tipoCli) {
        this.tipoCli = tipoCli;
    }
}
