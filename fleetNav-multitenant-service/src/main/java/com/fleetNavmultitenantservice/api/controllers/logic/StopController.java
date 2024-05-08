package com.fleetNavmultitenantservice.api.controllers.logic;


import com.fleetNavmultitenantservice.api.dto.request.StopRequest;
import com.fleetNavmultitenantservice.api.dto.response.StopResponse;
import com.fleetNavmultitenantservice.infraestructure.abstract_services.logic.IStopService;
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

@Tag(name = "Stop", description = "Our application's stop controller provides RESTful endpoints to manage and obtain information about stops on a transport route.")
@RestController
@RequestMapping("/stops")
@AllArgsConstructor
public class StopController {
    @Autowired
    private final IStopService stopService;

    // --------------------------------------------//
    // *******SAVE*******//
    @Operation(summary = "Save a Stopt", description = "Saves a new Stopt in the database.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Stop successfully saved", content = {
                    @Content(schema = @Schema(implementation = StopResponse.class), mediaType = "application/json") }),
            @ApiResponse(responseCode = "404", description = "Peticion no encontrada", content = {
                    @Content(schema = @Schema()) }),
            @ApiResponse(responseCode = "500", content = { @Content(schema = @Schema()) }) })
    @PostMapping
    public ResponseEntity<StopResponse> saveStop(@RequestBody StopRequest stopRequest) {
        return ResponseEntity.ok(stopService.create(stopRequest));
    }
    // --------------------------------------------//
    // *******UPDATE*******//

    @Operation(summary = "Update a Stopt", description = "updates an existing Stopt in the database")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Stop successfully update", content = {
                    @Content(schema = @Schema(implementation = StopResponse.class), mediaType = "application/json") }),
            @ApiResponse(responseCode = "404", description = "Peticion no encontrada", content = {
                    @Content(schema = @Schema()) }),
            @ApiResponse(responseCode = "500", content = { @Content(schema = @Schema()) }) })
    @PutMapping("/{id}")
    public ResponseEntity<StopResponse> updateStop( @Parameter(description = "Id of the Stop to be update")@PathVariable UUID id, @RequestBody StopRequest stopRequest) {
        return ResponseEntity.ok(stopService.update(id, stopRequest));
    }

    // --------------------------------------------//
    // *******DELETE*******//
    @Operation(summary = "Delete Stop by Id", description = "Deletes a Stop object by specifying its id.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Stop deleted"),
            @ApiResponse(responseCode = "404", description = "Stop not found")
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteStop(
            @Parameter(description = "Id of the Stop to be deleted")@PathVariable UUID id) {
        stopService.delete(id);
        return ResponseEntity.noContent().build();
    }

    // --------------------------------------------//
    // *******GET-ALL*******//
    @Operation(summary = "Get all Stops", description = "Retrieves a list of all tutorials with pagination support.")
    @GetMapping
    public ResponseEntity<Page<StopResponse>> getAllStops(
            @Parameter(description = "Page number ")@RequestParam(defaultValue = "0") int page,
            @Parameter(description = "Number of items per page")@RequestParam(defaultValue = "5") int size) {
        Pageable pageable = PageRequest.of(page, size);
        if (page != 0)
            pageable = PageRequest.of(page - 1, size);
        return ResponseEntity.ok(stopService.getAll(pageable));
    }
    // --------------------------------------------//
    // *******GET-BY-ID*******//

    @Operation(summary = "Get Stop by Id", description = "Retrieves a Stop object by specifying its id.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Stop found", content = @Content(schema = @Schema(implementation = StopResponse.class),mediaType = "application/json")),
            @ApiResponse(responseCode = "404", description = "Stop not found")
    })
    @GetMapping("/{id}")
    public ResponseEntity<Optional<StopResponse>> getStop(
            @Parameter(description = "id of the Stop to be get")@PathVariable UUID id) {
        return ResponseEntity.ok(stopService.getById(id));
    }
}
