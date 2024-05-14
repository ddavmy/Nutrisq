package com.project.nutrisq.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Entity
@Data
@NoArgsConstructor
public class UserInfoDetails {

    @Id
    private int userId;

    private String firstname;

    private String lastname;

    @Column(updatable = false, columnDefinition = "DATE DEFAULT CURRENT_DATE")
    private Date born;

    private double weight;

    private double height;

    private String sex;
}
