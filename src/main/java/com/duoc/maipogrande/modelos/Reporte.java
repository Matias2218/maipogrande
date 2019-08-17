package com.duoc.maipogrande.modelos;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "Reportes")
public class Reporte {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idRep;
    @NotNull
    @Column(length = 150, nullable = false)
    private String asuntoRep;
    @NotNull
    @Column(length = 500, nullable = false)
    private String descripcionRep;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "ID_VEN")
    private Venta venta;

    public Reporte() {
    }

    public Long getIdRep() {
        return idRep;
    }

    public void setIdRep(Long idRep) {
        this.idRep = idRep;
    }

    public String getAsuntoRep() {
        return asuntoRep;
    }

    public void setAsuntoRep(String asuntoRep) {
        this.asuntoRep = asuntoRep;
    }

    public String getDescripcionRep() {
        return descripcionRep;
    }

    public void setDescripcionRep(String descripcionRep) {
        this.descripcionRep = descripcionRep;
    }

    public Venta getVenta() {
        return venta;
    }

    public void setVenta(Venta venta) {
        this.venta = venta;
    }
}
