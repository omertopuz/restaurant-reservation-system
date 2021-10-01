package com.example.restaurant.repository;

import com.example.restaurant.model.entity.Reservations;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReservationRepository extends JpaRepository<Reservations,Long> {



}
