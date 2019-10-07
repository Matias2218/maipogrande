package com.duoc.maipogrande.modelos;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;

/**
 *Clase que guarda las ofertas realizadas a la publicacion del producto
 */
@Entity
@Table(name = "Ofertas_Productos")
@NamedStoredProcedureQueries({
        @NamedStoredProcedureQuery(
                name = "crearOfertaProducto",
                procedureName = "CREAROFERTAPRODUCTO",
                parameters = {
                        @StoredProcedureParameter(
                                mode = ParameterMode.IN,
                                name = "paisOrigen",
                                type = String.class
                        ),
                        @StoredProcedureParameter(
                                mode = ParameterMode.IN,
                                name = "precioOferta",
                                type = Integer.class
                        ),
                        @StoredProcedureParameter(
                                mode = ParameterMode.IN,
                                name = "unidadMasaOferta",
                                type = String.class
                        ),
                        @StoredProcedureParameter(
                                mode = ParameterMode.IN,
                                name = "idProdu",
                                type = Long.class
                        ),
                        @StoredProcedureParameter(
                                mode = ParameterMode.IN,
                                name = "idVenta",
                                type = Long.class
                        ),


                }
        ),
})
public class OfertaProducto {
    // Variable que almacena el id de la oferta, este valor es unico y auti incrementable
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idOferp;
    //Varible que alamcena el pais de la oferta para luego poder realizar las acciones conrrespondientes con el pedido
    @Null
    @Column(length = 80, nullable = false)
    private String paisOrigen;
    // Varible que almacena el monto total de la oferta realizada
    @Min(1)
    @Column(nullable = false)
    private Integer precioOferta;
    // Variable que alamcena el stock ofrecido para suplir el requerimiento de la demanda
    @Min(0)
    @Column(nullable = false)
    private Integer stockOferta;
    // Variable que determina la unidad de medidad del cargamento
    @NotNull
    @Column(length = 2, nullable = false)
    private String unidadMasaOferta;
    // Varible que almacena el tipo de producto ofrecido
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "ID_PRODU")
    private Producto producto;
    // Varible que hereda de la venta
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "ID_VENTA")
    private Venta venta;
    // Inicio de los metodos accesadores y mutadores
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
