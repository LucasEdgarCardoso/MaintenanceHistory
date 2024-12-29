package com.lec.MaintenanceHistory.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.lec.MaintenanceHistory.dto.AuthenticationDTO;
import com.lec.MaintenanceHistory.dto.LoginResponseDTO;
import com.lec.MaintenanceHistory.dto.RegisterDTO;
import com.lec.MaintenanceHistory.model.Users;
import com.lec.MaintenanceHistory.repository.UserRepository;
import com.lec.MaintenanceHistory.util.JwtUtil;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/auth")
public class AuthController {
    /*
     * @Autowired
     * private JwtUtil jwtUtil;
     */

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserRepository repository;

    @Autowired
    private JwtUtil jwtUtil;

    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody @Valid RegisterDTO registerDTO) {
        if (this.repository.findByLogin(registerDTO.login()) != null)
            return ResponseEntity.badRequest().build();

        String encryptedPassword = new BCryptPasswordEncoder().encode(registerDTO.password());
        Users newUser = new Users(registerDTO.login(), encryptedPassword, registerDTO.role());

        this.repository.save(newUser);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponseDTO> login(@RequestBody @Valid AuthenticationDTO infoRegister) {
        var usernamePassword = new UsernamePasswordAuthenticationToken(infoRegister.login(), infoRegister.password());
        var auth = this.authenticationManager.authenticate(usernamePassword);
        var token = jwtUtil.generateToken((Users) auth.getPrincipal());
        return ResponseEntity.ok(new LoginResponseDTO(token));
    }

}