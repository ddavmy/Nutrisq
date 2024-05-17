package com.project.nutrisq.repository;

import com.project.nutrisq.model.FoodItems;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FoodItemsRepository extends JpaRepository<FoodItems, Integer> {

}
