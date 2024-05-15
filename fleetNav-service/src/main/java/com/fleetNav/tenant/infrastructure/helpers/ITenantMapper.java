package com.fleetNav.tenant.infrastructure.helpers;

import com.fleetNav.tenant.api.dto.TenantDTO;
import com.fleetNav.tenant.domain.entities.Tenant;

public interface ITenantMapper {

    TenantDTO convertTenantToTenantResponse(Tenant tenant);

    Tenant covertTenantRequestToTenant(TenantDTO tenantDTO);
}
