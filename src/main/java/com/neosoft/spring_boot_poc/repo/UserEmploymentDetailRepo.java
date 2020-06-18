package com.neosoft.spring_boot_poc.repo;

import com.neosoft.spring_boot_poc.model.UserEmploymentDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserEmploymentDetailRepo extends JpaRepository<UserEmploymentDetail,Integer> {
}
