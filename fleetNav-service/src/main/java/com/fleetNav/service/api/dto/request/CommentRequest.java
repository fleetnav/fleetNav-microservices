package com.fleetNav.service.api.dto.request;

import java.util.UUID;

import lombok.NoArgsConstructor;
import lombok.Getter;
import lombok.AllArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CommentRequest {
    private String date;
    private String observation;
    private Double price;
    private UUID tripId;
}
