package com.fleetNav.domain.repositories;

import com.fleetNav.domain.entities.Route;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface RouteRepository extends CrudRepository<Route, UUID> {
}
