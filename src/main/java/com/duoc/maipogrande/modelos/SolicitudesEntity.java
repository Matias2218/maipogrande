package com.duoc.maipogrande.modelos;

import javax.persistence.*;
import java.sql.Time;
import java.util.Collection;
import java.util.Objects;

@Entity
@Table(name = "SOLICITUDES", schema = "ROOT")
public class SolicitudesEntity {
    private long idSol;
    private Time fechaEmision;
    private Time fechaLimite;
    private String estado;
    private String paisDestino;
    private long clientesIdCli;
    private long administradoresIdAdm;
    private Collection<ProductosSolicitadosEntity> productosSolicitadosByIdSol;
    private ClientesEntity clientesByClientesIdCli;
    private AdministradoresEntity administradoresByAdministradoresIdAdm;
    private Collection<VentasEntity> ventasByIdSol;

    @Id
    @Column(name = "ID_SOL")
    public long getIdSol() {
        return idSol;
    }

    public void setIdSol(long idSol) {
        this.idSol = idSol;
    }

    @Basic
    @Column(name = "FECHA_EMISION")
    public Time getFechaEmision() {
        return fechaEmision;
    }

    public void setFechaEmision(Time fechaEmision) {
        this.fechaEmision = fechaEmision;
    }

    @Basic
    @Column(name = "FECHA_LIMITE")
    public Time getFechaLimite() {
        return fechaLimite;
    }

    public void setFechaLimite(Time fechaLimite) {
        this.fechaLimite = fechaLimite;
    }

    @Basic
    @Column(name = "ESTADO")
    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    @Basic
    @Column(name = "PAIS_DESTINO")
    public String getPaisDestino() {
        return paisDestino;
    }

    public void setPaisDestino(String paisDestino) {
        this.paisDestino = paisDestino;
    }

    @Basic
    @Column(name = "CLIENTES_ID_CLI")
    public long getClientesIdCli() {
        return clientesIdCli;
    }

    public void setClientesIdCli(long clientesIdCli) {
        this.clientesIdCli = clientesIdCli;
    }

    @Basic
    @Column(name = "ADMINISTRADORES_ID_ADM")
    public long getAdministradoresIdAdm() {
        return administradoresIdAdm;
    }

    public void setAdministradoresIdAdm(long administradoresIdAdm) {
        this.administradoresIdAdm = administradoresIdAdm;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SolicitudesEntity that = (SolicitudesEntity) o;
        return idSol == that.idSol &&
                clientesIdCli == that.clientesIdCli &&
                administradoresIdAdm == that.administradoresIdAdm &&
                Objects.equals(fechaEmision, that.fechaEmision) &&
                Objects.equals(fechaLimite, that.fechaLimite) &&
                Objects.equals(estado, that.estado) &&
                Objects.equals(paisDestino, that.paisDestino);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idSol, fechaEmision, fechaLimite, estado, paisDestino, clientesIdCli, administradoresIdAdm);
    }

    @OneToMany(mappedBy = "solicitudesBySolicitudesIdSol")
    public Collection<ProductosSolicitadosEntity> getProductosSolicitadosByIdSol() {
        return productosSolicitadosByIdSol;
    }

    public void setProductosSolicitadosByIdSol(Collection<ProductosSolicitadosEntity> productosSolicitadosByIdSol) {
        this.productosSolicitadosByIdSol = productosSolicitadosByIdSol;
    }

    @ManyToOne
    @JoinColumn(name = "CLIENTES_ID_CLI", referencedColumnName = "ID_CLI", nullable = false)
    public ClientesEntity getClientesByClientesIdCli() {
        return clientesByClientesIdCli;
    }

    public void setClientesByClientesIdCli(ClientesEntity clientesByClientesIdCli) {
        this.clientesByClientesIdCli = clientesByClientesIdCli;
    }

    @ManyToOne
    @JoinColumn(name = "ADMINISTRADORES_ID_ADM", referencedColumnName = "ID_ADM", nullable = false)
    public AdministradoresEntity getAdministradoresByAdministradoresIdAdm() {
        return administradoresByAdministradoresIdAdm;
    }

    public void setAdministradoresByAdministradoresIdAdm(AdministradoresEntity administradoresByAdministradoresIdAdm) {
        this.administradoresByAdministradoresIdAdm = administradoresByAdministradoresIdAdm;
    }

    @OneToMany(mappedBy = "solicitudesBySolicitudesIdSol")
    public Collection<VentasEntity> getVentasByIdSol() {
        return ventasByIdSol;
    }

    public void setVentasByIdSol(Collection<VentasEntity> ventasByIdSol) {
        this.ventasByIdSol = ventasByIdSol;
    }
}
