package com.duoc.maipogrande.modelos;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;


@Entity
@Table(name = "Productores")
@NamedStoredProcedureQueries({
        @NamedStoredProcedureQuery(
                name = "buscarProdPorEmail",
                procedureName = "BUSCARPRODUCTORPOREMAIL",
                resultClasses = {Productor.class},
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
        ),
        @NamedStoredProcedureQuery(
                name = "buscarProdPorId",
                procedureName = "BUSCARPRODUCTORPORID",
                resultClasses = {Productor.class},
                parameters = {
                        @StoredProcedureParameter(
                                mode = ParameterMode.IN,
                                name = "id",
                                type = Long.class
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
public class Productor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idProd;
    @NotNull
    @Column(length = 11, nullable = false)
    private String rutProd;
    @NotNull
    @Column(length = 50, nullable = false)
    private String nombreProd;
    @Column(length = 50)
    private String apellidoProd;
    @NotNull
    @Column(length = 80, nullable = false,  unique = true)
    private String emailProd;
    @NotNull
    @Column(length = 60, nullable = false)
    private String contraseñaProd;
    @Column(nullable = false, columnDefinition = "DATE")
    private LocalDateTime fechaCreacionProd;
    @NotNull
    @Column(length = 12, nullable = false)
    private String telefonoProd;
    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "ID_CONTRA")
    private Contrato contrato;

    public Productor() {
    }

    public Long getIdProd() {
        return idProd;
    }

    public void setIdProd(Long idProd) {
        this.idProd = idProd;
    }

    public String getRutProd() {
        return rutProd;
    }

    public void setRutProd(String rutProd) {
        this.rutProd = rutProd;
    }

    public String getNombreProd() {
        return nombreProd;
    }

    public void setNombreProd(String nombreProd) {
        this.nombreProd = nombreProd;
    }

    public String getApellidoProd() {
        return apellidoProd;
    }

    public void setApellidoProd(String apellidoProd) {
        this.apellidoProd = apellidoProd;
    }

    public String getEmailProd() {
        return emailProd;
    }

    public void setEmailProd(String emailProd) {
        this.emailProd = emailProd;
    }

    public String getContraseñaProd() {
        return contraseñaProd;
    }

    public void setContraseñaProd(String contraseñaProd) {
        this.contraseñaProd = contraseñaProd;
    }

    public LocalDateTime getFechaCreacionProd() {
        return fechaCreacionProd;
    }

    public void setFechaCreacionProd(LocalDateTime fechaCreacionProd) {
        this.fechaCreacionProd = fechaCreacionProd;
    }

    public String getTelefonoProd() {
        return telefonoProd;
    }

    public void setTelefonoProd(String telefonoProd) {
        this.telefonoProd = telefonoProd;
    }

    public Contrato getContrato() {
        return contrato;
    }

    public void setContrato(Contrato contrato) {
        this.contrato = contrato;
    }
}
