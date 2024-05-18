package com.project.nutrisq.repository;

import com.project.nutrisq.model.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
    Optional<User> findByUsername(String username);

    void deleteByUsername(String username);

    @Query("SELECT u FROM User u LEFT JOIN FETCH u.userSpecifics")
    Page<User> findAllUsers(Pageable pageable);
}