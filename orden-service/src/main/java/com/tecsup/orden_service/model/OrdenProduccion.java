package com.tecsup.orden_service.model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;

@Entity
@Table(name = "orden_produccion")
public class OrdenProduccion {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idOrden;

    @Column(name = "id_producto", nullable = false)
    private Integer idProducto;

    @Column(nullable = false)
    private LocalDate fecha;

    @Column(nullable = false)
    private Integer cantidadPlanificada;

    @Column(nullable = false, length = 30)
    private String estado = "PENDIENTE";

    @OneToMany(mappedBy = "orden", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference
    private List<DetalleMaterial> detalles = new ArrayList<>();

    public OrdenProduccion() {
    }

    public Integer getIdOrden() {
        return idOrden;
    }

    public void setIdOrden(Integer idOrden) {
        this.idOrden = idOrden;
    }

    public Integer getIdProducto() {
        return idProducto;
    }

    public void setIdProducto(Integer idProducto) {
        this.idProducto = idProducto;
    }

    public LocalDate getFecha() {
        return fecha;
    }

    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }

    public Integer getCantidadPlanificada() {
        return cantidadPlanificada;
    }

    public void setCantidadPlanificada(Integer cantidadPlanificada) {
        this.cantidadPlanificada = cantidadPlanificada;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public List<DetalleMaterial> getDetalles() {
        return detalles;
    }

    public void setDetalles(List<DetalleMaterial> detalles) {
        this.detalles = detalles;
    }

}