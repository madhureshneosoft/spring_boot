package com.neosoft.spring_boot_poc.model.response;

import com.neosoft.spring_boot_poc.model.*;

import java.util.List;

public class UserResponse {

    private int id;

    private String userName;

    private UserDetailResponse userDetail;

    private UserEducationDetailResponse userEducationDetail;

    private UserEmploymentDetailResponse userEmploymentDetail;

    private List<UserProjectDetailResponse> userProjectDetail;

    private UserRole userRole;
}
