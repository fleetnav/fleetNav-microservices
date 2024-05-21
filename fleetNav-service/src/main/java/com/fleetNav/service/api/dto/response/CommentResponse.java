package com.fleetNav.service.api.dto.response;

import java.time.LocalDate;
import java.util.UUID;


import lombok.NoArgsConstructor;
import lombok.Getter;
import lombok.AllArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

public class CommentResponse {

    private UUID id;
    private LocalDate date;
    private String observation;
    private Double price;
}

