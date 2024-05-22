package com.project.nutrisq.service;

import com.project.nutrisq.controller.dto.UserDto;
import com.project.nutrisq.controller.mapper.UserSpecificsMapper;
import com.project.nutrisq.model.entity.UserInfoDetails;
import com.project.nutrisq.model.UserSpecifics;
import com.project.nutrisq.model.User;
import com.project.nutrisq.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AuthService implements UserDetailsService {
  
    @Autowired
    private UserRepository repository;
  
    @Autowired
    private PasswordEncoder encoder; 
  
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        Optional<User> userDetail = repository.findByUsername(username);

        return userDetail.map(UserInfoDetails::new)
                .orElseThrow(() -> new UsernameNotFoundException("User not found " + username));
    }

    public String createUser(UserDto userDto) {
        User user = new User();
        user.setEmail(userDto.getEmail());
        user.setUsername(userDto.getUsername());

        if (userDto.getPassword() != null) {
            user.setPassword(encoder.encode(userDto.getPassword()));

            if (userDto.getUserSpecifics() != null) {
                UserSpecifics userSpecifics = UserSpecificsMapper.mapToUserSpecifics(userDto.getUserSpecifics());
                userSpecifics.setUsername(user.getUsername());
                user.setUserSpecifics(userSpecifics);
                userSpecifics.setUser(user);

                repository.save(user);

                return "User created successfully";
            }
        }
        return "Couldn't create user";
    }
}