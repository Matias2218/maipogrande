package com.duoc.maipogrande.modelos;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "Solicitudes")
public class Solicitud {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idSol;
    @Column(nullable = false)
    private LocalDate fechaEmisionSol;
    @Column(nullable = false)
    private LocalDate fechalimiteSol;
    @NotNull
    @Column(length = 1, nullable = false)
    private Character estadoSol;
    @NotNull
    @Column(length = 60, nullable = false)
    private String paisDestinoSol;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "ID_CLI")
    private Cliente cliente;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "ID_ADM")
    private Administrador administrador;
    @OneToMany(fetch = FetchType.EAGER, mappedBy = "solicitud")
    private List<ProductoSolicitado> productoSolicitados;

    public Solicitud() {
    }

    public Long getIdSol() {
        return idSol;
    }

    public void setIdSol(Long idSol) {
        this.idSol = idSol;
    }

    public LocalDate getFechaEmisionSol() {
        return fechaEmisionSol;
    }

    public void setFechaEmisionSol(LocalDate fechaEmisionSol) {
        this.fechaEmisionSol = fechaEmisionSol;
    }

    public LocalDate getFechalimiteSol() {
        return fechalimiteSol;
    }

    public void setFechalimiteSol(LocalDate fechalimiteSol) {
        this.fechalimiteSol = fechalimiteSol;
    }

    public Character getEstadoSol() {
        return estadoSol;
    }

    public void setEstadoSol(Character estadoSol) {
        this.estadoSol = estadoSol;
    }

    public String getPaisDestinoSol() {
        return paisDestinoSol;
    }

    public void setPaisDestinoSol(String paisDestinoSol) {
        this.paisDestinoSol = paisDestinoSol;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public Administrador getAdministrador() {
        return administrador;
    }

    public void setAdministrador(Administrador administrador) {
        this.administrador = administrador;
    }

    public List<ProductoSolicitado> getProductoSolicitados() {
        return productoSolicitados;
    }

    public void setProductoSolicitados(List<ProductoSolicitado> productoSolicitados) {
        this.productoSolicitados = productoSolicitados;
    }
}
