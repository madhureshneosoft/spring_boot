package com.neosoft.spring_boot_poc.controller;

import com.neosoft.spring_boot_poc.model.User;
import com.neosoft.spring_boot_poc.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.sql.SQLIntegrityConstraintViolationException;
import java.util.*;

@RequestMapping("/api/user")
@RestController
public class UserController {

    private final UserService userService;
    private final Validation validation;

    @Autowired
    public UserController(UserService userService, Validation validation) {
        this.userService = userService;
        this.validation = validation;
    }

    /**
     * Method to add user
     * @param user object
     * @return added user
     */
    @PostMapping()
    public User addUser(@Valid @RequestBody User user) {
        return userService.addUser(user);
    }

    @PostMapping("/trial")
    public ResponseEntity<Object> addUserNew(@Valid @RequestBody User user) {
        return validation.addUser(user);
    }

    /**
     * Method to get all active user
     * @return list of users
     */
    @GetMapping("")
    public List<User> getUsers(){
        return userService.selectAllActiveUsers();
    }

    /**
     * Dynamic search new
     * @param string anything
     * @return list of user
     */
    @GetMapping("/getUser/{string}")
    public Set<User> dynamic(@PathVariable("string")String string){
        return new HashSet<>(userService.dynamicSearch(string));
    }

    /**
     * Method to get all inactive user
     * @return list of user
     */
    @GetMapping({"/inactive","/false"})
    public Set<User> getInactiveUsers(){
        return new HashSet<>(userService.selectAllInactiveUsers());
    }

    /**
     * Dynamically sort anything
     * @param anything field name
     * @return  list of user
     */
    @GetMapping("/sortBy{anything}")
    public List<User> sortByAnything(@PathVariable("anything")String anything){
        return userService.selectAllUserSortBy(anything);
    }

    /**
     * Method to edit user
     * @param id int
     * @param user object in json
     * @return user edited
     */
    @PutMapping("/{userId}")
    public User editUser(@PathVariable("userId") int id, @RequestBody User user){
        return userService.editUser(user,id);
    }

    /**
     * Method to delete user
     * @param id int
     */
    @DeleteMapping("/softDelete/{userId}")
    public void softDeleteUser(@PathVariable("userId")int id){
        User user = userService.selectUser(id);
        user.setActive(false);
        userService.editUser(user,id);
    }

    /**
     * Method to hard delete user
     * @param id int
     */
    @DeleteMapping("/hardDelete/{userId}")
    public void hardDeleteUser(@PathVariable("userId")int id){
        userService.deleteUser(id);
    }

}
