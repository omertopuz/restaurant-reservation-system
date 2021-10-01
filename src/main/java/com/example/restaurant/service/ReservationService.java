package com.example.restaurant.service;

import com.example.restaurant.exception.AlreadyBookedException;
import com.example.restaurant.exception.CapacityExceededException;
import com.example.restaurant.exception.EntityNotFoundException;
import com.example.restaurant.model.dto.ReservationRequest;
import com.example.restaurant.model.entity.Reservations;
import com.example.restaurant.model.entity.Restaurant;
import com.example.restaurant.model.entity.RestaurantTable;
import com.example.restaurant.repository.ReservationRepository;
import com.example.restaurant.repository.RestaurantRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalTime;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class ReservationService {

    private final RestaurantRepository restaurantRepository;
    private final ReservationRepository reservationRepository;

    public ReservationService(RestaurantRepository restaurantRepository, ReservationRepository reservationRepository) {
        this.restaurantRepository = restaurantRepository;
        this.reservationRepository = reservationRepository;
    }

    public Restaurant findRestaurant(long restaurantId){
        return findRestaurantChecked(restaurantId);
    }

    public Restaurant createRestaurant(Restaurant restaurant){
        return this.restaurantRepository.save(restaurant);
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public Restaurant updateRestaurant(Restaurant restaurant) {
        Restaurant entity = findRestaurant(restaurant.getId());

        if(restaurant.getName() != null && restaurant.getName().equals(entity.getName()))
            entity.setName(restaurant.getName());

        entity.setOpeningTime(restaurant.getOpeningTime());
        entity.setClosingTime(restaurant.getClosingTime());

        if(entity.getRestaurantTables() == null)
            entity.setRestaurantTables(new HashSet<>());

        Set<RestaurantTable> restaurantTableList = new HashSet<>(entity.getRestaurantTables());
        restaurantTableList.addAll(restaurant.getRestaurantTables());

        entity.setRestaurantTables(restaurantTableList);
        restaurantRepository.save(entity);
        return entity;
    }

    public List<RestaurantTable> getAvailableTablesInRestaurant(ReservationRequest request){
        return restaurantRepository.filterAvailableTablesInRestaurant(request.getRestaurantId(),request.getPartySize(),request.getStartDate(),request.getEndDate(), LocalTime.from(request.getStartDate()),LocalTime.from(request.getEndDate()),null);
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public Reservations createReservation(ReservationRequest request){
        RestaurantTable restaurantTable = restaurantRepository.getRestaurantTable(request.getRestaurantTableId(),request.getRestaurantId());
        if(restaurantTable == null)
            throw new EntityNotFoundException("Could not find Restaurant Table with restaurantId: " + request.getRestaurantId()+ " and table Id:" + request.getRestaurantTableId());

        if(restaurantTable.getCapacity() < request.getPartySize())
            throw new CapacityExceededException("Selected table has capacity of " + restaurantTable.getCapacity()+ ". But requested " + request.getPartySize());

        List<RestaurantTable> availableSlotList = restaurantRepository.filterAvailableTablesInRestaurant(request.getRestaurantId(),request.getPartySize(),request.getStartDate(),request.getEndDate(), LocalTime.from(request.getStartDate()),LocalTime.from(request.getEndDate()),request.getRestaurantTableId());
        if(availableSlotList == null || availableSlotList.size()==0)
            throw new AlreadyBookedException("Sorry! Restaurant "+ request.getRestaurantId() + " has already been booked ");
        else {
            Reservations reservation = new Reservations();
            reservation.setStartDate(request.getStartDate());
            reservation.setEndDate(request.getEndDate());
            reservation.setPartySize(request.getPartySize());
            reservation.setRestaurantTable(restaurantTable);
            return reservationRepository.save(reservation);
        }
    }


    private Restaurant findRestaurantChecked(long restaurantId) {
        return restaurantRepository.findById(restaurantId).orElseThrow(()->new EntityNotFoundException("Could not find Restaurant with id: " + restaurantId));
    }
}
