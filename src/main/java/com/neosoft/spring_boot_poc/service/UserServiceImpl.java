package com.neosoft.spring_boot_poc.service;

import com.neosoft.spring_boot_poc.model.User;
import com.neosoft.spring_boot_poc.repo.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepo userRepo;

    @Autowired
    public UserServiceImpl(UserRepo userRepo) {
        this.userRepo = userRepo;
    }

    @Override
    public User addUser(User user) {
        return userRepo.save(user);
    }

    @Override
    public User selectByEmailId(String emailId) {
        return userRepo.findByEmailIdAndActiveTrue(emailId);
    }

    @Override
    public User selectByMobileNumber(String mobileNumber) {
        return userRepo.findByMobileNumberAndActiveTrue(mobileNumber);
    }

/*
  * Method to find All users including inactive and active (currently not in use)
  * for future use if required
*/

//    @Override
//    public List<User> selectAllUsers() {
//        return userRepo.findAll();
//    }

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
    public List<User> selectAllUsersByBirthDate(String birthDate) {
        return userRepo.findAllByDateOfBirthAndActiveTrue(Date.valueOf(birthDate));
    }

    @Override
    public List<User> selectAllUsersByJoinDate(String joinDate) {
        return userRepo.findAllByDateOfJoinAndActiveTrue(Date.valueOf(joinDate));
    }

    @Override
    public List<User> selectAllActiveUsers() {
        return userRepo.findAllByActiveTrue();
    }

    @Override
    public User editUser(User user) {
        return userRepo.save(user);
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
