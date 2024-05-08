package com.fleetNavmultitenantservice.domain.repositories.logic;


import com.fleetNavmultitenantservice.domain.entities.logic.VehicleStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface VehicleStatusRepository extends JpaRepository<VehicleStatus, UUID> {
}
