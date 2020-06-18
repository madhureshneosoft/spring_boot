package com.neosoft.spring_boot_poc.service;

import com.neosoft.spring_boot_poc.model.User;
import com.neosoft.spring_boot_poc.model.UserProjectDetail;
import com.neosoft.spring_boot_poc.repo.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Date;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class UserServiceImpl implements UserService {

    private final UserRepo userRepo;

    @Autowired
    public UserServiceImpl(UserRepo userRepo) {
        this.userRepo = userRepo;
    }


    @Override
    public User addUser(User user) {
        user.getUserDetail().setUser(user);
        user.getUserEducationDetail().setUser(user);
        user.getUserEmploymentDetail().setUser(user);
        user.getUserProjectDetail().forEach(userProjectDetail -> userProjectDetail.setUser(user));
        user.getUserRole().setUser(user);
        return userRepo.save(user);
    }

    @Override
    public List<User> selectAllInactiveUsers() {
        return userRepo.findAllByActiveFalse();
    }

    /**
     * Method to dynamically sort the output
     * @param field to be sorted upon
     * @return sorted list
     */
    @Override
    public List<User> selectAllUserSortBy(String field) {
        return userRepo.findAll(Sort.by(field).ascending());
    }

    @Override
    public List<User> selectAllUsersByPincode(int pincode) {
        return userRepo.findAllByUserDetailPincodeAndActiveTrue(pincode);
    }

    @Override
    public List<User> selectAllUsersByFirstName(String firstName) {
        return userRepo.findAllByUserDetailFirstNameAndActiveTrue(firstName);
    }

    @Override
    public List<User> selectAllUsersByLastName(String lastName) {
        return userRepo.findAllByUserDetailLastNameAndActiveTrue(lastName);
    }

    @Override
    public List<User> selectAllUsersByBirthDate(String birthDate) {
        return userRepo.findAllByUserDetailDateOfBirthAndActiveTrue(Date.valueOf(birthDate));
    }

    @Override
    public List<User> selectAllUsersByJoinDate(String joinDate) {
        return userRepo.findAllByUserEmploymentDetailDateOfJoinAndActiveTrue(Date.valueOf(joinDate));
    }

    @Override
    public User selectByEmailId(String emailId) {
        return userRepo.findByUserDetailEmailIdAndActiveTrue(emailId);
    }

    @Override
    public User selectByMobileNumber(String mobileNumber) {
        return userRepo.findByUserDetailMobileNumberAndActiveTrue(mobileNumber);
    }

    /**
     *  Method to return all active users in database
     * @return list of active users
     *
     */
    @Override
    public List<User> selectAllActiveUsers() {
        return userRepo.findAllByActiveTrue();
    }

    /**
     *  Method to edit User
     * @param user to be added
     * @param id of the user
     * @return edited user
     */
    @Override
    public User editUser(User user,int id) {
        List<UserProjectDetail> userProjectDetailList = selectUser(id).getUserProjectDetail();
        user.setId(id);
        user.getUserRole().setId(id);
        user.getUserEmploymentDetail().setId(id);
        user.getUserEducationDetail().setId(id);
        user.getUserDetail().setId(id);
        for(int i=0;i<user.getUserProjectDetail().size();i++){
            user.getUserProjectDetail().get(i).setId(userProjectDetailList.get(i).getId());
        }
        return userRepo.save(user);
    }

    /**
     *  Method to select user based on id
     * @param id of the user to be selected
     * @return user
     */
    @Override
    public User selectUser(int id) {
        return userRepo.findById(id).orElse(null);
    }

    /**
     * Method to Hard delete user from database
     * @param id of the user to be deleted
     */
    @Override
    public void deleteUser(int id) {
        Optional<User> user = userRepo.findById(id);
        user.ifPresent(userRepo::delete);
    }

    /*    @Override
    public List<User> selectAllUsers() {
       return userRepo.findAll();
    }/*
//

     */
}
