package com.fleetNav.api.controllers;

import com.fleetNav.api.dto.request.CommentRequest;
import com.fleetNav.api.dto.response.CommentResponse;
import com.fleetNav.infraestructure.abstract_services.ICommentService;
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
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;
import java.util.UUID;



@Tag(name = "Comment" , description = "Our application's comment controller provides RESTful endpoints for managing comments on the platform. Use this controller to allow users to create, read, update and delete comments in different parts of the application.")
@RestController
@RequestMapping("/comments")
@AllArgsConstructor
public class CommentController {
    @Autowired
    private final ICommentService commentService;

    //--------------------------------------------//
    //*******SAVE*******//
    @Operation(
      summary = "Save a comment",
      description = "Saves a new comment in the database."
    )
    @ApiResponses({
      @ApiResponse(responseCode = "200",description = "Comment successfully saved" , content = {
                    @Content(schema = @Schema(implementation = CommentResponse.class), mediaType = "application/json") }),
      @ApiResponse(responseCode = "404",description = "Peticion no encontrada", content = { @Content(schema = @Schema()) }),
      @ApiResponse(responseCode = "500", content = { @Content(schema = @Schema()) }) })
    @PostMapping
    public ResponseEntity<CommentResponse> saveComment(@RequestBody CommentRequest commentRequest) {
        return ResponseEntity.ok(commentService.create(commentRequest));
    }
    // --------------------------------------------//
    // *******UPDATE*******//

    @Operation(summary = "Update a comment", description = "updates an existing comment in the database")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Comment successfully update", content = {
                    @Content(schema = @Schema(implementation = CommentResponse.class), mediaType = "application/json") }),
            @ApiResponse(responseCode = "404", description = "Peticion no encontrada", content = {
                    @Content(schema = @Schema()) }),
            @ApiResponse(responseCode = "500", content = { @Content(schema = @Schema() )}) })
    @PutMapping("/{id}")
    public ResponseEntity<CommentResponse> updateComment(
            @Parameter(description = "Id of the Comment to be update")@PathVariable UUID id, @RequestBody CommentRequest commentRequest) {
        return ResponseEntity.ok(commentService.update(id, commentRequest));
    }

    // --------------------------------------------//
    // *******DELETE*******//
    @Operation(summary = "Delete Comment by Id", description = "Deletes a Comment object by specifying its id.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Comment deleted"),
            @ApiResponse(responseCode = "404", description = "Comment not found")
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteComment(
            @Parameter(description = "Id of the Comment to be deleted") @PathVariable UUID id) {
        commentService.delete(id);
        return ResponseEntity.noContent().build();
    }

    // --------------------------------------------//
    // *******GET-ALL*******//
    @Operation(summary = "Get all Comments", description = "Retrieves a list of all tutorials with pagination support.")
    @GetMapping
    public ResponseEntity<Page<CommentResponse>> getAllComments(
            @Parameter(description = "Page number ")@RequestParam(defaultValue = "0") int page,
            @Parameter(description = "Number of items per page")@RequestParam(defaultValue = "5") int size) {
        Pageable pageable = PageRequest.of(page, size);
        if (page != 0) pageable = PageRequest.of(page - 1, size);
        return ResponseEntity.ok(commentService.getAll(pageable));
    }
    // --------------------------------------------//
    // *******GET-BY-ID*******//

    @Operation(summary = "Get Comment by Id", description = "Retrieves a Comment object by specifying its id.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Comment found", content = @Content(schema = @Schema(implementation = CommentResponse.class),mediaType = "application/json")),
            @ApiResponse(responseCode = "404", description = "Comment not found")
    })
    @GetMapping("/{id}")
    public ResponseEntity<Optional<CommentResponse>> getComment(
                    @Parameter(description = "id of the Comment to be get")@PathVariable UUID id) {
        return ResponseEntity.ok(commentService.getById(id));
    }
}
