package com.fleetNav.domain.repositories;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.fleetNav.domain.entities.Maintenance;

@Repository
public interface MaintenanceRepository extends JpaRepository<Maintenance, UUID> {
}
