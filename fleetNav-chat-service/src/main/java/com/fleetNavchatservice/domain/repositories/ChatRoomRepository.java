package com.fleetNavchatservice.domain.repositories;

import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.fleetNavchatservice.domain.entities.ChatRoom;

public interface ChatRoomRepository extends MongoRepository<ChatRoom, String> {
  Optional<ChatRoom> findBySenderIdAndRecipientId(String senderId, String recipientId);
}
