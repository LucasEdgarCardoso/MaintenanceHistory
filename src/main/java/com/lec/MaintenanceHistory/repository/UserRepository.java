package com.lec.MaintenanceHistory.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;

import com.lec.MaintenanceHistory.model.Users;

public interface UserRepository extends JpaRepository<Users, Integer> {
    UserDetails findByLogin(String login);
}
