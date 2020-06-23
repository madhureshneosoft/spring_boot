package com.neosoft.spring_boot_poc.repo;

import com.neosoft.spring_boot_poc.model.UserDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserDetailRepo extends JpaRepository<UserDetail,Integer> {
    boolean existsByEmailId(String emailId);
    boolean existsByMobileNumber(String mobileNumber);
}
