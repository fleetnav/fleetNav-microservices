package com.fleetNav.service.domain.repositories;

import java.util.UUID;

import com.fleetNav.service.domain.entities.Maintenance;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MaintenanceRepository extends JpaRepository<Maintenance, UUID> {
}
