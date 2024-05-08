package com.fleetNavmultitenantservice.domain.repositories.logic;

import com.fleetNavmultitenantservice.domain.entities.logic.Vehicle;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface VehicleRepository extends JpaRepository<Vehicle, UUID> {
}
