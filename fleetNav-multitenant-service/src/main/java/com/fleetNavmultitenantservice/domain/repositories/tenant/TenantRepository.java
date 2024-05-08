package com.fleetNavmultitenantservice.domain.repositories.tenant;

import com.fleetNavmultitenantservice.domain.entities.tenant.Tenant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TenantRepository extends JpaRepository<Tenant, String> {
}
