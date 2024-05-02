package com.fleetNav.domain.repositories;

import com.fleetNav.domain.entities.Vehicle;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface VehicleRepository extends CrudRepository<Vehicle, UUID> {
}
