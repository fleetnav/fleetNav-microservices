package com.fleetNav.domain.repositories;

import com.fleetNav.domain.entities.NextMaintenance;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface NextMaintenanceRepository extends JpaRepository<NextMaintenance, UUID> {
}
