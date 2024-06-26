package com.fleetNav.service.api.controllers;

import com.fleetNav.service.api.dto.error.ErrorsResponse;
import com.fleetNav.service.api.dto.request.VehicleStatusRequest;
import com.fleetNav.service.api.dto.response.VehicleStatusResponse;

import com.fleetNav.service.infraestructure.abstract_services.IVehicleStatusService;

import com.fleetNav.tenant.infrastructure.services.TenantService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

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

@Tag(name = "Vehicle Status", description = "Our application's vehicle status controller provides RESTful endpoints to obtain real-time information about the operational status of vehicles in a fleet.")
@RestController
@RequestMapping("/{tenant}/vehicleStatus")
public class VehicleStatusController {
    @Autowired
    private IVehicleStatusService vehicleStatusService;
    @Autowired
    private TenantService tenantService;

    @Operation(summary = "Update a vehicle status", description = "updates an existing vehicle status in the database")
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "Vehicle Status successfully update", content = {
                    @Content(schema = @Schema(implementation = VehicleStatusResponse.class), mediaType = "application/json")}),
           @ApiResponse(responseCode = "400", description = "Error : Invalid Request", content = {
                    @Content(schema = @Schema(implementation = ErrorsResponse.class)) }),
            @ApiResponse(responseCode = "404", description = "Error : Id not found", content = {
                    @Content(schema = @Schema(implementation = ErrorsResponse.class)) }),
            @ApiResponse(responseCode = "500", description = "Error : Internal server error", content = {
                    @Content(schema = @Schema(implementation = ErrorsResponse.class)) }),})
    @PutMapping("/{id}")
    public ResponseEntity<VehicleStatusResponse> updateVehicleStatus(
            @Parameter(description = "Id of the Vehicle Status to be update") @PathVariable UUID id,
            @Validated
            @RequestBody VehicleStatusRequest vehicleStatusRequest,
            @PathVariable String tenant) {
        tenantService.setTenantInContext(tenant);
        try {
            return ResponseEntity.ok(vehicleStatusService.update(id, vehicleStatusRequest));
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

    }

    @Operation(summary = "Get all Status of Vehicles", description = "Retrieves a list of all tutorials with pagination support.")
    @GetMapping
    public ResponseEntity<Page<VehicleStatusResponse>> getAllVehicleStatus(
            @Parameter(description = "Page number ") @RequestParam(defaultValue = "0") int page,
            @Parameter(description = "Number of items per page") @RequestParam(defaultValue = "5") int size,
            @PathVariable String tenant) {
        tenantService.setTenantInContext(tenant);
        try {
            Pageable pageable = PageRequest.of(page, size);
            if (page != 0)
                pageable = PageRequest.of(page - 1, size);
            return ResponseEntity.ok(vehicleStatusService.getAll(pageable));
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

    }

    @Operation(summary = "Get Vehicle Status by Id", description = "Retrieves a Vehicle Status object by specifying its id.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Vehicle Status found", content = @Content(schema = @Schema(implementation = VehicleStatusResponse.class), mediaType = "application/json")),
            @ApiResponse(responseCode = "400", description = "Error : Invalid Request", content = {
                    @Content(schema = @Schema(implementation = ErrorsResponse.class)) }),
            @ApiResponse(responseCode = "404", description = "Error : Id not found", content = {
                    @Content(schema = @Schema(implementation = ErrorsResponse.class)) }),
            @ApiResponse(responseCode = "500", description = "Error : Internal server error", content = {
                    @Content(schema = @Schema(implementation = ErrorsResponse.class)) }),
    })
    @GetMapping("/{id}")
    public ResponseEntity<Optional<VehicleStatusResponse>> getVehicleStatus(
            @Parameter(description = "id of the Vehicle Status to be get") @PathVariable UUID id,
            @PathVariable String tenant) {
        tenantService.setTenantInContext(tenant);
        try {
            return ResponseEntity.ok(vehicleStatusService.getById(id));
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

    }
}
