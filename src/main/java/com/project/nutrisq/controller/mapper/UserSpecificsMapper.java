package com.project.nutrisq.controller.mapper;

import com.project.nutrisq.controller.dto.UserSpecificsDto;
import com.project.nutrisq.model.UserSpecifics;

public class UserSpecificsMapper {

    public static UserSpecificsDto mapToUserSpecificsDto(UserSpecifics userSpecifics) {
        return UserSpecificsDto.builder()
                .firstname(userSpecifics.getFirstname())
                .lastname(userSpecifics.getLastname())
                .born(userSpecifics.getBorn())
                .height(userSpecifics.getHeight())
                .weight(userSpecifics.getWeight())
                .sex(userSpecifics.getSex())
                .build();
    }

    public static UserSpecifics mapToUserSpecifics(UserSpecificsDto userSpecificsDto) {
        UserSpecifics userSpecifics = new UserSpecifics();
        userSpecifics.setFirstname(userSpecificsDto.getFirstname());
        userSpecifics.setLastname(userSpecificsDto.getLastname());
        userSpecifics.setBorn(userSpecificsDto.getBorn());
        userSpecifics.setHeight(userSpecificsDto.getHeight());
        userSpecifics.setWeight(userSpecificsDto.getWeight());
        userSpecifics.setSex(userSpecificsDto.getSex());
        return userSpecifics;
    }
}