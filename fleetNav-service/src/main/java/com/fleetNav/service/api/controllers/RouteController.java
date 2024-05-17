package com.fleetNav.service.api.controllers;

import com.fleetNav.service.api.dto.error.ErrorsResponse;
import com.fleetNav.service.api.dto.request.RouteRequest;
import com.fleetNav.service.api.dto.request.RouteUpdateRequest;
import com.fleetNav.service.api.dto.response.RouteResponse;
import com.fleetNav.service.infraestructure.abstract_services.IRouteService;
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

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@Tag(name = "Route", description = "The route controller of our application provides RESTful endpoints to manage and obtain detailed information about the routes of a vehicle.")
@RestController
@RequestMapping("/{tenant}/routes")
@AllArgsConstructor
public class RouteController {

        @Autowired
        private final IRouteService routeService;

        @Autowired
        private TenantService tenantService;

        @Operation(summary = "Save a Route", description = "Saves a new Route in the database.")
        @ApiResponses({
                        @ApiResponse(responseCode = "200", description = "Route successfully saved", content = {
                                        @Content(schema = @Schema(implementation = RouteResponse.class), mediaType = "application/json"),
                        }),
                        @ApiResponse(responseCode = "400", description = "Error : Invalid Request", content = {
                                        @Content(schema = @Schema(implementation = ErrorsResponse.class)) }),

                        @ApiResponse(responseCode = "500", description = "Error : Internal server error", content = {
                                        @Content(schema = @Schema(implementation = ErrorsResponse.class)) }),
        })
        @PostMapping
        public ResponseEntity<RouteResponse> saveRoute(
                        @Validated @RequestBody RouteRequest routeRequest,
                        @PathVariable String tenant) {
                tenantService.setTenantInContext(tenant);
                try {
                        return ResponseEntity.ok(routeService.create(routeRequest));
                } catch (Exception e) {
                        System.out.println(e.getMessage());
                        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
                }
        }

        @Operation(summary = "Update a Route", description = "updates an existing Route in the database")
        @ApiResponses({
                        @ApiResponse(responseCode = "200", description = "Route successfully update", content = {
                                        @Content(schema = @Schema(implementation = RouteResponse.class), mediaType = "application/json"),
                        }),
                        @ApiResponse(responseCode = "400", description = "Error : Invalid Request", content = {
                                        @Content(schema = @Schema(implementation = ErrorsResponse.class)) }),
                        @ApiResponse(responseCode = "404", description = "Error : Id not found", content = {
                                        @Content(schema = @Schema(implementation = ErrorsResponse.class)) }),
                        @ApiResponse(responseCode = "500", description = "Error : Internal server error", content = {
                                        @Content(schema = @Schema(implementation = ErrorsResponse.class)) }),
        })
        @PutMapping("/{id}")
        public ResponseEntity<RouteResponse> updateRoute(
                        @Parameter(description = "Id of the Route to be update") @PathVariable UUID id,
                        @Validated @RequestBody RouteUpdateRequest routeUpdateRequest,
                        @PathVariable String tenant) {
                tenantService.setTenantInContext(tenant);
                try {
                        return ResponseEntity.ok(routeService.update(id, routeUpdateRequest));
                } catch (Exception e) {
                        System.out.println(e.getMessage());
                        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
                }
        }

        @Operation(summary = "Delete Route by Id", description = "Deletes a Route object by specifying its id.")
        @ApiResponses(value = {
                        @ApiResponse(responseCode = "204", description = "Route deleted"),
                        @ApiResponse(responseCode = "400", description = "Error : Invalid Request", content = {
                                        @Content(schema = @Schema(implementation = ErrorsResponse.class)) }),
                        @ApiResponse(responseCode = "404", description = "Error : Id not found", content = {
                                        @Content(schema = @Schema(implementation = ErrorsResponse.class)) }),
                        @ApiResponse(responseCode = "500", description = "Error : Internal server error", content = {
                                        @Content(schema = @Schema(implementation = ErrorsResponse.class)) }),
        })
        @DeleteMapping("/{id}")
        public ResponseEntity<Void> deleteRoute(
                        @Parameter(description = "Id of the Route to be deleted") @PathVariable UUID id,
                        @PathVariable String tenant) {
                tenantService.setTenantInContext(tenant);
                try {
                        routeService.delete(id);
                        return ResponseEntity.noContent().build();
                } catch (Exception e) {
                        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
                }
        }

        @Operation(summary = "Get all Routes", description = "Retrieves a list of all tutorials with pagination support.")
        @GetMapping
        public ResponseEntity<Page<RouteResponse>> getAllRoutes(
                        @Parameter(description = "Page number ") @RequestParam(defaultValue = "0") int page,
                        @Parameter(description = "Number of items per page") @RequestParam(defaultValue = "5") int size,
                        @PathVariable String tenant) {
                tenantService.setTenantInContext(tenant);
                try {
                        Pageable pageable = PageRequest.of(page, size);
                        if (page != 0)
                                pageable = PageRequest.of(page - 1, size);
                        return ResponseEntity.ok(routeService.getAll(pageable));
                } catch (Exception e) {
                        System.out.println(e.getMessage());
                        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
                }
        }

        @Operation(summary = "Get Route by Id", description = "Retrieves a Route object by specifying its id.")
        @ApiResponses(value = {
                        @ApiResponse(responseCode = "200", description = "Route found", content = @Content(schema = @Schema(implementation = RouteResponse.class), mediaType = "application/json")),
                        @ApiResponse(responseCode = "400", description = "Error : Invalid Request", content = {
                                        @Content(schema = @Schema(implementation = ErrorsResponse.class)) }),
                        @ApiResponse(responseCode = "404", description = "Error : Id not found", content = {
                                        @Content(schema = @Schema(implementation = ErrorsResponse.class)) }),
                        @ApiResponse(responseCode = "500", description = "Error : Internal server error", content = {
                                        @Content(schema = @Schema(implementation = ErrorsResponse.class)) }),
        })
        @GetMapping("/{id}")
        public ResponseEntity<Optional<RouteResponse>> getRoute(
                        @Parameter(description = "id of the Route to be get") @PathVariable UUID id,
                        @PathVariable String tenant) {
                tenantService.setTenantInContext(tenant);
                try {
                        return ResponseEntity.ok(routeService.getById(id));
                } catch (Exception e) {
                        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
                }
        }
}
