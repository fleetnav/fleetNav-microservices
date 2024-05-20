package com.fleetNav.service.domain.entities;


import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import com.fleetNav.service.util.enums.TripStatusEnum;
import com.fleetNav.service.util.enums.VehicleStatusEnum;
import jakarta.persistence.*;

import lombok.*;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Trip {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(name = "driver_id",nullable = false, length = 36)
    private String driverId;

    @Column(name = "date_start", nullable = false, length = 30)
    private LocalDate dateStart;

    @Column(name = "date_end", nullable = false, length = 30)
    private LocalDate dateEnd;

    @Column(name = "owner_earning",nullable = false)
    private Double ownerEarning;

    @Column(name = "driver_earning",nullable = false)
    private Double driverEarning;

    @Column(length = 45, nullable = false)
    @Enumerated(EnumType.STRING)
    private TripStatusEnum status;

    @OneToMany(mappedBy = "trip", cascade = CascadeType.ALL, orphanRemoval = false,fetch = FetchType.EAGER)
    private List<Comment> comments = new ArrayList<>();

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.MERGE)
    @JoinColumn(name = "route_id")
    private Route route;

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.MERGE)
    @JoinColumn(name = "vehicle_id")
    private Vehicle vehicle;
}

