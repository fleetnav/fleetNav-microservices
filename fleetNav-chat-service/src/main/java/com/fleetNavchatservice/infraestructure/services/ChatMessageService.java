package com.fleetNavchatservice.infraestructure.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.fleetNavchatservice.domain.entities.ChatMessage;
import com.fleetNavchatservice.domain.repositories.ChatMesaggeRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ChatMessageService {
  private final ChatMesaggeRepository repository;
  private final ChatRoomService chatRoomService;

  public ChatMessage save(ChatMessage chatMessage) {
    var chatId = chatRoomService
        .getChatRoomId(chatMessage.getSenderId(), chatMessage.getRecipientId(), true)
        .orElseThrow(); // You can create your own dedicated exception
    chatMessage.setChatId(chatId);
    repository.save(chatMessage);
    return chatMessage;
  }

  public List<ChatMessage> findChatMessages(String senderId, String recipientId) {
    var chatId = chatRoomService.getChatRoomId(senderId, recipientId, false);
    return chatId.map(repository::findByChatId).orElse(new ArrayList<>());
  }
}