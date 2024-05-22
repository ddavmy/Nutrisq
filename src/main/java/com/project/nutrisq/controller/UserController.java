package com.project.nutrisq.controller;

import com.project.nutrisq.controller.dto.UserDto;
import com.project.nutrisq.controller.dto.UserSpecificsDto;
import com.project.nutrisq.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.util.List;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @Target(ElementType.METHOD)
    @Retention(RetentionPolicy.RUNTIME)
    @PreAuthorize("#username == authentication.principal.username or hasAuthority('admin')")
    public @interface isOwnerOrAdmin {
    }

    @GetMapping("/users")
    @PreAuthorize("hasAuthority('admin')")
    public List<UserDto.GetUsers> getUsers(@RequestParam(required = false) Integer page) {
        int pageNumber = (page != null && page >= 0) ? page : 0;
        return userService.getUsers(pageNumber);
    }

    @GetMapping("userdata")
    public String getUserCountry() {
        return userService.getUserCountry();
    }

    @PutMapping("/user/{username}")
    @isOwnerOrAdmin
    public ResponseEntity<String> editUser(@RequestBody UserDto.UserEdit user, @PathVariable("username") String username) {
        String editedUserDto = userService.editUser(user, username);
        return ResponseEntity.status(HttpStatus.OK).body(editedUserDto);
    }

    @PutMapping("/role/{username}")
    @PreAuthorize("hasAuthority('admin')")
    public ResponseEntity<String> editUserRole(@RequestBody UserDto.RoleEdit role, @PathVariable("username") String username) {
        String editedUserDto = userService.editUserRole(role, username);
        return ResponseEntity.status(HttpStatus.OK).body(editedUserDto);
    }

    @PutMapping("/userSpecifics/{username}")
    @isOwnerOrAdmin
    public ResponseEntity<String> editUserSpecifics(@RequestBody UserSpecificsDto userSpecifics, @PathVariable("username") String username) {
        String updatedUserSpecifics = userService.editUserSpecifics(userSpecifics, username);
        return ResponseEntity.status(HttpStatus.OK).body(updatedUserSpecifics);
    }

    @DeleteMapping("/user/{username}")
    public ResponseEntity<String> deleteUser(@PathVariable("username") String username) {
        String deletedUser = userService.deleteUserByUsername(username);
        return ResponseEntity.status(HttpStatus.OK).body(deletedUser);
    }
}
