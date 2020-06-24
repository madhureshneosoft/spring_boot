package com.neosoft.spring_boot_poc.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.OptBoolean;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.sql.Date;

@Entity (name = "user_employment_tbl")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class UserEmploymentDetail {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private int id;

    @JsonBackReference
    @OneToOne
    private User user;

    @NotNull
    private long salary;

    @Column(unique = true)
    @NotNull
    @Pattern(regexp = "^([A-Za-z0-9])(([.])?[0-9a-z])*[@]([a-z])+([.]([a-z])+){1,3}",message = "Invalid Email-Id")
    private String workEmail;

    @Column(unique = true)
    @NotNull
    @Pattern(regexp = "^[789](\\d){9}",message = "Invalid mobile number")
    private String workMobileNumber;

    @NotNull
    private String department;

    @NotNull
    @JsonFormat(pattern = "yyyy-MM-dd", lenient = OptBoolean.FALSE)
    private Date dateOfJoin;

    @NotNull
    private Byte experience;
}
