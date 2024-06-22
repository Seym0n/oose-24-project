package com.sse.ooseproject;

import com.sse.ooseproject.models.Building;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BuildingRepository extends JpaRepository<Building, Long> {
}
