package com.neosoft.spring_boot_poc.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.OptBoolean;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.sql.Date;

@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "user_detail_tbl")
@Getter
@Setter
public class UserDetail {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private int id;

    @JsonBackReference
    @OneToOne
    private User user;

    @Column
    @NotNull
    @Pattern(regexp = "([a-zA-Z]){2,16}")
    private String firstName;

    @Column
    @NotNull
    @Pattern(regexp = "([a-zA-Z]){2,16}")
    private String lastName;

    @Column(unique = true)
    @NotNull
    @Pattern(regexp = "^[789](\\d){9}",message = "Invalid mobile number")
    private String mobileNumber;

    @Column
    @NotNull
    @JsonFormat(pattern = "yyyy-MM-dd", lenient = OptBoolean.FALSE)
    private Date dateOfBirth;

    @Column(unique = true)
    @NotNull
    @Pattern(regexp = "^([A-Za-z0-9])(([.])?[0-9a-z])*[@]([a-z])+([.]([a-z])+){1,3}",message = "Invalid Email-Id")
    private String emailId;

    @Column
    @NotNull
    private String address;

    @Column
    @Min(value = 111111, message = "Invalid pincode")
    @Max(value = 999999, message = "Invalid pincode")
    private int pincode;
}
