package com.fleetNav.domain.repositories;

import com.fleetNav.domain.entities.Cost;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface CostRepository extends CrudRepository<Cost, UUID> {
}
