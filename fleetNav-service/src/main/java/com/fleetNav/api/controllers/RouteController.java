package com.fleetNav.api.controllers;

import com.fleetNav.api.dto.request.RouteRequest;
import com.fleetNav.api.dto.response.RouteResponse;
import com.fleetNav.infraestructure.abstract_services.IRouteService;
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
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;
import java.util.UUID;

@Tag(name = "Route", description = "The route controller of our application provides RESTful endpoints to manage and obtain detailed information about the routes of a vehicle.")
@RestController
@RequestMapping("/Routes")
@AllArgsConstructor
public class RouteController {
    @Autowired
    private final IRouteService routeService;

    // --------------------------------------------//
    // *******SAVE*******//  
    @Operation(summary = "Save a Route", description = "Saves a new Route in the database.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Route successfully saved", content = {
                    @Content(schema = @Schema(implementation = RouteResponse.class), mediaType = "application/json")}),
            @ApiResponse(responseCode = "404", description = "Peticion no encontrada", content = {
                    @Content(schema = @Schema())}),
            @ApiResponse(responseCode = "500", content = {@Content(schema = @Schema())})})
    @PostMapping
    public ResponseEntity<RouteResponse> saveRoute(@RequestBody RouteRequest routeRequest) {
        return ResponseEntity.ok(routeService.create(routeRequest));
    }
    // --------------------------------------------//
    // *******UPDATE*******//

    @Operation(summary = "Update a Route", description = "updates an existing Route in the database")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Route successfully update", content = {
                    @Content(schema = @Schema(implementation = RouteResponse.class), mediaType = "application/json")}),
            @ApiResponse(responseCode = "404", description = "Peticion no encontrada", content = {
                    @Content(schema = @Schema())}),
            @ApiResponse(responseCode = "500", content = {@Content(schema = @Schema())})})
    @PutMapping("/{id}")
    public ResponseEntity<RouteResponse> updateRoute(
            @Parameter(description = "Id of the Route to be update") @PathVariable UUID id,
            @RequestBody RouteRequest routeRequest) {
        return ResponseEntity.ok(routeService.update(id, routeRequest));
    }

    // --------------------------------------------//
    // *******DELETE*******//
    @Operation(summary = "Delete Route by Id", description = "Deletes a Route object by specifying its id.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Route deleted"),
            @ApiResponse(responseCode = "404", description = "Route not found")
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteRoute(
            @Parameter(description = "Id of the Route to be deleted") @PathVariable UUID id) {
        routeService.delete(id);
        return ResponseEntity.noContent().build();
    }

    // --------------------------------------------//
    // *******GET-ALL*******//
    @Operation(summary = "Get all Routes", description = "Retrieves a list of all tutorials with pagination support.")
    @GetMapping
    public ResponseEntity<Page<RouteResponse>> getAllRoutes(
            @Parameter(description = "Page number ") @RequestParam(defaultValue = "0") int page,
            @Parameter(description = "Number of items per page") @RequestParam(defaultValue = "5") int size) {
        Pageable pageable = PageRequest.of(page, size);
        if (page != 0)
            pageable = PageRequest.of(page - 1, size);
        return ResponseEntity.ok(routeService.getAll(pageable));
    }
    // --------------------------------------------//
    // *******GET-BY-ID*******//

    @Operation(summary = "Get Route by Id", description = "Retrieves a Route object by specifying its id.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Route found", content = @Content(schema = @Schema(implementation = RouteResponse.class), mediaType = "application/json")),
            @ApiResponse(responseCode = "404", description = "Route not found")
    })
    @GetMapping("/{id}")
    public ResponseEntity<Optional<RouteResponse>> getRoute(
            @Parameter(description = "id of the Route to be get") @PathVariable UUID id) {
        return ResponseEntity.ok(routeService.getById(id));
    }
}
