package com.fleetNavchatservice.infraestructure.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.fleetNavchatservice.domain.entities.User;
import com.fleetNavchatservice.domain.repositories.UserRepository;
import com.fleetNavchatservice.util.enums.Status;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserService {

  private final UserRepository repository;

  public void saveUser(User user) {
    user.setStatus(Status.ONLINE);
    repository.save(user);
  }

  public void disconnect(User user) {
    var storedUser = repository.findById(user.getNickName()).orElse(null);
    if (storedUser != null) {
      storedUser.setStatus(Status.OFFLINE);
      repository.save(storedUser);
    }
  }

  public List<User> findConnectedUsers() {
    return repository.findAllByStatus(Status.ONLINE);
  }
}