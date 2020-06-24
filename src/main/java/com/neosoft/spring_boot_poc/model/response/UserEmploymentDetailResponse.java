package com.neosoft.spring_boot_poc.model.response;

import lombok.*;

import java.sql.Date;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class UserEmploymentDetailResponse {

    private String workEmail;

    private String workMobileNumber;

    private String department;

}
