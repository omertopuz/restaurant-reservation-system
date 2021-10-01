package com.example.restaurant.model.dto;

import com.example.restaurant.model.entity.Restaurant;
import com.example.restaurant.util.AppUtil;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@NoArgsConstructor
@Getter
@Setter
public class RestaurantDto {


    public RestaurantDto(Restaurant restaurant) {
        this.id = restaurant.getId();
        this.name = restaurant.getName();
        this.openingTime = restaurant.getOpeningTime() != null ? restaurant.getOpeningTime().format(AppUtil.getTimeFormatter()):"7/24";
        this.closingTime = restaurant.getOpeningTime() != null ? restaurant.getClosingTime().format(AppUtil.getTimeFormatter()):"7/24";
    }

    private long id;
    private String name;

    private String openingTime;

    private String closingTime;

}
