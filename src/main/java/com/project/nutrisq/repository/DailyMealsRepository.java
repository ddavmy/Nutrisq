package com.project.nutrisq.repository;

import com.project.nutrisq.model.DailyMeals;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DailyMealsRepository extends JpaRepository<DailyMeals, Integer> {

}
