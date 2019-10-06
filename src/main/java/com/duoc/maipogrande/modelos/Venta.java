package com.duoc.maipogrande.modelos;


import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.List;

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
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idVenta;
    @NotNull
    @Column(length = 60, nullable = false)
    private String paisOrigen;
    @NotNull
    @Column(nullable = false, length = 1)
    private Character tipoVenta;
    @Column(length = 1, nullable = false)
    private Character estadoVenta;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ID_ADM")
    private Administrador administrador;
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "DETALLES_OFERTAS_TRAN", joinColumns = {@JoinColumn(name = "ID_VENTA")}, inverseJoinColumns = {@JoinColumn(name = "ID_OFERT")})
    private List<OfertaTransportista> ofertaTransportistas;
    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "ID_SOL")
    private Solicitud solicitud;

    public Venta() {
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
