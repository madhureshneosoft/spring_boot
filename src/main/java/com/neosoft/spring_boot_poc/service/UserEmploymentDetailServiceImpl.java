package com.neosoft.spring_boot_poc.service;

import com.neosoft.spring_boot_poc.repo.UserEmploymentDetailRepo;
import org.springframework.stereotype.Service;

@Service
public class UserEmploymentDetailServiceImpl implements UserEmploymentDetailService {

    private final UserEmploymentDetailRepo userEmploymentDetailRepo;

    public UserEmploymentDetailServiceImpl(UserEmploymentDetailRepo userEmploymentDetailRepo) {
        this.userEmploymentDetailRepo = userEmploymentDetailRepo;
    }

    @Override
    public boolean checkForDuplicate(String emailId, String mobileNumber) {
        return userEmploymentDetailRepo.existsByWorkEmail(emailId) && userEmploymentDetailRepo.existsByWorkMobileNumber(mobileNumber);
    }
}
