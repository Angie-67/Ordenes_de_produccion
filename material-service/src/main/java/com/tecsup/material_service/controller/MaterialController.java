package com.tecsup.material_service.controller;

import java.util.List;

import com.tecsup.material_service.dto.MaterialDTO;
import com.tecsup.material_service.model.Material;
import com.tecsup.material_service.services.MaterialService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/materiales")
public class MaterialController {
    private final MaterialService service;

    public MaterialController(MaterialService service) {
        this.service = service;
    }


    @GetMapping
    public ResponseEntity<List<Material>> listar() {
        return ResponseEntity.ok(service.listar());
    }


    @GetMapping("/{id}")
    public ResponseEntity<Material> obtener(@PathVariable Integer id) {
        return ResponseEntity.ok(service.obtener(id));
    }


    @PostMapping
    public ResponseEntity<Material> guardar(@Valid @RequestBody MaterialDTO dto) {
        return ResponseEntity.status(201).body(service.guardar(convertir(dto)));
    }


    @PutMapping("/{id}")
    public ResponseEntity<Material> actualizar(
            @PathVariable Integer id,
            @Valid @RequestBody MaterialDTO dto
    ) {
        return ResponseEntity.ok(service.actualizar(id, convertir(dto)));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Integer id) {
        service.eliminar(id);
        return ResponseEntity.noContent().build();
    }

    private Material convertir(MaterialDTO dto) {
        Material material = new Material();
        material.setNombre(dto.getNombre());
        material.setDescripcion(dto.getDescripcion());
        material.setUnidadMedida(dto.getUnidadMedida());
        material.setCantidadDisponible(dto.getCantidadDisponible());
        material.setEstado(dto.getEstado());
        return material;
    }
}