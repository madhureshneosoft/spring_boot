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
public class UserProjectDetailRequest {

    private String projectName;

    private String projectDetail;

    private String projectCompany;

    private boolean active;

    private Date startDate;

    private Date endDate;
}
