package com.fleetNav.service.api.controllers;

import com.fleetNav.service.api.dto.error.ErrorsResponse;
import com.fleetNav.service.api.dto.request.VehicleRequest;
import com.fleetNav.service.api.dto.request.VehicleUpdateRequest;
import com.fleetNav.service.api.dto.response.VehicleResponse;
import com.fleetNav.service.infraestructure.abstract_services.IVehicleService;
import com.fleetNav.tenant.infrastructure.services.TenantService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@Tag(name = "Vehicle", description = "Our application's vehicle controller provides RESTful endpoints to manage and obtain information about the vehicles in a fleet.")
@RestController
@RequestMapping("/{tenant}/vehicles")
public class VehicleController {

        @Autowired
        private IVehicleService vehicleService;

        @Autowired
        private TenantService tenantService;

        @Operation(summary = "Save a Vehicle", description = "Saves a new Vehicle in the database.")
        @ApiResponses({
                        @ApiResponse(responseCode = "201", description = "Vehicle successfully saved", content = {
                                        @Content(schema = @Schema(implementation = VehicleResponse.class), mediaType = "application/json"),
                        }),
                        @ApiResponse(responseCode = "400", description = "Error : Invalid Request", content = {
                                        @Content(schema = @Schema(implementation = ErrorsResponse.class)) }),

                        @ApiResponse(responseCode = "500", description = "Error : Internal server error", content = {
                                        @Content(schema = @Schema(implementation = ErrorsResponse.class)) }),
        })
        @PostMapping
        public ResponseEntity<VehicleResponse> saveVehicle(
                        @Validated @RequestBody VehicleRequest vehicleRequest,
                        @PathVariable String tenant) {
                tenantService.setTenantInContext(tenant);
                try {
                        return ResponseEntity.ok(vehicleService.create(vehicleRequest));
                } catch (Exception e) {
                        System.out.println(e.getMessage());
                        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
                }
        }

        @Operation(summary = "Update a Vehicle", description = "updates an existing Vehicle in the database")
        @ApiResponses({
                        @ApiResponse(responseCode = "201", description = "Vehicle successfully update", content = {
                                        @Content(schema = @Schema(implementation = VehicleResponse.class), mediaType = "application/json"),
                        }),
                        @ApiResponse(responseCode = "400", description = "Error : Invalid Request", content = {
                                        @Content(schema = @Schema(implementation = ErrorsResponse.class)) }),
                        @ApiResponse(responseCode = "404", description = "Error : Id not found", content = {
                                        @Content(schema = @Schema(implementation = ErrorsResponse.class)) }),
                        @ApiResponse(responseCode = "500", description = "Error : Internal server error", content = {
                                        @Content(schema = @Schema(implementation = ErrorsResponse.class)) }),
        })
        @PutMapping("/{id}")
        public ResponseEntity<VehicleResponse> updateVehicle(
                        @Parameter(description = "Id of the Vehicle to be update") @PathVariable UUID id,
                        @Validated @RequestBody VehicleUpdateRequest vehicleUpdateRequest,
                        @PathVariable String tenant) {
                tenantService.setTenantInContext(tenant);
                try {
                        return ResponseEntity.ok(vehicleService.update(id, vehicleUpdateRequest));
                } catch (Exception e) {
                        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
                }
        }

        @Operation(summary = "Delete Vehicle by Id", description = "Deletes a Vehicle object by specifying its id.")
        @ApiResponses(value = {
                        @ApiResponse(responseCode = "204", description = "Vehicle deleted"),
                        @ApiResponse(responseCode = "400", description = "Error : Invalid Request", content = {
                                        @Content(schema = @Schema(implementation = ErrorsResponse.class)) }),
                        @ApiResponse(responseCode = "404", description = "Error : Id not found", content = {
                                        @Content(schema = @Schema(implementation = ErrorsResponse.class)) }),
                        @ApiResponse(responseCode = "500", description = "Error : Internal server error", content = {
                                        @Content(schema = @Schema(implementation = ErrorsResponse.class)) }),
        })
        @DeleteMapping("/{id}")
        public ResponseEntity<Void> deleteVehicle(
                        @Parameter(description = "Id of the Vehicle to be deleted") @PathVariable UUID id,
                        @PathVariable String tenant) {
                tenantService.setTenantInContext(tenant);
                try {
                        vehicleService.delete(id);
                        return ResponseEntity.noContent().build();
                } catch (Exception e) {
                        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
                }
        }

        @Operation(summary = "Get all Vehicles", description = "Retrieves a list of all tutorials with pagination support.")
        @GetMapping
        public ResponseEntity<Page<VehicleResponse>> getAllVehicles(
                        @Parameter(description = "Page number ") @RequestParam(defaultValue = "0") int page,
                        @Parameter(description = "Number of items per page") @RequestParam(defaultValue = "5") int size,
                        @PathVariable String tenant) {
                tenantService.setTenantInContext(tenant);
                try {
                        Pageable pageable = PageRequest.of(page, size);
                        if (page != 0)
                                pageable = PageRequest.of(page - 1, size);
                        return ResponseEntity.ok(vehicleService.getAll(pageable));
                } catch (Exception e) {
                        System.out.println(e.getMessage());
                        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
                }
        }

        @Operation(summary = "Get Vehicle by Id", description = "Retrieves a Vehicle object by specifying its id.")
        @ApiResponses(value = {
                        @ApiResponse(responseCode = "201", description = "Vehicle found", content = @Content(schema = @Schema(implementation = VehicleResponse.class), mediaType = "application/json")),
                        @ApiResponse(responseCode = "400", description = "Error : Invalid Request", content = {
                                        @Content(schema = @Schema(implementation = ErrorsResponse.class)) }),
                        @ApiResponse(responseCode = "404", description = "Error : Id not found", content = {
                                        @Content(schema = @Schema(implementation = ErrorsResponse.class)) }),
                        @ApiResponse(responseCode = "500", description = "Error : Internal server error", content = {
                                        @Content(schema = @Schema(implementation = ErrorsResponse.class)) }),
        })
        @GetMapping("/{id}")
        public ResponseEntity<Optional<VehicleResponse>> getVehicle(
                        @Parameter(description = "id of the Vehicle to be get") @PathVariable UUID id,
                        @PathVariable String tenant) {
                tenantService.setTenantInContext(tenant);
                try {
                        return ResponseEntity.ok(vehicleService.getById(id));
                } catch (Exception e) {
                        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
                }
        }
}
