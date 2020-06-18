package com.neosoft.spring_boot_poc.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity (name = "user_education_tbl")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class UserEducationDetail {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private int id;

    @JsonBackReference
    @OneToOne
    private User user;

    @NotNull
    private float sscPercentage;

    @NotNull
    private float hscPercentage;

    @NotNull
    private String sscBoardName;

    @NotNull
    private String hscBoardName;

    @NotNull
    private float cgpa;

    @NotNull
    private String universityName;

}

