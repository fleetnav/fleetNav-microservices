package com.fleetNav.api.dto.response;

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

    private String date;

    private String observation;

    private Double price;
}
/*
 * Is not necessary show trip id, because if you're watching this comment is
 * because you had already filtered
 */
