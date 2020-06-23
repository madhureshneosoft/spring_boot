package com.neosoft.spring_boot_poc.service;

import org.springframework.stereotype.Service;

@Service
public interface UserDetailService {

    public boolean checkForDuplicate(String emailId,String mobileNumber);
}
