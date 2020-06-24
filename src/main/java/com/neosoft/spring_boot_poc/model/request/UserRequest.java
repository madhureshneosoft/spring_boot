package com.neosoft.spring_boot_poc.model.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserRequest {

    private String userName;

    private String password;

    private UserDetailRequest userDetail;

    private UserEducationDetailRequest userEducationDetail;

    private UserEmploymentDetailRequest userEmploymentDetail;

    private List<UserProjectDetailRequest> userProjectDetail;

    private UserRoleRequest userRole;

}
