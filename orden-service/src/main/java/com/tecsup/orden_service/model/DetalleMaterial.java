package com.tecsup.orden_service.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;

@Entity
@Table(name = "detalle_material")
public class DetalleMaterial {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idDetalleMaterial;

    @Column(name = "id_material", nullable = false)
    private Integer idMaterial;

    @Column(nullable = false)
    private Integer cantidadRequerida;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_orden", nullable = false)
    @JsonBackReference
    private OrdenProduccion orden;

    public DetalleMaterial() {
    }

    public Integer getIdDetalleMaterial() {
        return idDetalleMaterial;
    }

    public void setIdDetalleMaterial(Integer idDetalleMaterial) {
        this.idDetalleMaterial = idDetalleMaterial;
    }

    public Integer getIdMaterial() {
        return idMaterial;
    }

    public void setIdMaterial(Integer idMaterial) {
        this.idMaterial = idMaterial;
    }

    public Integer getCantidadRequerida() {
        return cantidadRequerida;
    }

    public void setCantidadRequerida(Integer cantidadRequerida) {
        this.cantidadRequerida = cantidadRequerida;
    }

    public OrdenProduccion getOrden() {
        return orden;
    }

    public void setOrden(OrdenProduccion orden) {
        this.orden = orden;
    }

}