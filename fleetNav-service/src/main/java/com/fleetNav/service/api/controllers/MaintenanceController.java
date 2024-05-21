package com.fleetNav.service.api.controllers;

import com.fleetNav.service.api.dto.error.ErrorsResponse;
import com.fleetNav.service.api.dto.request.MaintenanceRequest;
import com.fleetNav.service.api.dto.response.MaintenanceResponse;

import com.fleetNav.service.infraestructure.abstract_services.IMaintenanceService;

import com.fleetNav.tenant.infrastructure.services.TenantService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;
import java.util.UUID;

@Tag(name = "Maintenance", description = "Our application's vehicle maintenance controller provides RESTful endpoints to manage vehicle fleet maintenance.")
@RestController
@RequestMapping("/{tenant}/maintenances")
@AllArgsConstructor
public class MaintenanceController {
    @Autowired
    private final IMaintenanceService maintenanceService;

    @Autowired
    private TenantService tenantService;

    @Operation(summary = "Save a maintenance", description = "Saves a new maintenance in the database.")
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "Maintenance successfully saved", content = {
                    @Content(schema = @Schema(implementation = MaintenanceResponse.class), mediaType = "application/json") }),
            @ApiResponse(responseCode = "400", description = "Error : Invalid Request", content = {
                    @Content(schema = @Schema(implementation = ErrorsResponse.class)) }),

            @ApiResponse(responseCode = "500", description = "Error : Internal server error", content = {
                    @Content(schema = @Schema(implementation = ErrorsResponse.class)) }),
    })
    @PostMapping
    public ResponseEntity<MaintenanceResponse> saveMaintenance(
            @Validated @RequestBody MaintenanceRequest maintenanceRequest,
            @PathVariable String tenant) {
        tenantService.setTenantInContext(tenant);
        try {
            return ResponseEntity.ok(maintenanceService.create(maintenanceRequest));
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

    }

    @Operation(summary = "Update a maintenance", description = "updates an existing maintenance in the database")
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "Maintenance successfully update", content = {
                    @Content(schema = @Schema(implementation = MaintenanceResponse.class), mediaType = "application/json") }),
            @ApiResponse(responseCode = "400", description = "Error : Invalid Request", content = {
                    @Content(schema = @Schema(implementation = ErrorsResponse.class)) }),
            @ApiResponse(responseCode = "404", description = "Error : Id not found", content = {
                    @Content(schema = @Schema(implementation = ErrorsResponse.class)) }),
            @ApiResponse(responseCode = "500", description = "Error : Internal server error", content = {
                    @Content(schema = @Schema(implementation = ErrorsResponse.class)) }), })
    @PutMapping("/{id}")
    public ResponseEntity<MaintenanceResponse> updateMaintenance(
            @Parameter(description = "Id of the Maintenance to be update") @PathVariable UUID id,
            @Validated @RequestBody MaintenanceRequest maintenanceRequest,
            @PathVariable String tenant) {
        tenantService.setTenantInContext(tenant);
        try {
            return ResponseEntity.ok(maintenanceService.update(id, maintenanceRequest));
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

    }

    @Operation(summary = "Get all Maintenances", description = "Retrieves a list of all tutorials with pagination support.")
    @GetMapping
    public ResponseEntity<Page<MaintenanceResponse>> getAllMaintenances(
            @Parameter(description = "Page number ") @RequestParam(defaultValue = "0") int page,
            @Parameter(description = "Number of items per page") @RequestParam(defaultValue = "5") int size,
            @PathVariable String tenant) {
        tenantService.setTenantInContext(tenant);
        try {
            Pageable pageable = PageRequest.of(page, size);
            if (page != 0)
                pageable = PageRequest.of(page - 1, size);
            return ResponseEntity.ok(maintenanceService.getAll(pageable));
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @Operation(summary = "Get Maintenance by Id", description = "Retrieves a Maintenance object by specifying its id.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Maintenance found", content = @Content(schema = @Schema(implementation = MaintenanceResponse.class), mediaType = "application/json")),
            @ApiResponse(responseCode = "400", description = "Error : Invalid Request", content = {
                    @Content(schema = @Schema(implementation = ErrorsResponse.class)) }),
            @ApiResponse(responseCode = "404", description = "Error : Id not found", content = {
                    @Content(schema = @Schema(implementation = ErrorsResponse.class)) }),
            @ApiResponse(responseCode = "500", description = "Error : Internal server error", content = {
                    @Content(schema = @Schema(implementation = ErrorsResponse.class)) }),
    })
    @GetMapping("/{id}")
    public ResponseEntity<Optional<MaintenanceResponse>> getMaintenance(
            @Parameter(description = "id of the Maintenance to be get") @PathVariable UUID id,
            @PathVariable String tenant) {
        tenantService.setTenantInContext(tenant);
        try {
            return ResponseEntity.ok(maintenanceService.getById(id));
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

    }
}
