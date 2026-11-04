package com.tecsup.orden_service.dto;

import java.time.LocalDate;
import java.util.List;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;

public class OrdenProduccionDTO {
    @NotNull(message = "El producto es obligatorio")
    @Positive(message = "El idProducto debe ser positivo")
    private Integer idProducto;

    @NotNull(message = "La fecha es obligatoria")
    private LocalDate fecha;

    @NotNull(message = "La cantidad planificada es obligatoria")
    @Positive(message = "La cantidad planificada debe ser positiva")
    private Integer cantidadPlanificada;

    @NotBlank(message = "El estado es obligatorio")
    @Size(max = 30, message = "El estado no debe superar 30 caracteres")
    private String estado = "PENDIENTE";

    @NotEmpty(message = "La orden debe tener al menos un material")
    @Valid
    private List<DetalleMaterialDTO> detalles;

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

    public List<DetalleMaterialDTO> getDetalles() {
        return detalles;
    }

    public void setDetalles(List<DetalleMaterialDTO> detalles) {
        this.detalles = detalles;
    }

}