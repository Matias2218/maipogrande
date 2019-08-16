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
    @Column(nullable = false)
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

}
