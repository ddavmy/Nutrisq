package com.project.nutrisq.repository;

import com.project.nutrisq.model.MealItems;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MealItemsRepository extends JpaRepository<MealItems, Integer> {

}
