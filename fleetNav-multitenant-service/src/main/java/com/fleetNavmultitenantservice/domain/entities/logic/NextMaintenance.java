package com.fleetNavmultitenantservice.domain.entities.logic;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "next_maintenance")
public class NextMaintenance {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(nullable = false, length = 30)
    private String date;

    @Column(nullable = false, length = 10)
    private String hour;

    @Column(nullable = false, length = 45)
    private String location;

}
