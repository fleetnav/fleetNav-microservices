package com.fleetNavmultitenantservice.infraestructure.helpers;

import com.fleetNavmultitenantservice.api.dto.TenantDTO;
import com.fleetNavmultitenantservice.domain.entities.Tenant;

public interface ITenantMapper {
    TenantDTO convertTenantToTenantResponse(Tenant tenant);
    Tenant convertTenantRequestToTenant(TenantDTO tenantDTO);
}
