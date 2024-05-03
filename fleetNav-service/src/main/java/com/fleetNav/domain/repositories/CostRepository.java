package com.fleetNav.domain.repositories;

import com.fleetNav.domain.entities.Cost;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface CostRepository extends JpaRepository<Cost, UUID> {
}
