package com.project.nutrisq.repository;

import com.project.nutrisq.model.UserSpecifics;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserSpecificsRepository extends JpaRepository<UserSpecifics, Integer> {

}
