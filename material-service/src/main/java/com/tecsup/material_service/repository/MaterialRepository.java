package com.tecsup.material_service.repository;

import com.tecsup.material_service.model.Material;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MaterialRepository extends JpaRepository<Material, Integer> {
}