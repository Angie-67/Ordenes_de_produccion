package com.tecsup.orden_service.repository;

import com.tecsup.orden_service.model.OrdenProduccion;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrdenProduccionRepository extends JpaRepository<OrdenProduccion, Integer> {
}