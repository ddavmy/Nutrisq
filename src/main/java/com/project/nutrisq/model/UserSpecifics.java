package com.project.nutrisq.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Entity
@Data
@Getter
@Setter
public class UserSpecifics {

    @Id
    @Column(name = "userId")
    private Integer id;

    private String firstname;

    private String lastname;

    @Column(nullable = false, updatable = false, columnDefinition = "DATE DEFAULT CURRENT_DATE")
    private Date born;

    private Double weight;

    private Double height;

    private String sex;

    @OneToOne
    @MapsId
    @JoinColumn(name = "userId")
    @JsonIgnore
    private User user;
}