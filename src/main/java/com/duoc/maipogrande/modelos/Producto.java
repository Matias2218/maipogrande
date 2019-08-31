package com.duoc.maipogrande.modelos;
import org.apache.tomcat.util.codec.binary.Base64;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.sql.Blob;
import java.time.LocalDateTime;

@Entity
@Table(name = "Productos")
@NamedStoredProcedureQueries({
        @NamedStoredProcedureQuery(
                name = "buscarProductosPorIdProd",
                procedureName = "BUSCARPRODUCTOSPORIDPROD",
                resultClasses = {Producto.class},
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
                name = "crearProducto",
                procedureName = "CREARPRODUCTO",
                parameters = {
                        @StoredProcedureParameter(
                                mode = ParameterMode.IN,
                                name = "nombre",
                                type = String.class
                        ),
                        @StoredProcedureParameter(
                                mode = ParameterMode.IN,
                                name = "precio",
                                type = Integer.class
                        ),
                        @StoredProcedureParameter(
                                mode = ParameterMode.IN,
                                name = "imagen",
                                type = Blob.class
                        ),
                        @StoredProcedureParameter(
                                mode = ParameterMode.IN,
                                name = "stock",
                                type = Integer.class
                        ),
                        @StoredProcedureParameter(
                                mode = ParameterMode.IN,
                                name = "tipo",
                                type = Character.class
                        ),
                        @StoredProcedureParameter(
                                mode = ParameterMode.IN,
                                name = "calidad",
                                type = Byte.class
                        ),
                        @StoredProcedureParameter(
                                mode = ParameterMode.IN,
                                name = "fechaIngreso",
                                type = LocalDateTime.class
                        ),
                        @StoredProcedureParameter(
                                mode = ParameterMode.IN,
                                name = "idProd",
                                type = Long.class
                        ),
                }
        ),
        @NamedStoredProcedureQuery(
                name = "buscarProductosPorId",
                procedureName = "BUSCARPRODUCTOSPORID",
                resultClasses = {Producto.class},
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
                name = "actualizarProducto",
                procedureName = "ACTUALIZARPRODUCTO",
                parameters = {
                        @StoredProcedureParameter(
                                mode = ParameterMode.IN,
                                name = "idProdu",
                                type = Long.class
                        ),
                        @StoredProcedureParameter(
                                mode = ParameterMode.IN,
                                name = "nombre",
                                type = String.class
                        ),
                        @StoredProcedureParameter(
                                mode = ParameterMode.IN,
                                name = "precio",
                                type = Integer.class
                        ),
                        @StoredProcedureParameter(
                                mode = ParameterMode.IN,
                                name = "stock",
                                type = Integer.class
                        ),
                        @StoredProcedureParameter(
                                mode = ParameterMode.IN,
                                name = "imagen",
                                type = Blob.class
                        ),
                        @StoredProcedureParameter(
                                mode = ParameterMode.IN,
                                name = "tipo",
                                type = Character.class
                        ),
                        @StoredProcedureParameter(
                                mode = ParameterMode.IN,
                                name = "calidad",
                                type = Byte.class
                        ),
                }
        ),
        @NamedStoredProcedureQuery(
                name = "eliminarProductoPorId",
                procedureName = "BORRARPRODUCTO",
                parameters = {
                        @StoredProcedureParameter(
                                mode = ParameterMode.IN,
                                name = "id",
                                type = Long.class
                        ),
                }
        ),
})
public class Producto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idProdu;
    @NotNull
    @Column(length = 50, nullable = false)
    private String nombreProdu;
    @Column(nullable = false, columnDefinition = "DATE")
    private LocalDateTime fechaIngresoProdu;
    @Min(1)
    @Column(nullable = false)
    private Byte calidadProdu;
    @Min(1)
    @Column(nullable = false)
    private Integer precioProdu;
    @Lob
    @Column(columnDefinition = "BLOB")
    private byte[] imagenProdu;
    @NotNull
    @Column(length = 1, nullable = false)
    private Character tipoComercializacionProdu;
    @Min(0)
    @Column(nullable = false)
    private Integer stockProdu;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "ID_PROD")
    private Productor productor;

    public Producto() {
    }

    public Long getIdProdu() {
        return idProdu;
    }

    public void setIdProdu(Long idProdu) {
        this.idProdu = idProdu;
    }

    public String getNombreProdu() {
        return nombreProdu;
    }

    public void setNombreProdu(String nombreProdu) {
        this.nombreProdu = nombreProdu;
    }

    public LocalDateTime getFechaIngresoProdu() {
        return fechaIngresoProdu;
    }

    public byte[] getImagenProdu() {
        return imagenProdu;
    }

    public void setImagenProdu(byte[] imagenProdu) {
        this.imagenProdu = imagenProdu;
    }

    public void setFechaIngresoProdu(LocalDateTime fechaIngresoProdu) {
        this.fechaIngresoProdu = fechaIngresoProdu;
    }

    public Byte getCalidadProdu() {
        return calidadProdu;
    }

    public void setCalidadProdu(Byte calidadProdu) {
        this.calidadProdu = calidadProdu;
    }

    public Integer getPrecioProdu() {
        return precioProdu;
    }

    public void setPrecioProdu(Integer precioProdu) {
        this.precioProdu = precioProdu;
    }

    public Character getTipoComercializacionProdu() {
        return tipoComercializacionProdu;
    }

    public void setTipoComercializacionProdu(Character tipoComercializacionProdu) {
        this.tipoComercializacionProdu = tipoComercializacionProdu;
    }

    public Integer getStockProdu() {
        return stockProdu;
    }

    public void setStockProdu(Integer stockProdu) {
        this.stockProdu = stockProdu;
    }

    public Productor getProductor() {
        return productor;
    }

    public void setProductor(Productor productor) {
        this.productor = productor;
    }

    public static String convertirImagen(byte[] blob )
    {
        return  "data:image/png;base64," + Base64.encodeBase64String(blob);
    }
}
