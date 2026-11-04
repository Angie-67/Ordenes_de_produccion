package com.tecsup.producto_service.services;

import java.util.List;

import com.tecsup.producto_service.exception.ResourceNotFoundException;
import com.tecsup.producto_service.model.Producto;
import com.tecsup.producto_service.repository.ProductoRepository;
import org.springframework.stereotype.Service;

@Service
public class ProductoService {
    private final ProductoRepository repo;

    public ProductoService(ProductoRepository repo) {
        this.repo = repo;
    }


    public List<Producto> listar() {
        return repo.findAll();
    }

    public Producto guardar(Producto producto) {
        return repo.save(producto);
    }


    public Producto obtener(Integer id) {
        return repo.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Producto no encontrado con id: " + id)
        );
    }


    public Producto actualizar(Integer id, Producto datos) {
        Producto producto = obtener(id);
        producto.setNombre(datos.getNombre());
        producto.setDescripcion(datos.getDescripcion());
        producto.setUnidadMedida(datos.getUnidadMedida());
        producto.setEstado(datos.getEstado());
        return repo.save(producto);
    }

    public void eliminar(Integer id) {
        repo.delete(obtener(id));
    }
}