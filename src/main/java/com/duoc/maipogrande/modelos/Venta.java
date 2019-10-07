package com.duoc.maipogrande.modelos;


import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * Clase que almacena los valores que seran requeridos para su correspondiente implementacion dentro de la pagina WEB
 */
@Entity
@Table(name = "Ventas")
@NamedStoredProcedureQueries({
        @NamedStoredProcedureQuery(
                name = "buscarVentasParaSubasta",
                procedureName = "BUSCARVENTASPARASUBASTA",
                resultClasses = {Venta.class},
                parameters = {
                        @StoredProcedureParameter(
                                mode = ParameterMode.IN,
                                name = "i",
                                type = Integer.class
                        ),
                        @StoredProcedureParameter(
                                mode = ParameterMode.REF_CURSOR,
                                name = "q",
                                type = void.class
                        ),
                }
        ),
        @NamedStoredProcedureQuery(
                name = "buscarVentasActivasProductor",
                procedureName = "BUSCARVENTASACTIVASPRODUCTOR",
                resultClasses = {Venta.class},
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
        ),
        @NamedStoredProcedureQuery(
                name = "buscarVentaParaIdParaSubasta",
                procedureName = "BUSCARVENTAPORIDPARASUBASTA",
                resultClasses = {Venta.class},
                parameters = {
                        @StoredProcedureParameter(
                                mode = ParameterMode.IN,
                                name = "id",
                                type = Integer.class
                        ),
                        @StoredProcedureParameter(
                                mode = ParameterMode.REF_CURSOR,
                                name = "q",
                                type = void.class
                        ),
                }
        ),

})
public class Venta {
    // Variable que almacena el id de la venta, esto se le asigna a cada uno para su correspondiente identificacion
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idVenta;
    // Variable que almacena el pais de origen de la venta
    @NotNull
    @Column(length = 60, nullable = false)
    private String paisOrigen;
    // Variable que almacena el tipo de venta, entre los cuales existen intero e externo
    @NotNull
    @Column(nullable = false, length = 1)
    private Character tipoVenta;
    // Variable que almacena el estado de la venta
    @Column(length = 1, nullable = false)
    private Character estadoVenta;
    // Variable que hereda de la clase Administrador, esto con el fin de verificar quien aprobo o rechazo la venta
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ID_ADM")
    private Administrador administrador;
    // Variable que hereda de la clase OfertaTransportista, con el fin de determinar la oferta final para la venta
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "DETALLES_OFERTAS_TRAN", joinColumns = {@JoinColumn(name = "ID_VENTA")}, inverseJoinColumns = {@JoinColumn(name = "ID_OFERT")})
    private List<OfertaTransportista> ofertaTransportistas;
    // Varible que hereda de la clase solicitud, esto con el din de determinar la solicitud creada para la venta
    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "ID_SOL")
    private Solicitud solicitud;
    // Variable que hereda de la clase OfertaProducto, esto con el fin determinar la oferta final del producto para la venta
    @OneToMany(mappedBy = "venta")
    private List<OfertaProducto> ofertaProductos;
    // Inicio de los metodos accesadores y mutadores
    public Venta() {
    }

    public List<OfertaProducto> getOfertaProductos() {
        return ofertaProductos;
    }

    public void setOfertaProductos(List<OfertaProducto> ofertaProductos) {
        this.ofertaProductos = ofertaProductos;
    }

    public Character getEstadoVenta() {
        return estadoVenta;
    }

    public void setEstadoVenta(Character estadoVenta) {
        this.estadoVenta = estadoVenta;
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

    public Character getTipoVenta() {
        return tipoVenta;
    }

    public Administrador getAdministrador() {
        return administrador;
    }

    public void setAdministrador(Administrador administrador) {
        this.administrador = administrador;
    }

    public void setTipoVenta(Character tipoVenta) {
        this.tipoVenta = tipoVenta;
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
