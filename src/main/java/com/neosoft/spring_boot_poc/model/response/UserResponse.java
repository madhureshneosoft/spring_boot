package com.neosoft.spring_boot_poc.model.response;

import com.neosoft.spring_boot_poc.model.*;
import lombok.*;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class UserResponse {

    private String userName;

    private UserDetailResponse userDetail;

    private UserEducationDetailResponse userEducationDetail;

    private UserEmploymentDetailResponse userEmploymentDetail;

    private List<UserProjectDetailResponse> userProjectDetail;

    private UserRoleDetailResponse userRole;
}
