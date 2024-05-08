package com.fleetNavmultitenantservice.domain.repositories.logic;

import com.fleetNavmultitenantservice.domain.entities.logic.Stop;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface StopRepository extends JpaRepository<Stop, UUID> {
}
