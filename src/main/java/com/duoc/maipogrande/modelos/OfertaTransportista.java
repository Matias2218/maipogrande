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

}
