package com.project.nutrisq.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
public class FoodItems {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private double calories;

    private  double proteins;

    private Double carbohydrates;

    private double fat;

    @Column(nullable = false)
    private double servingSize;

    @Column(nullable = false)
    private String servingUnit;
}
