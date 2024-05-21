package com.fleetNav.tenant.infrastructure.abstract_services;

import com.fleetNav.tenant.api.dto.TenantDTO;

import java.util.List;

public interface ITenantService {

    TenantDTO findByTenantId(String tenantId);

    boolean setTenantInContext(String tenantId);

    List<TenantDTO> getAllTenants();


    TenantDTO createNewTenant(TenantDTO tenantDTO);
}
