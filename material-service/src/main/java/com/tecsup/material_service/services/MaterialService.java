package com.tecsup.material_service.services;

import java.util.List;

import com.tecsup.material_service.exception.ResourceNotFoundException;
import com.tecsup.material_service.model.Material;
import com.tecsup.material_service.repository.MaterialRepository;
import org.springframework.stereotype.Service;

@Service
public class MaterialService {
    private final MaterialRepository repo;

    public MaterialService(MaterialRepository repo) {
        this.repo = repo;
    }


    public List<Material> listar() {
        return repo.findAll();
    }

    public Material guardar(Material material) {
        return repo.save(material);
    }


    public Material obtener(Integer id) {
        return repo.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Material no encontrado con id: " + id)
        );
    }


    public Material actualizar(Integer id, Material datos) {
        Material material = obtener(id);
        material.setNombre(datos.getNombre());
        material.setDescripcion(datos.getDescripcion());
        material.setUnidadMedida(datos.getUnidadMedida());
        material.setCantidadDisponible(datos.getCantidadDisponible());
        material.setEstado(datos.getEstado());
        return repo.save(material);
    }

    public void eliminar(Integer id) {
        repo.delete(obtener(id));
    }
}