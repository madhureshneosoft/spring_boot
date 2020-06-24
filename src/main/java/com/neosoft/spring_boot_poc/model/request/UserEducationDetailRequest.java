package com.neosoft.spring_boot_poc.model.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserEducationDetailRequest {

    private float sscPercentage;

    private float hscPercentage;

    private String sscBoardName;

    private String hscBoardName;

    private float cgpa;

    private String universityName;
}
