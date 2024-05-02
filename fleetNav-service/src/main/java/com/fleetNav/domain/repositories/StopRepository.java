package com.fleetNav.domain.repositories;

import com.fleetNav.domain.entities.Stop;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface StopRepository extends CrudRepository<Stop, UUID> {
}
