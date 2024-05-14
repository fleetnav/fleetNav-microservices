package com.fleetNav.service.api.controllers;

import com.fleetNav.service.api.dto.request.NextMaintenanceRequest;
import com.fleetNav.service.api.dto.response.NextMaintenanceResponse;

import com.fleetNav.tenant.infrastructure.services.TenantService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

import com.fleetNav.service.infraestructure.abstract_services.INextMaintenanceService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;
import java.util.UUID;

@Tag(name = "Next Maintenances", description = "Our application's upcoming maintenance controller provides RESTful endpoints to manage and schedule upcoming vehicle maintenance in a fleet.")
@RestController
@RequestMapping("/{tenant}/nextMaintenances")
@AllArgsConstructor
public class NextMaintenanceController {
    @Autowired
    private final INextMaintenanceService nextMaintenanceService;
    @Autowired
    private TenantService tenantService;
    // We don't need http request, because this action is called for vehicle
    /*@PostMapping
    public ResponseEntity<NextMaintenanceResponse> saveNextMaintenance(@RequestBody NextMaintenanceRequest nextMaintenanceRequest) {
        return ResponseEntity.ok(nextMaintenanceService.create(nextMaintenanceRequest));
    }*/

    // --------------------------------------------//
    // *******UPDATE*******//

    @Operation(summary = "Update a next maintenance", description = "updates an existing next maintenance in the database")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Next Maintenance successfully update", content = {
                    @Content(schema = @Schema(implementation = NextMaintenanceResponse.class), mediaType = "application/json")}),
            @ApiResponse(responseCode = "404", description = "Peticion no encontrada", content = {
                    @Content(schema = @Schema())}),
            @ApiResponse(responseCode = "500", content = {@Content(schema = @Schema())})})
    @PutMapping("/{id}")
    public ResponseEntity<NextMaintenanceResponse> updateNextMaintenance(@Parameter(description = "Id of the Next Maintenance to be update")
                                                                         @PathVariable UUID id,
                                                                         @RequestBody NextMaintenanceRequest nextMaintenanceRequest,
                                                                         @PathVariable String tenant
    ) {
        tenantService.setTenantInContext(tenant);
        try {
            return ResponseEntity.ok(nextMaintenanceService.update(id, nextMaintenanceRequest));
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    // Just exist one way to delete the next maintenance, and this is deleting the vehicle
    /*@DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteNextMaintenance(@PathVariable UUID id) {
        nextMaintenanceService.delete(id);
        return ResponseEntity.noContent().build();
    }*/
    // --------------------------------------------//
    // *******GET-ALL*******//
    @Operation(summary = "Get all next maintenances", description = "Retrieves a list of all tutorials with pagination support.")
    @GetMapping
    public ResponseEntity<Page<NextMaintenanceResponse>> getAllNextMaintenances(
            @Parameter(description = "Page number ") @RequestParam(defaultValue = "0") int page,
            @Parameter(description = "Number of items per page") @RequestParam(defaultValue = "5") int size,
            @PathVariable String tenant) {
        tenantService.setTenantInContext(tenant);
        try {
            Pageable pageable = PageRequest.of(page, size);
            if (page != 0) pageable = PageRequest.of(page - 1, size);
            return ResponseEntity.ok(nextMaintenanceService.getAll(pageable));
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

    }
    // --------------------------------------------//
    // *******GET-BY-ID*******//

    @Operation(summary = "Get Next Maintenance by Id", description = "Retrieves a Next Maintenance object by specifying its id.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Next Maintenance found", content = @Content(schema = @Schema(implementation = NextMaintenanceResponse.class), mediaType = "application/json")),
            @ApiResponse(responseCode = "404", description = "Next Maintenance not found")
    })
    @GetMapping("/{id}")
    public ResponseEntity<Optional<NextMaintenanceResponse>> getNextMaintenance(
            @Parameter(description = "id of the Next Maintenance to be get") @PathVariable UUID id,
            @PathVariable String tenant) {
        tenantService.setTenantInContext(tenant);
        try {
            return ResponseEntity.ok(nextMaintenanceService.getById(id));
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

    }
}
