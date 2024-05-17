package com.project.nutrisq.service;

import com.project.nutrisq.model.User;
import com.project.nutrisq.model.UserSpecifics;
import com.project.nutrisq.repository.UserRepository;
import com.project.nutrisq.repository.UserSpecificsRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

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
    public User editUser(User user) {
        User userEdited = userRepository.findById(user.getId()).orElseThrow();
        if (user.getEmail() != null) {
            userEdited.setEmail(user.getEmail());
        }
        if (user.getUsername() != null) {
            userEdited.setUsername(user.getUsername());
        }
        return userEdited;
    }

    @Transactional
    public User editUserRole(User user) {
        User userEdited = userRepository.findById(user.getId()).orElseThrow();
        if (user.getRoles() != null) {
            userEdited.setRoles(user.getRoles());
        }
        return userEdited;
    }

    @Transactional
    public UserSpecifics editUserSpecifics(UserSpecifics userSpecifics) {
        UserSpecifics userEdited = userSpecificsRepository.findById(userSpecifics.getId()).orElseThrow();

        Optional.ofNullable(userSpecifics.getFirstname()).ifPresent(userEdited::setFirstname);
        Optional.ofNullable(userSpecifics.getLastname()).ifPresent(userEdited::setLastname);
        Optional.ofNullable(userSpecifics.getBorn()).ifPresent(userEdited::setBorn);
        Optional.ofNullable(userSpecifics.getHeight()).ifPresent(userEdited::setHeight);
        Optional.ofNullable(userSpecifics.getWeight()).ifPresent(userEdited::setWeight);
        Optional.ofNullable(userSpecifics.getSex()).ifPresent(userEdited::setSex);

        return userEdited;
    }
}
