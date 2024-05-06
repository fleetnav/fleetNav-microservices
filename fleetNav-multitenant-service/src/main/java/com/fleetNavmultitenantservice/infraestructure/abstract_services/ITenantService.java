package com.fleetNavmultitenantservice.infraestructure.abstract_services;

import com.fleetNavmultitenantservice.api.dto.TenantDTO;

import java.util.List;

public interface ITenantService {
    TenantDTO findByTenantId(String tenantId);

    boolean setTenantInContext(String tenantId);

    List<TenantDTO> getAllTenants();

    TenantDTO createNewTenant(TenantDTO tenantDTO);
}
