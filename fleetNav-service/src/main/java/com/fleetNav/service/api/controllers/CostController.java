package com.fleetNav.service.api.controllers;

import com.fleetNav.service.api.dto.error.ErrorsResponse;
import com.fleetNav.service.api.dto.request.CostRequest;
import com.fleetNav.service.api.dto.response.CostResponse;
import com.fleetNav.service.infraestructure.abstract_services.ICostService;

import com.fleetNav.tenant.infrastructure.services.TenantService;
import lombok.AllArgsConstructor;

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

@Tag(name = "Cost", description = "Our application's route cost controller provides RESTful endpoints to calculate and manage the costs associated with a specific rout")
@RestController
@RequestMapping("/{tenant}/costs")
@AllArgsConstructor
public class CostController {
    @Autowired
    private final ICostService costService;

    @Autowired
    private TenantService tenantService;

    @Operation(summary = "Update a cost", description = "updates an existing cost in the database")
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "Cost successfully update", content = {
                    @Content(schema = @Schema(implementation = CostResponse.class), mediaType = "application/json")}),
            @ApiResponse(responseCode = "400", description = "Error : Invalid Request", content = {
                    @Content(schema = @Schema(implementation = ErrorsResponse.class)) }),
            @ApiResponse(responseCode = "404", description = "Error : Id not found", content = {
                    @Content(schema = @Schema(implementation = ErrorsResponse.class)) }),
            @ApiResponse(responseCode = "500", description = "Error : Internal server error", content = {
                    @Content(schema = @Schema(implementation = ErrorsResponse.class)) }),
        
        })
    @PutMapping("/{id}")
    public ResponseEntity<CostResponse> updateCost(
            @Parameter(description = "Id of the Cost to be update") @PathVariable UUID id,
            @Validated
            @RequestBody CostRequest costRequest,
            @PathVariable String tenant) {
        tenantService.setTenantInContext(tenant);
        try {
            return ResponseEntity.ok(costService.update(id, costRequest));
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @Operation(summary = "Get all Cost", description = "Retrieves a list of all tutorials with pagination support.")
    @GetMapping
    public ResponseEntity<Page<CostResponse>> getAllCosts(@Parameter(description = "Page number ") @RequestParam(defaultValue = "0") int page,
                                                          @Parameter(description = "Number of items per page") @RequestParam(defaultValue = "5") int size,
                                                          @PathVariable String tenant) {
        tenantService.setTenantInContext(tenant);
        try {
            Pageable pageable = PageRequest.of(page, size);
            if (page != 0) pageable = PageRequest.of(page - 1, size);
            return ResponseEntity.ok(costService.getAll(pageable));
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

    }

    @Operation(summary = "Get Cost by Id", description = "Retrieves a Cost object by specifying its id.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Cost found", content = @Content(schema = @Schema(implementation = CostResponse.class), mediaType = "application/json")),
            @ApiResponse(responseCode = "400", description = "Error : Invalid Request", content = {
                    @Content(schema = @Schema(implementation = ErrorsResponse.class)) }),
            @ApiResponse(responseCode = "404", description = "Error : Id not found", content = {
                    @Content(schema = @Schema(implementation = ErrorsResponse.class)) }),
            @ApiResponse(responseCode = "500", description = "Error : Internal server error", content = {
                    @Content(schema = @Schema(implementation = ErrorsResponse.class)) }),
    })
    @GetMapping("/{id}")
    public ResponseEntity<Optional<CostResponse>> getCost(
            @Parameter(description = "id of the Cost to be get")
            @PathVariable UUID id,
            @PathVariable String tenant) {
        tenantService.setTenantInContext(tenant);
        try {
            return ResponseEntity.ok(costService.getById(id));
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

    }
}
