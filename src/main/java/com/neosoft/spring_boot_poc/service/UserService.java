package com.neosoft.spring_boot_poc.service;

import com.neosoft.spring_boot_poc.model.User;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface UserService {

    /**
     *  Method to add new user
     * @param user user to be added
     * @return new user added
     */
    User addUser(User user);

    /**
     * Method to select all active user
     * @return list of all active user
     */
    List<User> selectAllActiveUsers();

    /**
     * Method to select all inactive user
     * @return list of all inactive user
     */
    List<User> selectAllInactiveUsers();

    /**
     * Method to Dynamically Sort based on fields
     * @param field to be sorted
     * @return sorted list
     */
    List<User> selectAllUserSortBy(String field);

    /**
     * Find Users based on pincode
     * @param pincode string
     * @return List of users
     */
    List<User> selectAllUsersByPincode(int pincode);

    /**
     * Find users based on first name
     * @param firstName string
     * @return list of users
     */
    List<User> selectAllUsersByFirstName(String firstName);

    /**
     * Find users based on last name
     * @param lastName string
     * @return list of users
     */
    List<User> selectAllUsersByLastName(String lastName);

    /**
     * find users based on birth date
     * @param birthDate string
     * @return  list of users
     */
    List<User> selectAllUsersByBirthDate(String birthDate);

    /**
     * find users based on join date
     * @param joinDate string
     * @return list of users
     */
    List<User> selectAllUsersByJoinDate(String joinDate);

    /**
     * find user based on email id
     * @param emailId string
     * @return user
     */
    User selectByEmailId(String emailId);

    /**
     * find user based on mobile number
     * @param mobileNumber string
     * @return user
     */
    User selectByMobileNumber(String mobileNumber);

    /**
     * Method to edit user
     * @param user object
     * @param id int
     * @return user
     */
    User editUser(User user, int id);

    /**
     * Select particular user based on id
     * @param id int
     * @return user
     */
    User selectUser(int id);

    /**
     * Delete user based on id
     * @param id int
     */
    void deleteUser(int id);

    /**
     * Dynamic searching
     * @param query string
     * @return list of user
     */
    List<User> dynamicSearch(String query);
}
