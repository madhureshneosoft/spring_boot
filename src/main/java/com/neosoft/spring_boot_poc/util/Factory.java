package com.neosoft.spring_boot_poc.util;

import com.neosoft.spring_boot_poc.model.User;
import com.neosoft.spring_boot_poc.model.response.*;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class Factory {

    public static List<UserResponse> userListToUserResponseList(List<User> userList){
        List<UserResponse> userResponses = new ArrayList<>();
        userList.forEach(user -> userResponses.add(userToUserResponse(user)));
        return userResponses;
    }

    public static UserResponse userToUserResponse(User user){
        UserDetailResponse userDetailResponse = new UserDetailResponse(user.getUserDetail().getFirstName(),
                user.getUserDetail().getLastName(),
                user.getUserDetail().getMobileNumber(),
                user.getUserDetail().getDateOfBirth(),
                user.getUserDetail().getEmailId(),
                user.getUserDetail().getAddress(),
                user.getUserDetail().getPincode());

        UserEducationDetailResponse userEducationDetailResponse = new UserEducationDetailResponse(user.getUserEducationDetail().getSscPercentage(),
                user.getUserEducationDetail().getHscPercentage(),
                user.getUserEducationDetail().getCgpa());

        UserEmploymentDetailResponse userEmploymentDetailResponse = new UserEmploymentDetailResponse(user.getUserEmploymentDetail().getWorkEmail(),
                user.getUserEmploymentDetail().getWorkMobileNumber(),
                user.getUserEmploymentDetail().getDepartment());

        List<UserProjectDetailResponse> userProjectDetailResponses = new ArrayList<>();

            user.getUserProjectDetail().forEach(userProjectDetail -> {
                userProjectDetailResponses.add(new UserProjectDetailResponse(userProjectDetail.getProjectName(),userProjectDetail.getProjectDetail(),userProjectDetail.isActive()));
            });

        UserRoleDetailResponse userRoleDetailResponse = new UserRoleDetailResponse(user.getUserRole().getRole());
        return new UserResponse(user.getUserName(),userDetailResponse,userEducationDetailResponse,userEmploymentDetailResponse,userProjectDetailResponses,userRoleDetailResponse);
    }
}
