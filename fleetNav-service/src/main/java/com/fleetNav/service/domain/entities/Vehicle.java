package com.fleetNav.service.domain.entities;


import com.fleetNav.service.util.enums.VehicleStatusEnum;
import jakarta.persistence.*;

import lombok.*;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Vehicle {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(name = "number_plate", length = 10, nullable = false)
    private String numberPlate;

    @Column(length = 4, nullable = false)
    private String model;

    @Column(length = 10, nullable = false)
    private String mileage;

    @Column(length = 45, nullable = false)
    @Enumerated(EnumType.STRING)
    private VehicleStatusEnum status;

    @OneToMany(mappedBy = "vehicle", cascade = CascadeType.ALL, orphanRemoval = true,fetch = FetchType.EAGER)
    private List<Trip> trips;

    @OneToMany(mappedBy = "vehicle", cascade = CascadeType.ALL, orphanRemoval = true,fetch = FetchType.EAGER)
    private List<Maintenance> maintenances = new ArrayList<>();

    @OneToOne
    @JoinColumn(name = "next_maintenance_id", referencedColumnName = "id")
    private NextMaintenance nextMaintenance;

    @OneToOne
    @JoinColumn(name = "vehicle_status_id", referencedColumnName = "id")
    private VehicleStatus vehicleStatus;
}
