package com.example.restaurant.repository;

import com.example.restaurant.model.entity.Reservations;
import com.example.restaurant.model.entity.Restaurant;
import com.example.restaurant.model.entity.RestaurantTable;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class RestaurantRepositoryTest {

    @Autowired
    private RestaurantRepository restaurantRepository;

    @Test
    void test_Tables(){
        List<Restaurant> restaurantList = restaurantRepository.findAll();
    }

    @Test
    void test_FindAvailableTables(){
        long restaurantId = 1l;
        int partySize = 3;
        LocalDateTime startDate = LocalDateTime.of(2021,10,11,12,45);
        LocalDateTime endDate = LocalDateTime.of(2021,10,11,16,30);

        List<RestaurantTable> availableTablesList = restaurantRepository.filterAvailableTablesInRestaurant(restaurantId,partySize
                ,startDate,endDate,LocalTime.from(startDate),LocalTime.from(endDate),null);

        assertEquals(availableTablesList.size(),3);
    }


    @Test
    void test_FindReservationDetails(){
        long reservationId = 4l;
        Reservations reservation = restaurantRepository.getReservationsDetails(reservationId);

        assertNotNull(reservation);
        assertEquals(reservation.getId(),reservationId);
    }
}