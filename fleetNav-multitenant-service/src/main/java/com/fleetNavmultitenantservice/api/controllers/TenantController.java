package com.fleetNavmultitenantservice.api.controllers;

import com.fleetNavmultitenantservice.api.dto.DataSourceDTO;
import com.fleetNavmultitenantservice.api.dto.TenantDTO;
import com.fleetNavmultitenantservice.infraestructure.abstract_services.ITenantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/tenants")
public class TenantController {
    @Autowired
    private ITenantService tenantService;

    @PostMapping("/{tenantId}")
    public void createTenant(@PathVariable String tenantId) {
        DataSourceDTO dataSourceDTO = new DataSourceDTO(tenantId);
        TenantDTO tenantDTO = new TenantDTO(tenantId, dataSourceDTO);
        tenantService.createNewTenant(tenantDTO);
    }

    @GetMapping()
    public List<TenantDTO> getAllTenants() {
        return tenantService.getAllTenants();
    }
}
