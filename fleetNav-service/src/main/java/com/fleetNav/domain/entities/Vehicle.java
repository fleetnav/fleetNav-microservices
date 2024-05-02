package com.fleetNav.domain.entities;


import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Vehicle {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(nullable = false, length = 36)
    private String id;
    @Column(name = "number_plate", length = 10, nullable = false)
    private String numberPlate;
    @Column(length = 4, nullable = false)
    private String model;
    @Column(length = 10, nullable = false)
    private String mileage;
    @Column(length = 45, nullable = false)
    private String status;
    @Column(length = 45, nullable = false)
    private String ownerId;
    @OneToOne()
    @JoinColumn(name = "next_maintenance_id", referencedColumnName = "id")
    private NextMaintenance nextMaintenance;
    @OneToOne()
    @JoinColumn(name = "vehicle_status_id", referencedColumnName = "id")
    private VehicleStatus vehicleStatus;
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "trip_id", referencedColumnName = "id")
    private Trip trip;
    @OneToOne()
    @JoinColumn(name = "maintenance_id", referencedColumnName = "id")
    private Maintenance maintenance;


}
