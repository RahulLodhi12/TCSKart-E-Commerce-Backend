package com.tcsKart.UserService.repository;

import com.tcsKart.UserService.bean.Admin;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AdminRepo extends JpaRepository<Admin,String> {
    Optional<Admin> findByUsername(String username);
}
