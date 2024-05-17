package com.fleetNav.tenant.api.controllers;

import com.fleetNav.service.api.dto.error.ErrorsResponse;

import com.fleetNav.tenant.api.dto.DataSourceDTO;
import com.fleetNav.tenant.api.dto.TenantDTO;
import com.fleetNav.tenant.infrastructure.abstract_services.ITenantService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "Tenant", description = "Operations pertaining to tenant in Tenant Management System")
@RestController
@RequestMapping("/tenants")
public class TenantController {
    @Autowired
    private ITenantService tenantService;

    @Operation(summary = "Save a tenant", description = "Saves a new tenant in the database.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Tenant successfully saved", content = {
                    @Content(schema = @Schema(implementation = TenantDTO.class), mediaType = "application/json"),
            }),
            @ApiResponse(responseCode = "400", description = "Error : Invalid Request", content = {
                    @Content(schema = @Schema(implementation = ErrorsResponse.class)) }),
            @ApiResponse(responseCode = "404", description = "Error : Id not found", content = {
                    @Content(schema = @Schema(implementation = ErrorsResponse.class)) }),
            @ApiResponse(responseCode = "500", description = "Error : Internal server error", content = {
                    @Content(schema = @Schema(implementation = ErrorsResponse.class)) }),
    })
    @PostMapping("/{tenantId}")
    public void createTenant(@Parameter(description = "Id for post tenant") @PathVariable String tenantId) {
        DataSourceDTO dataSourceDTO = new DataSourceDTO(tenantId);
        TenantDTO tenantDTO = new TenantDTO(tenantId, dataSourceDTO);
        tenantService.createNewTenant(tenantDTO);
    }

    @Operation(summary = "Get Tenant", description = "in this endpoint going to get tenant")

    @GetMapping
    public List<TenantDTO> getAllTenants() {
        return tenantService.getAllTenants();
    }
}
