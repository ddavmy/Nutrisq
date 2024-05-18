package com.project.nutrisq.service;

import com.project.nutrisq.controller.dto.UserDto;
import com.project.nutrisq.controller.dto.UserSpecificsDto;
import com.project.nutrisq.controller.mapper.UserSpecificsMapper;
import com.project.nutrisq.model.User;
import com.project.nutrisq.model.UserSpecifics;
import com.project.nutrisq.repository.UserRepository;
import com.project.nutrisq.repository.UserSpecificsRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserService {

    private static final int PAGE_SIZE = 3;
    private final UserRepository userRepository;
    private final UserSpecificsRepository userSpecificsRepository;

    @Autowired
    private PasswordEncoder encoder;

    public List<UserDto.GetUsers> getUsers(int page) {
        List<User> users = userRepository.findAllUsers(PageRequest.of(page, PAGE_SIZE)).getContent();
        return users.stream()
                .map(user -> {
                    UserSpecifics userSpecifics = userSpecificsRepository.findByUserId(user.getId())
                            .orElseThrow(() -> new RuntimeException("User specifics not found"));
                    UserSpecificsDto userSpecificsDto = UserSpecificsMapper.mapToUserSpecificsDto(userSpecifics);
                    return UserDto.GetUsers.builder()
                            .email(user.getEmail())
                            .username(user.getUsername())
                            .roles(user.getRoles())
                            .created(user.getCreated())
                            .userSpecifics(userSpecificsDto)
                            .build();
                })
                .collect(Collectors.toList());
    }

    @Transactional
    public UserDto.UserEdit editUser(UserDto.UserEdit user, String username) {
        User userEdited = userRepository.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("User not found"));

        Optional.ofNullable(user.getEmail()).ifPresent(userEdited::setEmail);
        Optional.ofNullable(user.getUsername()).ifPresent(userEdited::setUsername);
        Optional.ofNullable(user.getPassword())
                .ifPresent(password -> userEdited.setPassword(encoder.encode(password)));

        return new UserDto.UserEdit(
                userEdited.getEmail(),
                userEdited.getUsername(),
                userEdited.getPassword()
        );
    }

    @Transactional
    public UserDto.RoleEdit editUserRole(UserDto.RoleEdit user, String username) {
        User userToEdit = userRepository.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("User not found"));

        if (user.getRoles() != null && !user.getRoles().isEmpty()) {
            userToEdit.setRoles(user.getRoles());
        }
        userRepository.save(userToEdit);

        return new UserDto.RoleEdit(userToEdit.getRoles());
    }

    @Transactional
    public UserSpecificsDto editUserSpecifics(UserSpecificsDto userSpecificsDto, String username) {
        UserSpecifics userEdited = userSpecificsRepository.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("User not found"));

        Optional.ofNullable(userSpecificsDto.getFirstname()).ifPresent(userEdited::setFirstname);
        Optional.ofNullable(userSpecificsDto.getLastname()).ifPresent(userEdited::setLastname);
        Optional.ofNullable(userSpecificsDto.getBorn()).ifPresent(userEdited::setBorn);
        Optional.ofNullable(userSpecificsDto.getHeight()).ifPresent(userEdited::setHeight);
        Optional.ofNullable(userSpecificsDto.getWeight()).ifPresent(userEdited::setWeight);
        Optional.ofNullable(userSpecificsDto.getSex()).ifPresent(userEdited::setSex);

        return UserSpecificsMapper.mapToUserSpecificsDto(userEdited);
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
