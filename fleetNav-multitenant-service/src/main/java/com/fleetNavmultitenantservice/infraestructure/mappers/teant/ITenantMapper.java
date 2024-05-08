package com.fleetNavmultitenantservice.infraestructure.mappers.teant;

import com.fleetNavmultitenantservice.api.dto.tenant.TenantDTO;
import com.fleetNavmultitenantservice.domain.entities.tenant.Tenant;

public interface ITenantMapper {
    TenantDTO convertTenantToTenantResponse(Tenant tenant);
    Tenant convertTenantRequestToTenant(TenantDTO tenantDTO);
}
