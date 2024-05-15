package com.fleetNav.tenant.domain.repositories;

import com.fleetNav.tenant.domain.entities.Tenant;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TenantRepository extends JpaRepository<Tenant, String> {
}
