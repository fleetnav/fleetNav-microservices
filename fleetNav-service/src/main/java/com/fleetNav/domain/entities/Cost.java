package com.fleetNav.domain.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Cost {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(nullable = false, length = 36)
    private String id;
    @Column(name = "number_toll", nullable = false)
    private Integer numberToll;
    @Column(name = "price_toll", nullable = false)
    private Double priceToll;
    @Column(name = "price_gasoline", nullable = false)
    private Double priceGasoline;
    @Column(name = "total_price", nullable = false)
    private Double totalPrice;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "route_id", referencedColumnName = "id")
    private Route route;

}
