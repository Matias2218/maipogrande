package com.duoc.maipogrande.modelos;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.util.List;

@Entity
@Table(name = "Ventas")
public class Venta {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idVenta;
    @NotNull
    @Column(length = 60, nullable = false)
    private String paisOrigen;
    @Min(1)
    @Column(nullable = false)
    private Integer precioTotal;
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "DETALLES_VENTAS_PRODUCTOS", joinColumns = {@JoinColumn(name = "ID_VENTA")}, inverseJoinColumns = {@JoinColumn(name = "ID_OFERP")})
    private List<OfertaProducto> ofertaProductos;
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "DETALLES_OFERTAS_TRAN", joinColumns = {@JoinColumn(name = "ID_VENTA")}, inverseJoinColumns = {@JoinColumn(name = "ID_OFERT")})
    private List<OfertaTransportista> ofertaTransportistas;
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ID_SOL")
    private Solicitud solicitud;

    public Venta() {
    }

    public Long getIdVenta() {
        return idVenta;
    }

    public void setIdVenta(Long idVenta) {
        this.idVenta = idVenta;
    }

    public String getPaisOrigen() {
        return paisOrigen;
    }

    public void setPaisOrigen(String paisOrigen) {
        this.paisOrigen = paisOrigen;
    }

    public Integer getPrecioTotal() {
        return precioTotal;
    }

    public void setPrecioTotal(Integer precioTotal) {
        this.precioTotal = precioTotal;
    }

    public List<OfertaProducto> getOfertaProductos() {
        return ofertaProductos;
    }

    public void setOfertaProductos(List<OfertaProducto> ofertaProductos) {
        this.ofertaProductos = ofertaProductos;
    }

    public List<OfertaTransportista> getOfertaTransportistas() {
        return ofertaTransportistas;
    }

    public void setOfertaTransportistas(List<OfertaTransportista> ofertaTransportistas) {
        this.ofertaTransportistas = ofertaTransportistas;
    }

    public Solicitud getSolicitud() {
        return solicitud;
    }

    public void setSolicitud(Solicitud solicitud) {
        this.solicitud = solicitud;
    }
}
