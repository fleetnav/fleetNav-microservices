package com.fleetNavmultitenantservice.domain.repositories;

import com.fleetNavmultitenantservice.domain.entities.Tenant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TenantRepository extends JpaRepository<Tenant, String> {
}
