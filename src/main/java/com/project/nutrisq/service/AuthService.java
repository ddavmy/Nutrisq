package com.project.nutrisq.service;

import com.project.nutrisq.controller.dto.UserDto;
import com.project.nutrisq.controller.mapper.UserMapper;
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

    public String addUser(User user) {
        user.setPassword(encoder.encode(user.getPassword()));
        user.setRoles(user.getRoles());

        UserSpecifics userSpecifics = user.getUserSpecifics();
        if (userSpecifics == null) {
            userSpecifics = new UserSpecifics();
            userSpecifics.setUsername(user.getUsername());
            user.setUserSpecifics(userSpecifics);
        } else {
            userSpecifics.setUsername(user.getUsername());
            userSpecifics.setUser(user);
        }

        repository.save(user);

        return "User Added Successfully";
    }

    public UserDto createUser(UserDto userDto) {
        User user = new User();
        user.setEmail(userDto.getEmail());
        user.setUsername(userDto.getUsername());
        user.setPassword(encoder.encode(userDto.getPassword()));

        if (userDto.getUserSpecifics() != null) {
            UserSpecifics userSpecifics = UserSpecificsMapper.mapToUserSpecifics(userDto.getUserSpecifics());
            userSpecifics.setUsername(user.getUsername());
            user.setUserSpecifics(userSpecifics);
            userSpecifics.setUser(user);
        }

        User savedUser = repository.save(user);
        return UserMapper.mapToUserDto(savedUser);
    }
}