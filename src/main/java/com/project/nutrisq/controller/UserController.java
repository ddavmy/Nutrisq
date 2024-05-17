package com.project.nutrisq.controller;

import com.project.nutrisq.model.User;
import com.project.nutrisq.model.UserSpecifics;
import com.project.nutrisq.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @GetMapping()
    public List<User> getAllUsers() {
        return userService.getAllUsers();
    }

    @PutMapping("/user")
    public User editUser(@RequestBody User user) {
        return userService.editUser(user);
    }

    @PutMapping("/role")
    @PreAuthorize("hasAuthority('admin')")
    public User editUserRole(@RequestBody User user) {
        return userService.editUserRole(user);
    }

    @PutMapping("userSpecifics")
    public UserSpecifics editUserSpecifics(@RequestBody UserSpecifics userSpecifics) { return userService.editUserSpecifics(userSpecifics); }
}
