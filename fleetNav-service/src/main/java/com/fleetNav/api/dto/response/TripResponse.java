package com.fleetNav.api.dto.response;

import java.util.List;

import com.fleetNav.domain.entities.Comment;
import com.fleetNav.domain.entities.Route;
import com.fleetNav.domain.entities.Vehicle;

import lombok.NoArgsConstructor;
import lombok.Getter;
import lombok.AllArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TripResponse {
    private String dateStart;
    private String dateEnd;
    private Double cost;
    private List<Comment> comment;
    private Route route;
    private Vehicle vehicle;
}
