package com.example.restaurant.model.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ReservationRequest {

    private LocalDateTime startDate;
    private LocalDateTime endDate;
    private int partySize;

    private long restaurantId;
    private Long restaurantTableId;
}
