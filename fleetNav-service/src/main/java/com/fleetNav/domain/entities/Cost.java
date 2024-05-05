package com.fleetNav.domain.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Cost {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(name = "number_toll", nullable = false)
    private Integer numberToll;

    @Column(name = "price_toll", nullable = false)
    private Double priceToll;

    @Column(name = "price_gasoline", nullable = false)
    private Double priceGasoline;

    @Column(name = "total_price", nullable = false)
    private Double totalPrice;

}
