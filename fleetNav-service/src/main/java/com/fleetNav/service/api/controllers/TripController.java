package com.fleetNav.service.api.controllers;

import com.fleetNav.service.api.dto.error.ErrorsResponse;
import com.fleetNav.service.api.dto.request.TripRequest;
import com.fleetNav.service.api.dto.response.TripResponse;

import com.fleetNav.tenant.infrastructure.services.TenantService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

import com.fleetNav.service.infraestructure.abstract_services.ITripService;
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

@Tag(name = "Trip", description = "Our application's trip controller provides RESTful endpoints to manage and obtain information about planned trips.")
@RestController
@RequestMapping("/{tenant}/trips")
@AllArgsConstructor
public class TripController {
    @Autowired
    private final ITripService tripService;

    @Autowired
    private TenantService tenantService;

    @Operation(summary = "Save a Trip", description = "Saves a new Trip in the database.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Trip successfully saved", content = {
                    @Content(schema = @Schema(implementation = TripResponse.class), mediaType = "application/json") }),
            @ApiResponse(responseCode = "400", description = "Error : Invalid Request", content = {
                    @Content(schema = @Schema(implementation = ErrorsResponse.class)) }),

            @ApiResponse(responseCode = "500", description = "Error : Internal server error", content = {
                    @Content(schema = @Schema(implementation = ErrorsResponse.class)) }), })

    @PostMapping
    public ResponseEntity<TripResponse> saveTrip(
            @Validated @RequestBody TripRequest tripRequest,
            @PathVariable String tenant) {
        tenantService.setTenantInContext(tenant);
        try {
            return ResponseEntity.ok(tripService.create(tripRequest));
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

    }

    @Operation(summary = "Update a Trip", description = "updates an existing Trip in the database")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Trip successfully update", content = {
                    @Content(schema = @Schema(implementation = TripResponse.class), mediaType = "application/json") }),
            @ApiResponse(responseCode = "400", description = "Error : Invalid Request", content = {
                    @Content(schema = @Schema(implementation = ErrorsResponse.class)) }),
            @ApiResponse(responseCode = "404", description = "Error : Id not found", content = {
                    @Content(schema = @Schema(implementation = ErrorsResponse.class)) }),
            @ApiResponse(responseCode = "500", description = "Error : Internal server error", content = {
                    @Content(schema = @Schema(implementation = ErrorsResponse.class)) }), })
    @PutMapping("/{id}")
    public ResponseEntity<TripResponse> updateTrip(
            @Parameter(description = "Id of the Trip to be update") @PathVariable UUID id,
            @Validated @RequestBody TripRequest tripRequest,
            @PathVariable String tenant) {
        tenantService.setTenantInContext(tenant);
        try {
            return ResponseEntity.ok(tripService.update(id, tripRequest));
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

    }

    @Operation(summary = "Get all Trips", description = "Retrieves a list of all tutorials with pagination support.")
    @GetMapping
    public ResponseEntity<Page<TripResponse>> getAllTrips(
            @Parameter(description = "Page number ") @RequestParam(defaultValue = "0") int page,
            @Parameter(description = "Number of items per page") @RequestParam(defaultValue = "5") int size,
            @PathVariable String tenant) {
        tenantService.setTenantInContext(tenant);
        try {
            Pageable pageable = PageRequest.of(page, size);
            if (page != 0)
                pageable = PageRequest.of(page - 1, size);
            return ResponseEntity.ok(tripService.getAll(pageable));
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

    }

    @Operation(summary = "Get Trip by Id", description = "Retrieves a Trip object by specifying its id.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Trip found", content = @Content(schema = @Schema(implementation = TripResponse.class), mediaType = "application/json")),
            @ApiResponse(responseCode = "400", description = "Error : Invalid Request", content = {
                    @Content(schema = @Schema(implementation = ErrorsResponse.class)) }),
            @ApiResponse(responseCode = "404", description = "Error : Id not found", content = {
                    @Content(schema = @Schema(implementation = ErrorsResponse.class)) }),
            @ApiResponse(responseCode = "500", description = "Error : Internal server error", content = {
                    @Content(schema = @Schema(implementation = ErrorsResponse.class)) }),
    })
    @GetMapping("/{id}")
    public ResponseEntity<Optional<TripResponse>> getTrip(
            @Parameter(description = "id of the Trip to be get") @PathVariable UUID id,
            @PathVariable String tenant) {
        tenantService.setTenantInContext(tenant);
        try {
            return ResponseEntity.ok(tripService.getById(id));
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

    }
}
