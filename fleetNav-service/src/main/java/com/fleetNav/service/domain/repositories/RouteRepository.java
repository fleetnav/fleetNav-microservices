package com.fleetNav.service.domain.repositories;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.fleetNav.service.domain.entities.Route;

@Repository
public interface RouteRepository extends JpaRepository<Route, UUID> {
}
