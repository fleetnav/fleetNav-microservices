package com.fleetNav.domain.repositories;

import com.fleetNav.domain.entities.Trip;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface TripRepository extends CrudRepository<Trip, UUID> {
}
