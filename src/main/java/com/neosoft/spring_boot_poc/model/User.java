package com.neosoft.spring_boot_poc.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.sql.Date;

@ToString
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "user_tbl")
@Getter
@Setter
@EqualsAndHashCode
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column
    private int id;

    @Column
    @NotNull
    @Size(min = 2, message = "First Name too short (at least 2 characters required)")
    private String firstName;

    @Column
    @NotNull
    @Size(min = 2, message = "Last Name too short (at least 2 characters required)")
    private String lastName;

    @Column(unique = true)
    @NotNull
    @Pattern(regexp = "^[789](\\d){9}",message = "Invalid mobile number")
    private String mobileNumber;

    @Column
    @NotNull
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT")
    private Date dateOfBirth;  //format : dd-mm-yyyy

    @Column
    @NotNull
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT")
    private Date dateOfJoin;  //format : dd-mm-yyyy

    @Column(unique = true)
    @NotNull
    @Pattern(regexp = "^([A-Za-z0-9])(([.])?[0-9a-z])*[@]([a-z])+([.]([a-z])+){1,3}",message = "Invalid Email-Id")
    private String emailId;

    @Column
    @NotNull
    private String address;

    @Column
    @NotNull
    private boolean active;

    @Column
    @Min(111111)
    @Max(999999)
    private int pincode;
}
