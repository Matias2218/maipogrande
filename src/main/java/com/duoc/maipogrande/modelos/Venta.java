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
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "DETALLES_OFERTAS_TRANSPORTISTAS", joinColumns = {@JoinColumn(name = "ID_VENTA")}, inverseJoinColumns = {@JoinColumn(name = "ID_OFERT")})
    private List<Producto> productos;




}
