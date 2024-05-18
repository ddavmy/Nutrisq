package com.project.nutrisq.controller.mapper;

import com.project.nutrisq.controller.dto.UserDto;
import com.project.nutrisq.controller.dto.UserSpecificsDto;
import com.project.nutrisq.model.User;
import lombok.NoArgsConstructor;

@NoArgsConstructor
public class UserMapper {

    public static UserDto mapToUserDto(User user) {
        UserSpecificsDto userSpecificsDto = null;
        if (user.getUserSpecifics() != null) {
            userSpecificsDto = UserSpecificsMapper.mapToUserSpecificsDto(user.getUserSpecifics());
        }

        return UserDto.builder()
                .email(user.getEmail())
                .username(user.getUsername())
                .password(user.getPassword())
                .userSpecifics(userSpecificsDto)
                .build();
    }
}