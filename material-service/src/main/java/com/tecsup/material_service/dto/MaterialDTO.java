package com.tecsup.material_service.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
import jakarta.validation.constraints.Size;

public class MaterialDTO {
    @NotBlank(message = "El nombre es obligatorio")
    @Size(max = 100, message = "El nombre no debe superar 100 caracteres")
    private String nombre;

    @Size(max = 250, message = "La descripción no debe superar 250 caracteres")
    private String descripcion;

    @NotBlank(message = "La unidad de medida es obligatoria")
    @Size(max = 30, message = "La unidad de medida no debe superar 30 caracteres")
    private String unidadMedida;

    @NotNull(message = "La cantidad disponible es obligatoria")
    @PositiveOrZero(message = "La cantidad disponible no puede ser negativa")
    private Integer cantidadDisponible = 0;

    private String estado = "A";

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getUnidadMedida() {
        return unidadMedida;
    }

    public void setUnidadMedida(String unidadMedida) {
        this.unidadMedida = unidadMedida;
    }

    public Integer getCantidadDisponible() {
        return cantidadDisponible;
    }

    public void setCantidadDisponible(Integer cantidadDisponible) {
        this.cantidadDisponible = cantidadDisponible;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

}