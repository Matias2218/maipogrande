package com.duoc.maipogrande.modelos;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.Null;
import java.util.List;

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
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "DETALLES_OFERTAS_PRODUCTOS", joinColumns = {@JoinColumn(name = "ID_OFERP")}, inverseJoinColumns = {@JoinColumn(name = "ID_PRODU")})
    private List<Producto> productos;

    public OfertaProducto() {
    }

    public Long getIdOferp() {
        return idOferp;
    }

    public void setIdOferp(Long idOferp) {
        this.idOferp = idOferp;
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

    public List<Producto> getProductos() {
        return productos;
    }

    public void setProductos(List<Producto> productos) {
        this.productos = productos;
    }
}
