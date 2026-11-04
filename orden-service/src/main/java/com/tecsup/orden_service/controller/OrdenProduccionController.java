package com.tecsup.orden_service.controller;

import java.util.ArrayList;
import java.util.List;

import com.tecsup.orden_service.dto.DetalleMaterialDTO;
import com.tecsup.orden_service.dto.OrdenProduccionDTO;
import com.tecsup.orden_service.model.DetalleMaterial;
import com.tecsup.orden_service.model.OrdenProduccion;
import com.tecsup.orden_service.services.OrdenProduccionService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/ordenes")
public class OrdenProduccionController {
    private final OrdenProduccionService service;

    public OrdenProduccionController(OrdenProduccionService service) {
        this.service = service;
    }


    @GetMapping
    public ResponseEntity<List<OrdenProduccion>> listar() {
        return ResponseEntity.ok(service.listar());
    }


    @GetMapping("/{id}")
    public ResponseEntity<OrdenProduccion> obtener(@PathVariable Integer id) {
        return ResponseEntity.ok(service.obtener(id));
    }


    @PostMapping
    public ResponseEntity<OrdenProduccion> guardar(
            @Valid @RequestBody OrdenProduccionDTO dto
    ) {
        return ResponseEntity.status(201).body(service.guardar(convertir(dto)));
    }

    @PutMapping("/{id}")
    public ResponseEntity<OrdenProduccion> actualizar(
            @PathVariable Integer id,
            @Valid @RequestBody OrdenProduccionDTO dto
    ) {
        return ResponseEntity.ok(service.actualizar(id, convertir(dto)));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Integer id) {
        service.eliminar(id);
        return ResponseEntity.noContent().build();
    }

    private OrdenProduccion convertir(OrdenProduccionDTO dto) {
        OrdenProduccion orden = new OrdenProduccion();
        orden.setIdProducto(dto.getIdProducto());
        orden.setFecha(dto.getFecha());
        orden.setCantidadPlanificada(dto.getCantidadPlanificada());
        orden.setEstado(dto.getEstado());

        List<DetalleMaterial> detalles = new ArrayList<>();

        for (DetalleMaterialDTO dtoDetalle : dto.getDetalles()) {
            DetalleMaterial detalle = new DetalleMaterial();
            detalle.setIdMaterial(dtoDetalle.getIdMaterial());
            detalle.setCantidadRequerida(dtoDetalle.getCantidadRequerida());
            detalle.setOrden(orden);
            detalles.add(detalle);
        }

        orden.setDetalles(detalles);
        return orden;
    }
}