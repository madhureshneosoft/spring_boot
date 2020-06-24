package com.neosoft.spring_boot_poc.model.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserDetailRequest {

    private String firstName;

    private String lastName;

    private String mobileNumber;

    private Date dateOfBirth;

    private String emailId;

    private String address;

    private int pincode;
}
