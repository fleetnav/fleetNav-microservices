package com.fleetNavchatservice.api.controllers;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;

import com.fleetNavchatservice.domain.entities.User;
import com.fleetNavchatservice.infraestructure.services.UserService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;

@Tag(name = "User", description = "Our application's user controller provides RESTful endpoints to manage and interact with users in a chat system.")
@Controller
@CrossOrigin(origins = "*")
@RequiredArgsConstructor
public class UserController {

  private final UserService userService;
@Operation(
        summary = "Add user",
        description = "Add a new user and publish it to all subscribers."
    )
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "User successfully added",
            content = @Content(schema = @Schema(implementation = User.class))),
        @ApiResponse(responseCode = "400", description = "Invalid user data")
    })
  @MessageMapping("/user.addUser")
  @SendTo("/user/public")
  public User addUser(
      @Payload User user) {
    userService.saveUser(user);
    return user;
  }

  @Operation(summary = "Disconnect user", description = "Disconnect a user and publish it to all subscribers.")
  @ApiResponses(value = {
      @ApiResponse(responseCode = "200", description = "User successfully disconnected", content = @Content(schema = @Schema(implementation = User.class))),
      @ApiResponse(responseCode = "400", description = "Invalid user data")
  })
  @MessageMapping("/user.disconnectUser")
  @SendTo("/user/public")
  public User disconnectUser(
      @Payload User user) {
    userService.disconnect(user);
    return user;
  }
 @Operation(
        summary = "Get connected users",
        description = "Retrieves a list of all connected users."
    )
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Connected users successfully recovered",
            content = @Content(array = @ArraySchema(schema = @Schema(implementation = User.class)))),
        @ApiResponse(responseCode = "500", description = "Internal server error")
    })
  @GetMapping("/users")
  public ResponseEntity<List<User>> findConnectedUsers() {
    return ResponseEntity.ok(userService.findConnectedUsers());
  }
}