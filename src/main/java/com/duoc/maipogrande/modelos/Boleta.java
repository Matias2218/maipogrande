package com.duoc.maipogrande.modelos;

import javax.persistence.*;
import javax.validation.constraints.Min;
import java.time.LocalDateTime;

@Entity
@Table(name = "Boletas")
public class Boleta {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idBol;
    @Column(nullable = false, columnDefinition = "DATE")
    private LocalDateTime fechaEmisionBol;
    @Min(1)
    @Column(nullable = false)
    private Integer precioTotal;
    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "ID_VEN")
    private Venta venta;

    public Boleta() {
    }

    public Integer getPrecioTotal() {
        return precioTotal;
    }

    public void setPrecioTotal(Integer precioTotal) {
        this.precioTotal = precioTotal;
    }

    public Long getIdBol() {
        return idBol;
    }

    public void setIdBol(Long idBol) {
        this.idBol = idBol;
    }

    public LocalDateTime getFechaEmisionBol() {
        return fechaEmisionBol;
    }

    public void setFechaEmisionBol(LocalDateTime fechaEmisionBol) {
        this.fechaEmisionBol = fechaEmisionBol;
    }

    public Venta getVenta() {
        return venta;
    }

    public void setVenta(Venta venta) {
        this.venta = venta;
    }
}
