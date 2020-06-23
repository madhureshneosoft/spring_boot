package com.neosoft.spring_boot_poc.repo;

import com.neosoft.spring_boot_poc.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.sql.Date;
import java.util.List;

@Repository
public interface UserRepo extends JpaRepository<User,Integer> {

    /**
     * Overriding default findAll query
     * @return list of user
     */
    @Query("select u from user_tbl u where u.active=true")
    List<User> findAll();

    /**
     * Finds all active users in database
     * @return all the Active users
     */
    List<User> findAllByActiveTrue();

    /**
     * Finds all inactive user in database
     * @return all Inactive users
     */
    List<User> findAllByActiveFalse();

    boolean existsByUserName(String userName);

    /**
     * Finds all active users in database using first name
     * @return all users for given name
     */
    List<User> findAllByUserDetailFirstNameAndActiveTrue(String firstName);

    /**
     * Finds all active users in database using last name
     * @return all users for given last name
     */
    List<User> findAllByUserDetailLastNameAndActiveTrue(String lastName);

    /**
     * Finds all active users in database using pincode
     * @return all users for given name
     */
    List<User> findAllByUserDetailPincodeAndActiveTrue(int pincode);

    /**
     * Finds all active users in database using email
     * @return all users for given email
     */
    User findByUserDetailEmailIdAndActiveTrue(String email);

    /**
     * Finds all active users in database using date of birth
     * @return all users for given date of birth
     */
    List<User> findAllByUserDetailDateOfBirthAndActiveTrue(Date dateOfBirth);

    /**
     * Finds all active users in database using date of join
     * @return all users for given date of join
     */
    List<User> findAllByUserEmploymentDetailDateOfJoinAndActiveTrue(Date dateOfBirth);

    /**
     * Finds user in database using mobile number
     * @return user
     */
    User findByUserDetailMobileNumberAndActiveTrue(String mobile);
}
