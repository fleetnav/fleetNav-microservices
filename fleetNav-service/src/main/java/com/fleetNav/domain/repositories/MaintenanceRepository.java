package com.fleetNav.domain.repositories;

import com.fleetNav.domain.entities.Maintenance;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface MaintenanceRepository extends CrudRepository<Maintenance, UUID> {
}
