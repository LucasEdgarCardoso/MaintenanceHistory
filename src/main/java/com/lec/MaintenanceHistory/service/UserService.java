package com.lec.MaintenanceHistory.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.lec.MaintenanceHistory.model.Users;
import com.lec.MaintenanceHistory.repository.UserRepository;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    public Users registerUser(Users user) {
        if (userRepository.findById(user.getId()).isPresent()) {
            throw new IllegalArgumentException("User already exists");
        }
        user.setPassword(passwordEncoder.encode(user.getPassword())); // Criptografa a senha
        return userRepository.save(user);
    }

}
