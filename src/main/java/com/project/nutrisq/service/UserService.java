package com.project.nutrisq.service;

import com.project.nutrisq.model.User;
import com.project.nutrisq.model.UserSpecifics;
import com.project.nutrisq.repository.UserRepository;
import com.project.nutrisq.repository.UserSpecificsRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final UserSpecificsRepository userSpecificsRepository;

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @Transactional
    public User editUser(User user, String username) {
        User userEdited = userRepository.findByUsername(username).orElseThrow(() -> new RuntimeException("User not found"));

        if (user.getEmail() != null) {
            userEdited.setEmail(user.getEmail());
        }
        if (user.getUsername() != null) {
            String newUsername = user.getUsername();
            userEdited.setUsername(newUsername);
        }

        return userEdited;
    }

    @Transactional
    public User editUserRole(User user, String username) {
        User userEdited = userRepository.findByUsername(username).orElseThrow(() -> new RuntimeException("User not found"));
        if (user.getRoles() != null) {
            userEdited.setRoles(user.getRoles());
        }
        return userEdited;
    }

    @Transactional
    public UserSpecifics editUserSpecifics(UserSpecifics userSpecifics, String username) {
        UserSpecifics userEdited = userSpecificsRepository.findByUsername(username).orElseThrow(() -> new RuntimeException("User not found"));

        Optional.ofNullable(userSpecifics.getFirstname()).ifPresent(userEdited::setFirstname);
        Optional.ofNullable(userSpecifics.getLastname()).ifPresent(userEdited::setLastname);
        Optional.ofNullable(userSpecifics.getBorn()).ifPresent(userEdited::setBorn);
        Optional.ofNullable(userSpecifics.getHeight()).ifPresent(userEdited::setHeight);
        Optional.ofNullable(userSpecifics.getWeight()).ifPresent(userEdited::setWeight);
        Optional.ofNullable(userSpecifics.getSex()).ifPresent(userEdited::setSex);

        return userEdited;
    }

    @Transactional
    public String deleteUserByUsername(String username) {
        if (userSpecificsRepository.existsByUsername(username)) {
            userRepository.deleteByUsername(username);
            return "User " + username + " has been deleted";
        } else {
            throw new RuntimeException("User not found");
        }
    }
}
