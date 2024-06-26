package com.project.nutrisq.model;

import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;

@Entity
@Data
public class DailyMeals {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(nullable = false)
    private int userId;

    @Column(nullable = false, columnDefinition = "DATE DEFAULT CURRENT_DATE")
    private Date date;

    @Column(nullable = false)
    private String mealType;
}
