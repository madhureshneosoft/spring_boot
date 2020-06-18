package com.neosoft.spring_boot_poc.repo;

import com.neosoft.spring_boot_poc.model.UserRole;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRoleRepo extends JpaRepository<UserRole,Integer> {
}
