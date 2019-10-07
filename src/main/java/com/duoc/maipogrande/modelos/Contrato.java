package com.duoc.maipogrande.modelos;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

/**
 *
 */
@Entity
@Table(name = "Contratos")
public class Contrato {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idContra;
    @Column(nullable = false)
    private LocalDate fechaInicioContra;
    @Column(nullable = false)
    private LocalDate fechaTerminoContra;
    @Lob
    @Column(columnDefinition = "BLOB")
    private byte[] pdfContra;
    @NotNull
    @Column(nullable = false, length = 1)
    private Character tipoContra;

    public Contrato() {
    }

    public Long getIdContra() {
        return idContra;
    }

    public void setIdContra(Long idContra) {
        this.idContra = idContra;
    }

    public LocalDate getFechaInicioContra() {
        return fechaInicioContra;
    }

    public void setFechaInicioContra(LocalDate fechaInicioContra) {
        this.fechaInicioContra = fechaInicioContra;
    }

    public LocalDate getFechaTerminoContra() {
        return fechaTerminoContra;
    }

    public void setFechaTerminoContra(LocalDate fechaTerminoContra) {
        this.fechaTerminoContra = fechaTerminoContra;
    }

    public byte[] getPdfContra() {
        return pdfContra;
    }

    public void setPdfContra(byte[] pdfContra) {
        this.pdfContra = pdfContra;
    }

    public Character getTipoContra() {
        return tipoContra;
    }

    public void setTipoContra(Character tipoContra) {
        this.tipoContra = tipoContra;
    }
}
