package com.neosoft.spring_boot_poc.model.response;

import lombok.*;

import java.sql.Date;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class UserDetailResponse {

    private String firstName;

    private String lastName;

    private String mobileNumber;

    private Date dateOfBirth;

    private String emailId;

    private String address;

    private int pincode;

}
