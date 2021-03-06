package com.neosoft.spring_boot_poc.service;

import com.neosoft.spring_boot_poc.model.User;
import com.neosoft.spring_boot_poc.model.response.UserResponse;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;

@Service
public interface UserService {

    /**
     *  Method to add new user
     * @param user user to be added
     * @return new user added
     */
    User addUser(User user);

    boolean checkForDuplicate(String userName);

    boolean userExists(int id);

    int editUserNew(HashMap<String,Object> map, int id);

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
