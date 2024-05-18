package com.project.nutrisq.controller;

import com.project.nutrisq.controller.dto.UserDto;
import com.project.nutrisq.controller.dto.UserSpecificsDto;
import com.project.nutrisq.model.User;
import com.project.nutrisq.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @GetMapping("/users")
    public List<UserDto.GetUsers> getUsers(@RequestParam(required = false) Integer page) {
        int pageNumber = (page != null && page >= 0) ? page : 0;
        return userService.getUsers(pageNumber);
    }

    @PutMapping("/user/{username}")
    public UserDto.UserEdit editUser(@RequestBody UserDto.UserEdit user, @PathVariable("username") String username) {
        return userService.editUser(user, username);
    }

    @PutMapping("/role/{username}")
    @PreAuthorize("hasAuthority('admin')")
    public UserDto.RoleEdit editUserRole(@RequestBody UserDto.RoleEdit role, @PathVariable("username") String username) {
        return userService.editUserRole(role, username);
    }

    @PutMapping("/userSpecifics/{username}")
    public ResponseEntity<UserSpecificsDto> editUserSpecifics(@RequestBody UserSpecificsDto userSpecifics, @PathVariable("username") String username) {
        UserSpecificsDto updatedUserSpecifics = userService.editUserSpecifics(userSpecifics, username);
        return ResponseEntity.ok(updatedUserSpecifics);
    }

    @DeleteMapping("/user/{username}")
    public String deleteUser(@PathVariable("username") String username) {
        return userService.deleteUserByUsername(username);
    }
}
