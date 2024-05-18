package com.project.nutrisq.repository;

import com.project.nutrisq.model.UserSpecifics;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserSpecificsRepository extends JpaRepository<UserSpecifics, Integer> {

    Optional<UserSpecifics> findByUsername(String username);

    boolean existsByUsername(String username);
}
