package com.project.nutrisq.controller.dto;

import lombok.Builder;
import lombok.Getter;

import java.util.Date;

@Getter
@Builder
public class UserSpecificsDto {
    private String firstname;
    private String lastname;
    private Date born;
    private Double height;
    private Double weight;
    private String sex;
}