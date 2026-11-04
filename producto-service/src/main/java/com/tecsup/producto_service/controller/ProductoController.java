package com.tecsup.producto_service.controller;

import java.util.List;

import com.tecsup.producto_service.dto.ProductoDTO;
import com.tecsup.producto_service.model.Producto;
import com.tecsup.producto_service.services.ProductoService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/productos")
public class ProductoController {
    private final ProductoService service;

    public ProductoController(ProductoService service) {
        this.service = service;
    }


    @GetMapping
    public ResponseEntity<List<Producto>> listar() {
        return ResponseEntity.ok(service.listar());
    }


    @GetMapping("/{id}")
    public ResponseEntity<Producto> obtener(@PathVariable Integer id) {
        return ResponseEntity.ok(service.obtener(id));
    }


    @PostMapping
    public ResponseEntity<Producto> guardar(@Valid @RequestBody ProductoDTO dto) {
        return ResponseEntity.status(201).body(service.guardar(convertir(dto)));
    }


    @PutMapping("/{id}")
    public ResponseEntity<Producto> actualizar(
            @PathVariable Integer id,
            @Valid @RequestBody ProductoDTO dto
    ) {
        return ResponseEntity.ok(service.actualizar(id, convertir(dto)));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Integer id) {
        service.eliminar(id);
        return ResponseEntity.noContent().build();
    }

    private Producto convertir(ProductoDTO dto) {
        Producto producto = new Producto();
        producto.setNombre(dto.getNombre());
        producto.setDescripcion(dto.getDescripcion());
        producto.setUnidadMedida(dto.getUnidadMedida());
        producto.setEstado(dto.getEstado());
        return producto;
    }
}