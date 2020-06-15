package com.neosoft.spring_boot_poc.repo;

import com.neosoft.spring_boot_poc.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepo extends JpaRepository<User,Integer> {

    List<User> findAllByActiveTrue();
    List<User> findAllByFirstNameAndActiveTrue(String firstName);
    List<User> findAllByLastNameAndActiveTrue(String lastName);
    List<User> findAllByPincodeAndActiveTrue(int pincode);
    User findByEmailIdAndActiveTrue(String emailId);
    User findByMobileNumberAndActiveTrue(String mobileNumber);
}
