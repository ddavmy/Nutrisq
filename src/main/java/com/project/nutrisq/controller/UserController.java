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

    @GetMapping("/users")
    public List<User> getAllUsers() {
        return userService.getAllUsers();
    }

    @PutMapping("/user/{username}")
    public User editUser(@RequestBody User user, @PathVariable("username") String username) { return userService.editUser(user, username); }

    @PutMapping("/role/{username}")
    @PreAuthorize("hasAuthority('admin')")
    public User editUserRole(@RequestBody User user, @PathVariable("username") String username) { return userService.editUserRole(user, username); }

    @PutMapping("userSpecifics/{username}")
    public UserSpecifics editUserSpecifics(@RequestBody UserSpecifics userSpecifics, @PathVariable("username") String username) { return userService.editUserSpecifics(userSpecifics, username); }

    @DeleteMapping("/user/{username}")
    public String deleteUser(@PathVariable("username") String username) { return userService.deleteUserByUsername(username); }
}
