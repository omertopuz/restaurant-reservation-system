package com.example.restaurant.controller;

import com.example.restaurant.model.dto.ReservationRequest;
import com.example.restaurant.model.dto.ReservationRequestDto;
import com.example.restaurant.model.dto.ReservationsDto;
import com.example.restaurant.model.dto.RestaurantTableDto;
import com.example.restaurant.model.entity.RestaurantTable;
import com.example.restaurant.service.ReservationService;
import com.example.restaurant.util.AppUtil;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("api/v1/reservations")
public class ReservationController {

    private final ReservationService reservationService;
    private final ObjectMapper objectMapper;

    public ReservationController(ReservationService reservationService, ObjectMapper objectMapper) {
        this.reservationService = reservationService;
        this.objectMapper = objectMapper;
    }

    @RequestMapping(method = RequestMethod.GET, path = "/restaurants/{restaurantId}/restaurant-tables")
    public List<RestaurantTableDto> getAvailableRestaurantTable(@PathVariable("restaurantId") long restaurantId
                                                                ,@RequestParam("partySize") int partySize
                                                                ,@RequestParam("startDate") String startDate
                                                                ,@RequestParam("endDate") String endDate
    ){
        ReservationRequest request = new ReservationRequest(LocalDateTime.parse(startDate, AppUtil.getDateTimeFormatter())
                ,LocalDateTime.parse(endDate,AppUtil.getDateTimeFormatter())
                ,partySize
                ,restaurantId
                ,null);
        List<RestaurantTable> availableTablesInRestaurant = reservationService.getAvailableTablesInRestaurant(request);
        return availableTablesInRestaurant.stream().map(r->new RestaurantTableDto(r)).collect(Collectors.toList());
    }

    @RequestMapping(method = RequestMethod.POST, path = "/restaurants/{restaurantId}/restaurant-tables/{restaurantTableId}")
    @ResponseStatus(HttpStatus.CREATED)
    public ReservationsDto createReservation(@PathVariable("restaurantId") long restaurantId
            ,@PathVariable("restaurantTableId") long restaurantTableId
            ,@RequestBody ReservationRequestDto reservationRequest
    ) {
        ReservationRequest request = new ReservationRequest(LocalDateTime.parse(reservationRequest.getStartDate(), AppUtil.getDateTimeFormatter())
                ,LocalDateTime.parse(reservationRequest.getEndDate(),AppUtil.getDateTimeFormatter())
                ,reservationRequest.getPartySize()
                ,restaurantId
                ,restaurantTableId);
        return new ReservationsDto(reservationService.createReservation(request));
    }
}
