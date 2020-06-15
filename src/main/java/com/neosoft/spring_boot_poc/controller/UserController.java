package com.neosoft.spring_boot_poc.controller;

import com.neosoft.spring_boot_poc.model.SortByBirthDate;
import com.neosoft.spring_boot_poc.model.SortByJoiningDate;
import com.neosoft.spring_boot_poc.model.User;
import com.neosoft.spring_boot_poc.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static com.neosoft.spring_boot_poc.RegExpression.EMAIL_REGEXP;
import static com.neosoft.spring_boot_poc.RegExpression.MOBILE_REGEXP;

@RequestMapping("api/user")
@RestController
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("")
    public void addNewUser(@Valid @RequestBody User user){
        userService.addUser(user);
    }

    @GetMapping("")
    public List<User> getUsers(){
        return userService.selectAllActiveUsers();
    }

    @GetMapping("/getUser/{anything}")
    public Set<User> getUser(@PathVariable("anything")String anything){
        Set<User> userList = new HashSet<>();
        if(anything.matches("(\\d){6}")){
            userList.addAll(userService.selectAllUsersByPincode(Integer.parseInt(anything)));
        } else if(anything.matches(EMAIL_REGEXP.getRegExp())){
            userList.add(userService.selectByEmailId(anything));
        } else if(anything.matches(MOBILE_REGEXP.getRegExp())){
            userList.add(userService.selectByMobileNumber(anything));
        } else {
            userList.addAll(userService.selectAllUsersByFirstName(anything));
            userList.addAll(userService.selectAllUsersByLastName(anything));
        }

        return userList;
    }

    @GetMapping("/sortByBirthDate")
    public List<User> sortByBirthDate(){
        List<User> userList = userService.selectAllActiveUsers();
        userList.sort(new SortByBirthDate());
        return userList;
    }

    @GetMapping("/sortByJoinDate")
    public List<User> sortByJoinDate(){
        List<User> userList = userService.selectAllActiveUsers();
        userList.sort(new SortByJoiningDate());
        return userList;
    }

    @PutMapping("/{userId}")
    public void editUser(@PathVariable("userId") int id,@RequestBody User user){
        user.setId(id);
        userService.editUser(user);
    }

    @DeleteMapping("/softDelete/{userId}")
    public void softDeleteUser(@PathVariable("userId")int id){
        User user = userService.selectUser(id);
        user.setActive(false);
        userService.editUser(user);
    }

    @DeleteMapping("/hardDelete/{userId}")
    public void hardDeleteUser(@PathVariable("userId")int id){
        userService.deleteUser(id);
    }

}
