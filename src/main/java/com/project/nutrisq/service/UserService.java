package com.project.nutrisq.service;

import com.project.nutrisq.model.User;
import com.project.nutrisq.repository.UserRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

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
}
