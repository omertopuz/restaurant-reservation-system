package com.example.restaurant.repository;

import com.example.restaurant.model.entity.Reservations;
import com.example.restaurant.model.entity.Restaurant;
import com.example.restaurant.model.entity.RestaurantTable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

public interface RestaurantRepository extends JpaRepository<Restaurant,Long> {

    /**
     * Declare @partySize AS INT
     * Declare @restaurantId AS INT
     * Declare @startDate AS Date
     * Declare @endDate AS Date
     *
     * SELECT * FROM RESTAURANT r
     * inner join RESTAURANT_TABLE  rt ON r.ID = rt.RESTAURANT_ID
     * where
     * r.ID = @restaurantId --ensure selected restaurant
     * AND rt.CAPACITY >= @partySize    -- ensure capacity
     * AND (r.OPENING_TIME IS NULL OR r.OPENING_TIME < cast(@startDate as time) )
     * AND (r.CLOSING_TIME IS NULL OR r.CLOSING_TIME > cast(@endDate as time)) -- ensure opening/closing times
     * AND NOT EXISTS ( select * from RESERVATIONS rs where rs.RESTAURANT_TABLE_ID = rt.ID AND rs.START_DATE < @endDate
     *                      AND rs.END_DATE > @startDate)
     * */
    @Query(" from RestaurantTable rt inner join fetch rt.restaurant r " +
            "where " +
            "r.id = :restaurantId " +
            "AND (:restaurantTableId IS NULL OR rt.id = :restaurantTableId )" +
            "AND rt.capacity >= :partySize "
            +"AND (r.openingTime IS NULL OR r.openingTime < :startTime) AND (r.closingTime IS NULL OR r.closingTime > :endTime) "
    +"AND NOT EXISTS ( from Reservations rs where rs.restaurantTable.id = rt.id AND rs.startDate < :endDate AND rs.endDate > :startDate )")
    List<RestaurantTable> filterAvailableTablesInRestaurant(@Param("restaurantId") Long restaurantId
                                                      ,@Param("partySize") int partySize
                                                      ,@Param("startDate") LocalDateTime startDate
                                                      ,@Param("endDate") LocalDateTime endDate
                                                      ,@Param("startTime") LocalTime startTime
                                                      ,@Param("endTime") LocalTime endTime
                                                      ,@Param("restaurantTableId") Long restaurantTableId
    );

    @Query( " from Reservations rs inner join fetch rs.restaurantTable rt inner join fetch rt.restaurant r where rs.id = :reservationsId " )
    Reservations getReservationsDetails(@Param("reservationsId") Long reservationsId);

    @Query( " from RestaurantTable rt inner join fetch rt.restaurant r where rt.id = :restaurantTableId AND r.id =:restaurantId " )
    RestaurantTable getRestaurantTable(@Param("restaurantTableId") Long restaurantTableId,@Param("restaurantId") Long restaurantId);

}
