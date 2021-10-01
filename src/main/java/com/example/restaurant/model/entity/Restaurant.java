package com.example.restaurant.model.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalTime;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "restaurant")
public class Restaurant {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String name;

    @Column(name = "opening_time", nullable = true)
    private LocalTime openingTime;

    @Column(name = "closing_time", nullable = true)
    private LocalTime closingTime;

    @OneToMany(mappedBy = "restaurant",fetch = FetchType.EAGER,cascade = CascadeType.ALL)
    private Set<RestaurantTable> restaurantTables;

}
