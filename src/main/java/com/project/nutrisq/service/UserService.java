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
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserService {

    private static final int PAGE_SIZE = 3;
    private final UserRepository userRepository;
    private final UserSpecificsRepository userSpecificsRepository;
    private RestTemplate restTemplate = new RestTemplate();

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
    public String editUser(UserDto.UserEdit user, String username) {
        User userEdited = userRepository.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("User not found"));

        Optional.ofNullable(user.getEmail()).ifPresent(userEdited::setEmail);
        Optional.ofNullable(user.getUsername()).ifPresent(userEdited::setUsername);
        Optional.ofNullable(user.getPassword())
                .ifPresent(password -> userEdited.setPassword(encoder.encode(password)));

        return "User edited successfully";
    }

    @Transactional
    public String editUserRole(UserDto.RoleEdit user, String username) {
        User userEdited = userRepository.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("User not found"));

        Optional.ofNullable(user.getRoles()).ifPresent(userEdited::setRoles);

        return "User roles edited successfully";
    }

    @Transactional
    public String editUserSpecifics(UserSpecificsDto userSpecificsDto, String username) {
        Optional<User> user = userRepository.findByUsername(username);
        if (user.isPresent()) {
            UserSpecifics userEdited = userSpecificsRepository.findByUsername(username)
                    .orElseThrow(() -> new RuntimeException("User not found"));

            Optional.ofNullable(userSpecificsDto.getFirstname()).ifPresent(userEdited::setFirstname);
            Optional.ofNullable(userSpecificsDto.getLastname()).ifPresent(userEdited::setLastname);
            Optional.ofNullable(userSpecificsDto.getBorn()).ifPresent(userEdited::setBorn);
            Optional.ofNullable(userSpecificsDto.getHeight()).ifPresent(userEdited::setHeight);
            Optional.ofNullable(userSpecificsDto.getWeight()).ifPresent(userEdited::setWeight);
            Optional.ofNullable(userSpecificsDto.getSex()).ifPresent(userEdited::setSex);

            return "User specifics updated successfully";
        } else {
            return "User " + username + " not found";
        }
    }

    @Transactional
    public String deleteUserByUsername(String username) {
        Optional<User> user = userRepository.findByUsername(username);
        if (user.isPresent()) {
            userRepository.deleteByUsername(username);
            return "User " + username + " has been deleted";
        } else {
            return "User " + username + " not found";
        }
    }

    // Get user's country code
    public String getUserCountry() {
        String country = restTemplate.getForObject("https://api.country.is", String.class);

        JSONObject jsonObject = new JSONObject(country);
        String countryCode = jsonObject.getString("country");

        return countryCode.toLowerCase();
    }
}
