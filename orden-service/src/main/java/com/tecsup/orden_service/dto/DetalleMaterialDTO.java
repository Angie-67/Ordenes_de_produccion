package com.tecsup.orden_service.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public class DetalleMaterialDTO {
    @NotNull(message = "El material es obligatorio")
    @Positive(message = "El idMaterial debe ser positivo")
    private Integer idMaterial;

    @NotNull(message = "La cantidad requerida es obligatoria")
    @Positive(message = "La cantidad requerida debe ser positiva")
    private Integer cantidadRequerida;

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

}