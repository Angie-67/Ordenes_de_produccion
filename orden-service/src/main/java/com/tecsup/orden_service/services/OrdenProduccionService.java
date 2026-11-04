package com.tecsup.orden_service.services;

import java.util.List;

import com.tecsup.orden_service.exception.ResourceNotFoundException;
import com.tecsup.orden_service.model.DetalleMaterial;
import com.tecsup.orden_service.model.OrdenProduccion;
import com.tecsup.orden_service.repository.OrdenProduccionRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class OrdenProduccionService {
    private final OrdenProduccionRepository repo;

    public OrdenProduccionService(OrdenProduccionRepository repo) {
        this.repo = repo;
    }


    public List<OrdenProduccion> listar() {
        return repo.findAll();
    }


    public OrdenProduccion guardar(OrdenProduccion orden) {
        enlazarDetalles(orden);
        return repo.save(orden);
    }

    public OrdenProduccion obtener(Integer id) {
        return repo.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Orden no encontrada con id: " + id)
        );
    }


    @Transactional
    public OrdenProduccion actualizar(Integer id, OrdenProduccion datos) {
        OrdenProduccion orden = obtener(id);

        orden.setIdProducto(datos.getIdProducto());
        orden.setFecha(datos.getFecha());
        orden.setCantidadPlanificada(datos.getCantidadPlanificada());
        orden.setEstado(datos.getEstado());

        orden.getDetalles().clear();

        for (DetalleMaterial detalle : datos.getDetalles()) {
            detalle.setOrden(orden);
            orden.getDetalles().add(detalle);
        }

        return repo.save(orden);
    }

    public void eliminar(Integer id) {
        repo.delete(obtener(id));
    }

    private void enlazarDetalles(OrdenProduccion orden) {
        for (DetalleMaterial detalle : orden.getDetalles()) {
            detalle.setOrden(orden);
        }
    }
}