package com.duoc.maipogrande.modelos;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;

@Entity
@Table(name = "Ofertas_Productos")
public class OfertaProducto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idOferp;
    @Null
    @Column(length = 80, nullable = false)
    private String paisOrigen;
    @Min(1)
    @Column(nullable = false)
    private Integer precioOferta;
    @Min(0)
    @Column(nullable = false)
    private Integer stockOferta;
    @NotNull
    @Column(length = 2, nullable = false)
    private String unidadMasaOferta;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "ID_PRODU")
    private Producto producto;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "ID_VENTA")
    private Venta venta;



    public OfertaProducto() {
    }

    public Long getIdOferp() {
        return idOferp;
    }

    public void setIdOferp(Long idOferp) {
        this.idOferp = idOferp;
    }

    public Integer getStockOferta() {
        return stockOferta;
    }

    public Venta getVenta() {
        return venta;
    }

    public void setVenta(Venta venta) {
        this.venta = venta;
    }

    public void setStockOferta(Integer stockOferta) {
        this.stockOferta = stockOferta;
    }

    public String getUnidadMasaOferta() {
        return unidadMasaOferta;
    }

    public void setUnidadMasaOferta(String unidadMasaOferta) {
        this.unidadMasaOferta = unidadMasaOferta;
    }

    public String getPaisOrigen() {
        return paisOrigen;
    }

    public void setPaisOrigen(String paisOrigen) {
        this.paisOrigen = paisOrigen;
    }

    public Integer getPrecioOferta() {
        return precioOferta;
    }

    public void setPrecioOferta(Integer precioOferta) {
        this.precioOferta = precioOferta;
    }

    public Producto getProducto() {
        return producto;
    }

    public void setProducto(Producto producto) {
        this.producto = producto;
    }
}
