package com.fleetNav.service.domain.entities;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Route {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(nullable = false, length = 45)
    private String name;

    @Column(nullable = false, length = 45)
    private String destination;

    @Column(nullable = false, length = 45)
    private String origin;

    @Column(name = "average_time", nullable = false, length = 5)
    private String averageTime;

    @Column(nullable = false, length = 10)
    private String mileage;

    @OneToMany(mappedBy = "route", cascade = CascadeType.ALL, orphanRemoval = true,fetch = FetchType.EAGER)
    private List<Stop> stops = new ArrayList<>();

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "cost_id", referencedColumnName = "id")
    private Cost costId;
}
