package com.neosoft.spring_boot_poc.util;

import com.neosoft.spring_boot_poc.model.*;
import com.neosoft.spring_boot_poc.model.request.UserProjectDetailRequest;
import com.neosoft.spring_boot_poc.model.request.UserRequest;
import com.neosoft.spring_boot_poc.model.response.*;
import org.springframework.stereotype.Component;

import java.sql.Date;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

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

            user.getUserProjectDetail().forEach(userProjectDetail -> userProjectDetailResponses.add(new UserProjectDetailResponse(userProjectDetail.getProjectName(),userProjectDetail.getProjectDetail(),userProjectDetail.isActive())));

        UserRoleDetailResponse userRoleDetailResponse = new UserRoleDetailResponse(user.getUserRole().getRole());
        return new UserResponse(user.getUserName(),userDetailResponse,userEducationDetailResponse,userEmploymentDetailResponse,userProjectDetailResponses,userRoleDetailResponse);
    }

    public static User userRequestToUser(UserRequest userRequest){
        List<UserProjectDetail> details = userRequestProjectListToUserProjectList(userRequest.getUserProjectDetail());
        User user = new User(0,
                userRequest.getUserName(),
                userRequest.getPassword(),
                true,
                new Date(new java.util.Date().getTime()),
                new Date(new java.util.Date().getTime()),
                new UserDetail(0,
                        null,
                        userRequest.getUserDetail().getFirstName(),
                        userRequest.getUserDetail().getLastName(),
                        userRequest.getUserDetail().getMobileNumber(),
                        userRequest.getUserDetail().getDateOfBirth(),
                        userRequest.getUserDetail().getEmailId(),
                        userRequest.getUserDetail().getAddress(),
                        userRequest.getUserDetail().getPincode()),
                new UserEducationDetail(0,
                        null,
                        userRequest.getUserEducationDetail().getSscPercentage(),
                        userRequest.getUserEducationDetail().getHscPercentage(),
                        userRequest.getUserEducationDetail().getSscBoardName(),
                        userRequest.getUserEducationDetail().getHscBoardName(),
                        userRequest.getUserEducationDetail().getCgpa(),
                        userRequest.getUserEducationDetail().getUniversityName()),
                new UserEmploymentDetail(0,
                        null,
                        25000,
                        userRequest.getUserDetail().getFirstName()+"."+userRequest.getUserDetail().getLastName()+"@neosoft.com",
                        "99786"+ (10000 + new Random().nextInt(99999 - 10000 + 1)),
                        userRequest.getUserEmploymentDetail().getDepartment(),
                        new Date(new java.util.Date().getTime()),
                        userRequest.getUserEmploymentDetail().getExperience()),
                details,
                new UserRole(0, userRequest.getUserRole().getRole(), null));
        user.getUserEmploymentDetail().setUser(user);
        user.getUserEducationDetail().setUser(user);
        user.getUserRole().setUser(user);
        user.getUserDetail().setUser(user);
        user.getUserProjectDetail().forEach(userProjectDetail -> userProjectDetail.setUser(user));
        return user;
    }

    private static List<UserProjectDetail> userRequestProjectListToUserProjectList(List<UserProjectDetailRequest> userProjectDetails) {
        List<UserProjectDetail> details = new ArrayList<>();
        for(UserProjectDetailRequest u : userProjectDetails){
            details.add(new UserProjectDetail(0,null,u.getProjectName(),u.getProjectDetail(),u.getProjectCompany(),u.isActive(),u.getStartDate(),u.getEndDate()));
        }
        return details;
    }
}
