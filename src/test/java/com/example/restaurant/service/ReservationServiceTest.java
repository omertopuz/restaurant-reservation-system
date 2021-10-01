package com.example.restaurant.service;

import com.example.restaurant.exception.AlreadyBookedException;
import com.example.restaurant.exception.CapacityExceededException;
import com.example.restaurant.model.dto.ReservationRequest;
import com.example.restaurant.model.entity.Reservations;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class ReservationServiceTest {

    @Autowired
    private ReservationService reservationService;

    @Test
    void test_CreateReservations(){
        long restaurantId = 3l;
        long tableId = 17l;
        int partySize = 3;
        ReservationRequest request = new ReservationRequest(LocalDateTime.of(2021,10,11,15,45)
        ,LocalDateTime.of(2021,10,11,16,30)
        ,partySize
        ,restaurantId
        ,tableId);

        Reservations reservations = reservationService.createReservation(request);
        assertNotNull(reservations);
        assertTrue(reservations.getId()>0);
    }

    @Test
    void test_RequestCreateReservationsForOversize_Expect_CapacityExceededException(){
        long restaurantId = 3l;
        long tableId = 18l;
        int partySize = 5;
        ReservationRequest request = new ReservationRequest(LocalDateTime.of(2021,10,11,15,45)
                ,LocalDateTime.of(2021,10,11,16,30)
                ,partySize
                ,restaurantId
                ,tableId);
        assertThrows(CapacityExceededException.class,()->{
            Reservations reservations = reservationService.createReservation(request);
        });
    }

    @Test
    void test_RequestCreateReservationsForAlreadyBookedSlot_Expect_AlreadyBookedException(){
        long restaurantId = 1l;
        long tableId = 4l;
        int partySize = 4;
        ReservationRequest request = new ReservationRequest(LocalDateTime.of(2021,10,11,18,30)
                ,LocalDateTime.of(2021,10,11,20,00)
                ,partySize
                ,restaurantId
                ,tableId);
        assertThrows(AlreadyBookedException.class,()->{
            Reservations reservations = reservationService.createReservation(request);
        });
    }
}