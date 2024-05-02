package com.fleetNavmultitenantservice.domain.entities;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class Stop {
  @Id
  @GeneratedValue(strategy =  GenerationType.UUID)
  @Column(nullable = false, length = 36 )
  private String id ;
  @Column(nullable = false, length = 45)
  private String name;
  @Column(nullable = false, length = 45)
  private String location;
  @Column(nullable = false, length = 45)
  private String time;
  @ManyToOne(cascade = CascadeType.ALL)
  @JoinColumn(name = "route_id", referencedColumnName = "id")
  private Route route ;
}
