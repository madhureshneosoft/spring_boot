package com.neosoft.spring_boot_poc.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
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
    private String workEmail;

    @Column(unique = true)
    @NotNull
    private String workMobileNumber;

    @NotNull
    private String department;

    @NotNull
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "Europe/Madrid")
    private Date dateOfJoin;

    @NotNull
    private Byte experience;
}
