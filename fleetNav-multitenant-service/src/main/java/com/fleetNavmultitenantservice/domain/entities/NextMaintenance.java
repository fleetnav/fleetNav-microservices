package com.fleetNavmultitenantservice.domain.entities;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;

@Entity(name = "next_maintencance")
public class NextMaintenance {
  @Id
  @GeneratedValue(strategy = GenerationType.UUID)
  @Column(nullable = false, length = 36)
  private String id;
  @Column(nullable = false, length = 30)
  private String date;
  @Column(nullable = false, length = 10)
  private String hour;
  @Column(nullable = false, length = 45)
  private String location;

  @OneToOne(cascade = CascadeType.ALL)
  @JoinColumn(name = "vehicle_id", referencedColumnName = "id")

  private Vehicle vehicle;

}
