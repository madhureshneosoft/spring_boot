package com.neosoft.spring_boot_poc.service;

import com.neosoft.spring_boot_poc.exception.ApiError;
import com.neosoft.spring_boot_poc.exception.NoUserFoundException;
import com.neosoft.spring_boot_poc.model.User;
import com.neosoft.spring_boot_poc.model.UserProjectDetail;
import com.neosoft.spring_boot_poc.repo.UserRepo;
import com.neosoft.spring_boot_poc.repo.UserRepoImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Date;
import java.sql.SQLIntegrityConstraintViolationException;
import java.util.*;

@Service
@Transactional
public class UserServiceImpl implements UserService {

    private final UserRepo userRepo;
    private final UserRepoImpl userRepoImpl;

    @Autowired
    public UserServiceImpl(UserRepo userRepo, UserRepoImpl userRepoImpl) {
        this.userRepo = userRepo;
        this.userRepoImpl = userRepoImpl;
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
     *
     * @param field to be sorted upon
     * @return sorted list
     */
    @Override
    public List<User> selectAllUserSortBy(String field) {
        return userRepo.findAll(Sort.by(field).ascending());
    }

    /**
     * Method to return all active users in database
     *
     * @return list of active users
     */
    @Override
    public List<User> selectAllActiveUsers() {
        List<User> userList = userRepo.findAllByActiveTrue();
        if (userList.size() != 0) {
            return userList;
        } else {
            throw new NoUserFoundException("No Active User");
        }
    }

    /**
     * Method to edit User
     *
     * @param user to be added
     * @param id   of the user
     * @return edited user
     */
    @Override
    public User editUser(User user, int id) {
        List<UserProjectDetail> userProjectDetailList = selectUser(id).getUserProjectDetail();
        user.setId(id);
        user.getUserRole().setId(id);
        user.getUserEmploymentDetail().setId(id);
        user.getUserEducationDetail().setId(id);
        user.getUserDetail().setId(id);
        for (int i = 0; i < user.getUserProjectDetail().size(); i++) {
            user.getUserProjectDetail().get(i).setId(userProjectDetailList.get(i).getId());
        }
        return userRepo.save(user);
    }

    /**
     * Method to select user based on id
     *
     * @param id of the user to be selected
     * @return user
     */
    @Override
    public User selectUser(int id) {
        return userRepo.findById(id).orElse(null);
    }

    /**
     * Method to Hard delete user from database
     *
     * @param id of the user to be deleted
     */
    @Override
    public void deleteUser(int id) {
        Optional<User> user = userRepo.findById(id);
        user.ifPresent(userRepo::delete);
    }

    @Override
    public List<User> dynamicSearch(String string) {
        String[] split = string.split("[&]");
        StringBuilder query = new StringBuilder("SELECT u FROM user_tbl u where ");

        List<String> fields = new ArrayList<>();
        List<String> values = new ArrayList<>();

        for (String s : split) {
            String[] innerSplit = s.split("[=]");
            fields.add(innerSplit[0]);
            values.add(innerSplit[1]);
        }

        for (int i = 0; i < split.length; i++) {
            if (i == 0) {
                query.append("u.").append(fields.get(i)).append("='").append(values.get(i)).append("'");
            } else {
                query.append(" and u.").append(fields.get(i)).append("='").append(values.get(i)).append("'");
            }
        }
        System.out.println(query.toString());
        return new ArrayList<>(userRepoImpl.dynamicSearch(query.toString()));
    }
}
