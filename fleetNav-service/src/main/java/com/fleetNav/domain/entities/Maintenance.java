package com.fleetNav.domain.entities;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
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
public class Maintenance {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(nullable = false, length = 36)
    private String id;
    @Column(nullable = false, length = 30)
    private String date;
    @Column(nullable = false, length = 200)
    private String observation;
    @Column(nullable = false, length = 36)
    private Double price;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "vehicle_id", referencedColumnName = "id")

    private Vehicle vehicle;
}
