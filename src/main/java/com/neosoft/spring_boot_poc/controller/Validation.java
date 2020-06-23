package com.neosoft.spring_boot_poc.controller;

import com.neosoft.spring_boot_poc.exception.ApiError;
import com.neosoft.spring_boot_poc.model.User;
import com.neosoft.spring_boot_poc.service.UserDetailService;
import com.neosoft.spring_boot_poc.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.util.Collections;

@Component
public class Validation {

    private final UserService userService;
    private final UserDetailService userDetailService;

    @Autowired
    public Validation(UserService userService, UserDetailService userDetailService) {
        this.userService = userService;
        this.userDetailService = userDetailService;
    }

    public ResponseEntity<Object> addUser(User user){
        ApiError apiError;
        if(user.getUserDetail().getDateOfBirth().compareTo(user.getUserEmploymentDetail().getDateOfJoin())>0){
            if(!userDetailService.checkForDuplicate(user.getUserDetail().getEmailId(),user.getUserDetail().getMobileNumber())) {
                apiError = new ApiError(HttpStatus.ACCEPTED, "Data Inserted Successful", null);
                userService.addUser(user);
            } else {
                apiError = new ApiError(HttpStatus.BAD_REQUEST,"Duplicate Entry", Collections.singletonList("Email Id or Mobile Number"));
            }
        } else {
            apiError = new ApiError(HttpStatus.BAD_REQUEST,"Join Date cannot be before Birth Date", Collections.singletonList("Birth date should be less than Join date"));
        }
        return new ResponseEntity<>(apiError,apiError.getStatus());
    }

//    public ResponseEntity<Object> editUser(User user){
//
//    }
//
//    public ResponseEntity<Object> softDeleteUser(int id){
//
//    }
//
//    public ResponseEntity<Object> hardDeleteUser(int id){
//
//    }
//
//    public ResponseEntity<Object> selectAllUser(){
//
//    }
//
//    public ResponseEntity<Object> dynamicSearch(String string){
//
//    }
}
