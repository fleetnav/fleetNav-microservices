package com.fleetNavchatservice.domain.entities;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import com.fleetNavchatservice.util.enums.Status;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Document
public class User {

  @Id
  private String nickName;
  private String fullName;
  private Status status;
}
