package com.fleetNavchatservice.api.controllers;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.fleetNavchatservice.api.dto.ChatNotification;
import com.fleetNavchatservice.domain.entities.ChatMessage;
import com.fleetNavchatservice.infraestructure.services.ChatMessageService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;

@Tag(name = "Chat Mesagge", description = "Our application's chat message controller provides RESTful endpoints to manage and send messages between users in a chat system.")
@Controller
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class ChatMessageController {

  private final SimpMessagingTemplate messagingTemplate;
  private final ChatMessageService chatMessageService;

  @Operation(summary = "Send message", description = "Sends a message to all users connected to the chat.")
  @ApiResponses(value = {
      @ApiResponse(responseCode = "200", description = "Message sent successfully"),
      @ApiResponse(responseCode = "400", description = "Invalid message data")
  })
  @MessageMapping("/chat")
  public void processMessage(@Payload ChatMessage chatMessage) {
    ChatMessage savedMsg = chatMessageService.save(chatMessage);
    messagingTemplate.convertAndSendToUser(
        chatMessage.getRecipientId(), "/queue/messages",
        new ChatNotification(
            savedMsg.getId(),
            savedMsg.getSenderId(),
            savedMsg.getRecipientId(),
            savedMsg.getContent()));
  }
 @Operation(
        summary = "Get chat messages",
        description = "Retrieves all chat messages between two specific users."
    )
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Messages successfully retrieved",
            content = @Content(array = @ArraySchema(schema = @Schema(implementation = ChatMessage.class)))),
        @ApiResponse(responseCode = "404", description = "Messages not found")
    })
  @GetMapping("/messages/{senderId}/{recipientId}")
  public ResponseEntity<List<ChatMessage>> findChatMessages(@Parameter(description = "Id of the sender user") @PathVariable String senderId,
      @Parameter(description = "Id ofthe recipient user") @PathVariable String recipientId) {
    return ResponseEntity
        .ok(chatMessageService.findChatMessages(senderId, recipientId));
  }
}