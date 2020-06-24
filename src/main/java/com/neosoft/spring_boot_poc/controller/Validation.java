package com.neosoft.spring_boot_poc.controller;

import com.google.gson.Gson;
import com.neosoft.spring_boot_poc.exception.ApiError;
import com.neosoft.spring_boot_poc.model.User;
import com.neosoft.spring_boot_poc.model.request.UserRequest;
import com.neosoft.spring_boot_poc.service.UserDetailService;
import com.neosoft.spring_boot_poc.service.UserEmploymentDetailService;
import com.neosoft.spring_boot_poc.service.UserService;
import com.neosoft.spring_boot_poc.util.Factory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import static com.neosoft.spring_boot_poc.constant.RegExpression.*;

@Component
public abstract class Validation {

    private final UserDetailService userDetailService;
    private final UserService userService;
    private final Gson gson;

    @Autowired
    public Validation(UserDetailService userDetailService, UserService userService, UserEmploymentDetailService userEmploymentDetailService) {
        this.userDetailService = userDetailService;
        this.userService = userService;
        gson = new Gson();
    }

    public void valid(int userId) throws ApiError {
        if (!userService.userExists(userId)) {
            throw new ApiError(HttpStatus.NO_CONTENT,"Invalid UserId",null);
        }
    }

    public boolean valid(UserRequest user) throws ApiError {
        List<String> errors = new ArrayList<>();
        if (duplicateValidator(user)) {
            errors.add("Duplicate Email and/or Mobile Number Found");
        }
        if (!emailValidator(user)) {
            errors.add("Invalid Email");
        }
        if (!mobileNumberValidator(user)) {
            errors.add("Invalid Mobile Number");
        }
        if (!passwordValidator(user)) {
            errors.add("Password must contain lower case, higher case, digit and special character and should be of length between 8 to 36");
        }
        if (!userNameValidator(user)) {
            errors.add("Invalid username, username should contain only lower case and digits");
        }
        if (!firstNameLastNameValidator(user)) {
            errors.add("Invalid First name or Last Name, should contain only lower case and/or upper case");
        }
        if (!pincodeValidator(user)) {
            errors.add("Invalid pincode, pincode should contain only digits and should be of length 6");
        }
        if (!marksValidator(user)) {
            errors.add("Invalid ssc %, hsc % and/or cgpa, should contain only digits and should be max 100 for ssc & hsc, 10 for cgpa");
        }
        if (!projectValidator(user)) {
            errors.add("If project is valid it should not have end date, if project is inactive it should not have end date before start date");
        }
        if(!dateValidator(user)){
            errors.add("Invalid Date");
        }
        if (errors.isEmpty()) {
            return true;
        } else {
            throw new ApiError(HttpStatus.BAD_REQUEST, "Data invalid", errors);
        }
    }

    protected ResponseEntity<Object> responseBuilder(User user) throws ApiError {
        return new ResponseEntity<>(gson.toJson(Factory.userToUserResponse(user)), HttpStatus.OK);
    }

    protected ResponseEntity<Object> responseBuilder(ApiError error) {
        return new ResponseEntity<>(gson.toJson(error), HttpStatus.BAD_REQUEST);
    }

    protected ResponseEntity<Object> responseBuilder(List<User> userList) {
        if(userList.size()!=0) {
            return new ResponseEntity<>(gson.toJson(Factory.userListToUserResponseList(userList)), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(gson.toJson(new ApiError(HttpStatus.NO_CONTENT,"No User Found",null)), HttpStatus.NO_CONTENT);
        }
    }

    public boolean duplicateValidator(UserRequest user) {
        return userDetailService.checkForDuplicate(user.getUserDetail().getEmailId(), user.getUserDetail().getMobileNumber())
                && userService.checkForDuplicate(user.getUserName());
    }

    public boolean emailValidator(UserRequest user) {
        return user.getUserDetail().getEmailId().matches(EMAIL_REGEXP.getRegExp());
    }

    public boolean mobileNumberValidator(UserRequest user) {
        return user.getUserDetail().getMobileNumber().matches(MOBILE_REGEXP.getRegExp());
    }

    public boolean passwordValidator(UserRequest user) {
        return user.getPassword().matches(PASSWORD_REGEXP.getRegExp());
    }

    public boolean userNameValidator(UserRequest user) {
        return user.getUserName().matches(USERNAME_REGEXP.getRegExp());
    }

    public boolean firstNameLastNameValidator(UserRequest user) {
        return user.getUserDetail().getFirstName().matches(NAME_REGEXP.getRegExp())
                && user.getUserDetail().getLastName().matches(NAME_REGEXP.getRegExp());
    }

    public boolean pincodeValidator(UserRequest user) {
        return String.valueOf(user.getUserDetail().getPincode()).matches(PINCODE_REGEXP.getRegExp());
    }

    public boolean marksValidator(UserRequest user) {
        return user.getUserEducationDetail().getSscPercentage() <= 100
                && user.getUserEducationDetail().getHscPercentage() <= 100
                && user.getUserEducationDetail().getCgpa() <= 10;
    }

    public boolean projectValidator(UserRequest user) {
        return user.getUserProjectDetail().stream().allMatch(userProjectDetail -> (!userProjectDetail.isActive()
                && userProjectDetail.getEndDate().after(userProjectDetail.getStartDate()))
                || (userProjectDetail.isActive() && null == userProjectDetail.getEndDate()));
    }

    public boolean dateValidator(UserRequest user) {
        boolean flag;
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        format.setLenient(false);
        try {
            format.parse(user.getUserDetail().getDateOfBirth().toString());
            flag = user.getUserProjectDetail().stream().allMatch((userProjectDetail -> {
                try {
                    if (userProjectDetail.isActive()) {
                        format.parse(userProjectDetail.getStartDate().toString());
                    } else {
                        format.parse(userProjectDetail.getStartDate().toString());
                        format.parse(userProjectDetail.getEndDate().toString());
                    }
                } catch (ParseException e) {
                    return false;
                }
                return true;
            }));
        } catch (ParseException e) {
            return false;
        }
        return flag ;
    }

}
