package com.duoc.maipogrande.modelos;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Entity
@Table(name = "Productos")
public class Producto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idProdu;
    @NotNull
    @Column(length = 50, nullable = false)
    private String nombreProdu;
    @Column(nullable = false, columnDefinition = "DATE")
    private LocalDateTime fechaIngresoProdu;
    @Min(1)
    @Column(nullable = false)
    private Byte calidadProdu;
    @Min(1)
    @Column(nullable = false)
    private Integer precioProdu;
    @NotNull
    @Column(length = 1, nullable = false)
    private Character tipoComercializacionProdu;
    @Min(0)
    @Column(nullable = false)
    private Integer stockProdu;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "ID_PROD")
    private Productor productor;

    public Producto() {
    }

    public Long getIdProdu() {
        return idProdu;
    }

    public void setIdProdu(Long idProdu) {
        this.idProdu = idProdu;
    }

    public String getNombreProdu() {
        return nombreProdu;
    }

    public void setNombreProdu(String nombreProdu) {
        this.nombreProdu = nombreProdu;
    }

    public LocalDateTime getFechaIngresoProdu() {
        return fechaIngresoProdu;
    }

    public void setFechaIngresoProdu(LocalDateTime fechaIngresoProdu) {
        this.fechaIngresoProdu = fechaIngresoProdu;
    }

    public Byte getCalidadProdu() {
        return calidadProdu;
    }

    public void setCalidadProdu(Byte calidadProdu) {
        this.calidadProdu = calidadProdu;
    }

    public Integer getPrecioProdu() {
        return precioProdu;
    }

    public void setPrecioProdu(Integer precioProdu) {
        this.precioProdu = precioProdu;
    }

    public Character getTipoComercializacionProdu() {
        return tipoComercializacionProdu;
    }

    public void setTipoComercializacionProdu(Character tipoComercializacionProdu) {
        this.tipoComercializacionProdu = tipoComercializacionProdu;
    }

    public Integer getStockProdu() {
        return stockProdu;
    }

    public void setStockProdu(Integer stockProdu) {
        this.stockProdu = stockProdu;
    }

    public Productor getProductor() {
        return productor;
    }

    public void setProductor(Productor productor) {
        this.productor = productor;
    }
}
