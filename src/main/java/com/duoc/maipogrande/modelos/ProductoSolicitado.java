package com.duoc.maipogrande.modelos;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Table(name = "Productos_Solicitados")
@NamedStoredProcedureQueries({
        @NamedStoredProcedureQuery(
                name = "crearProductosSolicitados",
                procedureName = "CREARPRODUCTOSOLICITADOS",
                parameters = {
                        @StoredProcedureParameter(
                                mode = ParameterMode.IN,
                                name = "nombreProds",
                                type = String.class
                        ),
                        @StoredProcedureParameter(
                                mode = ParameterMode.IN,
                                name = "cantidadProds",
                                type = Integer.class
                        ),
                        @StoredProcedureParameter(
                                mode = ParameterMode.IN,
                                name = "unidadProds",
                                type = String.class
                        ),
                }
        )
})
public class ProductoSolicitado {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idProdS;
    @NotNull
    @Column(length = 50, nullable = false)
    private String nombreProdS;
    @NotNull
    @Size(max = 2)
    @Column(length = 2, nullable = false)
    private String unidadProdS;
    @Min(1)
    @Column(nullable = false)
    private Integer cantidadProdS;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "ID_SOL")
    private Solicitud solicitud;

    public ProductoSolicitado() {
    }

    public ProductoSolicitado(@NotNull String nombreProdS, @NotNull @Size(max = 2) String unidadProdS, @Min(1) Integer cantidadProdS) {
        this.nombreProdS = nombreProdS;
        this.unidadProdS = unidadProdS;
        this.cantidadProdS = cantidadProdS;
    }

    public Long getIdProdS() {
        return idProdS;
    }

    public void setIdProdS(Long idProdS) {
        this.idProdS = idProdS;
    }

    public String getNombreProdS() {
        return nombreProdS;
    }

    public void setNombreProdS(String nombreProdS) {
        this.nombreProdS = nombreProdS;
    }

    public String getUnidadProdS() {
        return unidadProdS;
    }

    public void setUnidadProdS(String unidadProdS) {
        this.unidadProdS = unidadProdS;
    }

    public Integer getCantidadProdS() {
        return cantidadProdS;
    }

    public void setCantidadProdS(Integer cantidadProdS) {
        this.cantidadProdS = cantidadProdS;
    }

    public Solicitud getSolicitud() {
        return solicitud;
    }

    public void setSolicitud(Solicitud solicitud) {
        this.solicitud = solicitud;
    }
}
