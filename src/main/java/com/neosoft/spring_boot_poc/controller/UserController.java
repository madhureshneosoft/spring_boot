package com.neosoft.spring_boot_poc.controller;

import com.neosoft.spring_boot_poc.exception.ApiError;
import com.neosoft.spring_boot_poc.model.User;
import com.neosoft.spring_boot_poc.service.UserDetailService;
import com.neosoft.spring_boot_poc.service.UserEmploymentDetailService;
import com.neosoft.spring_boot_poc.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.*;

@RequestMapping("/api/user")
@RestController
public class UserController extends Validation {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService,
                          UserDetailService userDetailService,
                          UserEmploymentDetailService userEmploymentDetailService) {
        super(userDetailService, userService, userEmploymentDetailService);
        this.userService = userService;
    }

    /**
     * Method to add user
     *
     * @param user object
     * @return added user
     */
    @PostMapping()
    public ResponseEntity<Object> addUser(@Valid @RequestBody User user) throws ApiError {
        ResponseEntity<Object> responseEntity = null;
        try {
            if (valid(user)) {
                responseEntity = responseBuilder(userService.addUser(user));
            }
        } catch (ApiError e) {
            responseEntity = responseBuilder(e);
        }
        return responseEntity;
    }

    /**
     * Method to get all active user
     *
     * @return list of users
     */
    @GetMapping("")
    public ResponseEntity<Object> getUsers() {
        return responseBuilder(userService.selectAllActiveUsers());
    }

    /**
     * Dynamic search new
     *
     * @param string anything
     * @return list of user
     */
    @GetMapping("/getUser/{string}")
    public ResponseEntity<Object> dynamic(@PathVariable("string") String string) {
        return responseBuilder(userService.dynamicSearch(string));
    }

    /**
     * Method to get all inactive user
     *
     * @return list of user
     */
    @GetMapping({"/inactive", "/false"})
    public ResponseEntity<Object> getInactiveUsers() {
        return responseBuilder(userService.selectAllInactiveUsers());
    }

    /**
     * Dynamically sort anything
     *
     * @param anything field name
     * @return list of user
     */
    @GetMapping("/sortBy{anything}")
    public ResponseEntity<Object> sortByAnything(@PathVariable("anything") String anything) {
        return responseBuilder(userService.selectAllUserSortBy(anything));
    }

    /**
     * Method to edit user
     *
     * @param id   int
     * @param user object in json
     * @return user edited
     */
    @PutMapping("/{userId}")
    public User editUser(@PathVariable("userId") int id, @RequestBody User user) {
        return userService.editUser(user, id);
    }

    /**
     * Method to delete user
     *
     * @param id int
     */
    @DeleteMapping("/softDelete/{userId}")
    public ResponseEntity<Object> softDeleteUser(@PathVariable("userId") int id) {
        try {
            valid(id);
        } catch (ApiError e) {
            return responseBuilder(e);
        }
        User user = userService.selectUser(id);
        user.setActive(false);
        userService.editUser(user, id);
        return responseBuilder(user);
    }

    /**
     * Method to hard delete user
     *
     * @param id int
     */
    @DeleteMapping("/hardDelete/{userId}")
    public ResponseEntity<Object> hardDeleteUser(@PathVariable("userId") int id) {
        try {
            valid(id);
        } catch (ApiError e) {
            return responseBuilder(e);
        }
        userService.deleteUser(id);
        return new ResponseEntity<>("User Permanently deleted from Database", HttpStatus.OK);
    }

}
