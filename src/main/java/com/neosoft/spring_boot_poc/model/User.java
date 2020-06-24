package com.neosoft.spring_boot_poc.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.OptBoolean;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.sql.Date;
import java.util.List;

@Entity(name = "user_tbl")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(unique = true)
    @NotNull
    @Pattern(regexp = "([A-Za-z0-9]){4,12}", message = "invalid username")
    private String userName;

    @NotNull
    @Pattern(regexp = "^(?=.*?[A-Z])(?=.*?[a-z])(?=.*?[0-9])(?=.*?[#?!@$%^&*-]).{8,36}$",message = "invalid password")
    private String password;

    @NotNull
    private boolean active;

    @NotNull
    @JsonFormat(pattern = "yyyy-MM-dd", lenient = OptBoolean.FALSE)
    private Date createDate;

    @NotNull
    @JsonFormat(pattern = "yyyy-MM-dd", lenient = OptBoolean.FALSE)
    private Date updateDate;

    @JsonManagedReference
    @OneToOne(cascade = CascadeType.ALL)
    private UserDetail userDetail;

    @JsonManagedReference
    @OneToOne(cascade = CascadeType.ALL)
    private UserEducationDetail userEducationDetail;

    @JsonManagedReference
    @OneToOne(cascade = CascadeType.ALL)
    private UserEmploymentDetail userEmploymentDetail;

    @JsonManagedReference
    @OneToMany(cascade = CascadeType.ALL)
    private List<UserProjectDetail> userProjectDetail;

    @JsonManagedReference
    @OneToOne(cascade = CascadeType.ALL)
    private UserRole userRole;
}
