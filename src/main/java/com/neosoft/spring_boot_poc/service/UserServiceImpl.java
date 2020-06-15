package com.neosoft.spring_boot_poc.service;

import com.neosoft.spring_boot_poc.model.User;
import com.neosoft.spring_boot_poc.repo.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepo userRepo;

    @Autowired
    public UserServiceImpl(UserRepo userRepo) {
        this.userRepo = userRepo;
    }

    @Override
    public void addUser(User user) {
        userRepo.save(user);
    }

    @Override
    public User selectByEmailId(String emailId) {
        return userRepo.findByEmailIdAndActiveTrue(emailId);
    }

    @Override
    public User selectByMobileNumber(String mobileNumber) {
        return userRepo.findByMobileNumberAndActiveTrue(mobileNumber);
    }

    @Override
    public List<User> selectAllUsers() {
        return userRepo.findAll();
    }

    @Override
    public List<User> selectAllUsersByPincode(int pincode) {
        return userRepo.findAllByPincodeAndActiveTrue(pincode);
    }

    @Override
    public List<User> selectAllUsersByFirstName(String firstName) {
        return userRepo.findAllByFirstNameAndActiveTrue(firstName);
    }

    @Override
    public List<User> selectAllUsersByLastName(String lastName) {
        return userRepo.findAllByLastNameAndActiveTrue(lastName);
    }

    @Override
    public List<User> selectAllActiveUsers() {
        return userRepo.findAllByActiveTrue();
    }

    @Override
    public void editUser(User user) {
        userRepo.save(user);
    }

    @Override
    public User selectUser(int id) {
        return userRepo.findById(id).orElse(null);
    }

    @Override
    public void deleteUser(int id) {
        userRepo.deleteById(id);
    }
}
