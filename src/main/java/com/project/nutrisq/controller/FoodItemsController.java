package com.project.nutrisq.controller;

import com.project.nutrisq.model.FoodItems;
import com.project.nutrisq.repository.FoodItemsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class FoodItemsController {

    private final FoodItemsRepository foodItemsRepository;

    @GetMapping("/foodItems")
    private List<FoodItems> getFoodItems() {
        return foodItemsRepository.findAll();
    }
}
