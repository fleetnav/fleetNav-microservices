package com.fleetNav.service.api.controllers;

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

    //--------------------------------------------//
    //*******SAVE*******//
    @Operation(
            summary = "Save a Trip",
            description = "Saves a new Trip in the database."
    )
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Trip successfully saved", content = {
                    @Content(schema = @Schema(implementation = TripResponse.class), mediaType = "application/json")}),
            @ApiResponse(responseCode = "404", description = "Peticion no encontrada", content = {@Content(schema = @Schema())}),
            @ApiResponse(responseCode = "500", content = {@Content(schema = @Schema())})})


    @PostMapping
    public ResponseEntity<TripResponse> saveTrip(@RequestBody TripRequest tripRequest,
                                                 @PathVariable String tenant) {
        tenantService.setTenantInContext(tenant);
        try {
            return ResponseEntity.ok(tripService.create(tripRequest));
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

    }
    // --------------------------------------------//
    // *******UPDATE*******//

    @Operation(summary = "Update a Trip", description = "updates an existing Trip in the database")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Trip successfully update", content = {
                    @Content(schema = @Schema(implementation = TripResponse.class), mediaType = "application/json")}),
            @ApiResponse(responseCode = "404", description = "Peticion no encontrada", content = {
                    @Content(schema = @Schema())}),
            @ApiResponse(responseCode = "500", content = {@Content(schema = @Schema())})})
    @PutMapping("/{id}")
    public ResponseEntity<TripResponse> updateTrip(
            @Parameter(description = "Id of the Trip to be update") @PathVariable UUID id,
            @RequestBody TripRequest tripRequest,
            @PathVariable String tenant) {
        tenantService.setTenantInContext(tenant);
        try {
            return ResponseEntity.ok(tripService.update(id, tripRequest));
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

    }

    // --------------------------------------------//
    // *******DELETE*******//
  /*  @Operation(summary = "Delete Trip by Id", description = "Deletes a Trip object by specifying its id.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Trip deleted"),
            @ApiResponse(responseCode = "404", description = "Trip not found")
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTrip(
            @Parameter(description = "Id of the Trip to be deleted") @PathVariable UUID id,
            @PathVariable String tenant) {
        tenantService.setTenantInContext(tenant);
        try {
            tripService.delete(id);
            return ResponseEntity.noContent().build();
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

    }*/

    // --------------------------------------------//
    // *******GET-ALL*******//
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
    // --------------------------------------------//
    // *******GET-BY-ID*******//

    @Operation(summary = "Get Trip by Id", description = "Retrieves a Trip object by specifying its id.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Trip found", content = @Content(schema = @Schema(implementation = TripResponse.class), mediaType = "application/json")),
            @ApiResponse(responseCode = "404", description = "Trip not found")
    })
    @GetMapping("/{id}")
    public ResponseEntity<Optional<TripResponse>> getTrip(@Parameter(description = "id of the Trip to be get") @PathVariable UUID id,
                                                          @PathVariable String tenant) {
        tenantService.setTenantInContext(tenant);
        try {
            return ResponseEntity.ok(tripService.getById(id));
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

    }
}