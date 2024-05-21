package com.fleetNavchatservice.domain.repositories;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.fleetNavchatservice.domain.entities.User;
import com.fleetNavchatservice.util.enums.Status;

public interface UserRepository extends MongoRepository<User, String> {
  List<User> findAllByStatus(Status status);
}
