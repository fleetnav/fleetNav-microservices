package com.fleetNav.service.domain.entities;


import jakarta.persistence.*;

import lombok.*;

import java.util.UUID;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Stop {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(nullable = false, length = 45)
    private String name;

    @Column(nullable = false, length = 45)
    private String location;

    @Column(nullable = false, length = 45)
    private String time;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "route_id", referencedColumnName = "id")
    private Route route;
}
