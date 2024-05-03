package com.fleetNav.domain.repositories;

import com.fleetNav.domain.entities.Vehicle;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface VehicleStatusRepository extends JpaRepository<Vehicle, UUID> {
}
