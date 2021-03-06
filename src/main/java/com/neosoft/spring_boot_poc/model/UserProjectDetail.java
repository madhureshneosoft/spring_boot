package com.neosoft.spring_boot_poc.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.OptBoolean;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.sql.Date;

@Entity(name = "user_project_tbl")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class UserProjectDetail {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private int id;

    @JsonBackReference
    @ManyToOne
    private User user;

    @NotNull
    private String projectName;

    @NotNull
    private String projectDetail;

    @NotNull
    private String projectCompany;

    @NotNull
    private boolean active;

    @NotNull
    @JsonFormat(pattern = "yyyy-MM-dd", lenient = OptBoolean.FALSE)
    private Date startDate;

    @JsonFormat(pattern = "yyyy-MM-dd", lenient = OptBoolean.FALSE)
    private Date endDate;
}
