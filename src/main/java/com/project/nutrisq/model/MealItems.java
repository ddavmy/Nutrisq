package com.project.nutrisq.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
public class MealItems {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(nullable = false)
    private int dailyMealId;

    @Column(nullable = false)
    private int foodItemId;

    @Column(nullable = false, columnDefinition = "INT(1)")
    private int quantity;
}
