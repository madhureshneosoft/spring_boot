package com.neosoft.spring_boot_poc.model.response;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class UserEducationDetailResponse {

    private float sscPercentage;

    private float hscPercentage;

    private float cgpa;

}
