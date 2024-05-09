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

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class ChatMessageController {

  private final SimpMessagingTemplate messagingTemplate;
  private final ChatMessageService chatMessageService;

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

  @GetMapping("/messages/{senderId}/{recipientId}")
  public ResponseEntity<List<ChatMessage>> findChatMessages(@PathVariable String senderId,
      @PathVariable String recipientId) {
    return ResponseEntity
        .ok(chatMessageService.findChatMessages(senderId, recipientId));
  }
}