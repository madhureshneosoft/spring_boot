package com.neosoft.spring_boot_poc.service;

import com.neosoft.spring_boot_poc.repo.UserDetailRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserDetailServiceImpl implements UserDetailService {

    private final UserDetailRepo userDetailRepo;

    @Autowired
    public UserDetailServiceImpl(UserDetailRepo userDetailRepo) {
        this.userDetailRepo = userDetailRepo;
    }

    @Override
    public boolean checkForDuplicate(String emailId,String mobileNumber) {
        return userDetailRepo.existsByEmailId(emailId) && userDetailRepo.existsByMobileNumber(mobileNumber);
    }
}
