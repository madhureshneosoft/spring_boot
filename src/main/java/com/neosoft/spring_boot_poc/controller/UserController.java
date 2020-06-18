package com.neosoft.spring_boot_poc.controller;

import com.neosoft.spring_boot_poc.model.User;
import com.neosoft.spring_boot_poc.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static com.neosoft.spring_boot_poc.RegExpression.*;

@RequestMapping("/api/user")
@RestController
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping()
    public User addUser(@Valid @RequestBody User user){
        return userService.addUser(user);
    }

    @GetMapping("")
    public List<User> getUsers(){
        return userService.selectAllActiveUsers();
    }

    @GetMapping("/getUser/{anything}")
    public Set<User> getUser(@PathVariable("anything")String anything){
        Set<User> userList = new HashSet<>();
        if(anything.matches(PINCODE_REGEXP.getRegExp())){
            userList.addAll(userService.selectAllUsersByPincode(Integer.parseInt(anything)));
        } else if(anything.matches(EMAIL_REGEXP.getRegExp())){
            userList.add(userService.selectByEmailId(anything));
        } else if(anything.matches(MOBILE_REGEXP.getRegExp())){
            userList.add(userService.selectByMobileNumber(anything));
        } else if(anything.matches(DATE_REGEXP.getRegExp())){
            userList.addAll(userService.selectAllUsersByBirthDate(anything));
            userList.addAll(userService.selectAllUsersByJoinDate(anything));
        } else if(anything.matches("(\\d)+")){
            userList.add(userService.selectUser(Integer.parseInt(anything)));
        } else {
            userList.addAll(userService.selectAllUsersByFirstName(anything));
            userList.addAll(userService.selectAllUsersByLastName(anything));
        }
        return userList;
    }

    @GetMapping({"/inactive","/false"})
    public Set<User> getInactiveUsers(){
        return new HashSet<>(userService.selectAllInactiveUsers());
    }

    @GetMapping("/sortBy{anything}")
    public List<User> sortByAnything(@PathVariable("anything")String anything){
        return userService.selectAllUserSortBy(anything);
    }

    @PutMapping("/{userId}")
    public User editUser(@PathVariable("userId") int id, @RequestBody User user){
        return userService.editUser(user,id);
    }

    @DeleteMapping("/softDelete/{userId}")
    public void softDeleteUser(@PathVariable("userId")int id){
        User user = userService.selectUser(id);
        user.setActive(false);
        userService.editUser(user,id);
    }

    @DeleteMapping("/hardDelete/{userId}")
    public void hardDeleteUser(@PathVariable("userId")int id){
        userService.deleteUser(id);
    }

}
