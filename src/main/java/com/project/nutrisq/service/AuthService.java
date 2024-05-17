package com.project.nutrisq.service;

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
        if (userSpecifics != null) {
            userSpecifics.setUser(user);
        }

        repository.save(user);

        return "User Added Successfully";
    }
}