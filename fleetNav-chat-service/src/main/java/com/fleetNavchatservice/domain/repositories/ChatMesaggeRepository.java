package com.fleetNavchatservice.domain.repositories;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.fleetNavchatservice.domain.entities.ChatMessage;

public interface ChatMesaggeRepository extends MongoRepository<ChatMessage, String> {
  List<ChatMessage> findByChatId(String chatId);
}
