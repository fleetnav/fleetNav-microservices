package com.fleetNav.domain.repositories;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.fleetNav.domain.entities.Trip;

@Repository
public interface TripRepository extends JpaRepository<Trip, UUID> {
}