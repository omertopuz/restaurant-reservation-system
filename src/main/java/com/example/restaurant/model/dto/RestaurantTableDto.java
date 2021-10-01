package com.example.restaurant.model.dto;

import com.example.restaurant.model.entity.RestaurantTable;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class RestaurantTableDto {
    private long id ;
    private int capacity;
    private RestaurantDto restaurantDto;

    public RestaurantTableDto(RestaurantTable restaurantTable) {
        this.id = restaurantTable.getId();
        this.capacity = restaurantTable.getCapacity();
        this.restaurantDto = new RestaurantDto(restaurantTable.getRestaurant());
    }
}
