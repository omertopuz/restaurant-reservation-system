package com.example.restaurant.model.dto;

import com.example.restaurant.model.entity.Reservations;
import com.example.restaurant.util.AppUtil;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.format.DateTimeFormatter;

@NoArgsConstructor
@Getter
@Setter
public class ReservationsDto {

    private long id;
    private String startDate;
    private String endDate;
    private int partySize;
    private RestaurantTableDto restaurantTable;

    public ReservationsDto(Reservations reservations) {
        this.id = reservations.getId();
        this.startDate = reservations.getStartDate() != null ? reservations.getStartDate().format(AppUtil.getDateTimeFormatter()):"";
        this.endDate = reservations.getEndDate() != null ? reservations.getEndDate().format(AppUtil.getDateTimeFormatter()):"";
        this.partySize = reservations.getPartySize();
        this.restaurantTable = new RestaurantTableDto(reservations.getRestaurantTable());
    }

}
