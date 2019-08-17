package com.duoc.maipogrande.modelos;

import javax.persistence.*;
import javax.validation.constraints.Min;

@Entity
@Table(name = "OFERTAS_TRANSPORTISTAS")
public class OfertaTransportista {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idOfert;
    @Min(1)
    @Column(nullable = false)
    private Integer precioOfertaOfert;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "ID_TRAN")
    private Transportista transportista;

    public OfertaTransportista() {
    }

    public Long getIdOfert() {
        return idOfert;
    }

    public void setIdOfert(Long idOfert) {
        this.idOfert = idOfert;
    }

    public Integer getPrecioOfertaOfert() {
        return precioOfertaOfert;
    }

    public void setPrecioOfertaOfert(Integer precioOfertaOfert) {
        this.precioOfertaOfert = precioOfertaOfert;
    }

    public Transportista getTransportista() {
        return transportista;
    }

    public void setTransportista(Transportista transportista) {
        this.transportista = transportista;
    }
}
