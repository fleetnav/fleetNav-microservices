package com.fleetNavmultitenantservice.domain.repositories.logic;

import com.fleetNavmultitenantservice.domain.entities.logic.Route;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface RouteRepository extends JpaRepository<Route, UUID> {
}
