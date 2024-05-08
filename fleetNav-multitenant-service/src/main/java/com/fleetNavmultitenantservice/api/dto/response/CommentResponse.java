package com.fleetNavmultitenantservice.api.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

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
