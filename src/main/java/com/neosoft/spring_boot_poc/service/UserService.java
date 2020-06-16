package com.neosoft.spring_boot_poc.service;

import com.neosoft.spring_boot_poc.model.User;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface UserService {

    User addUser(User user);
//    List<User> selectAllUsers(); //for selecting active and Inactive users current not in use
    List<User> selectAllUsersByPincode(int pincode);
    List<User> selectAllUsersByFirstName(String firstName);
    List<User> selectAllUsersByLastName(String lastName);
    List<User> selectAllUsersByBirthDate(String birthDate);
    List<User> selectAllUsersByJoinDate(String joinDate);
    List<User> selectAllActiveUsers();
    User selectByEmailId(String emailId);
    User selectByMobileNumber(String mobileNumber);
    User editUser(User user);
    User selectUser(int id);
    void deleteUser(int id);
}
