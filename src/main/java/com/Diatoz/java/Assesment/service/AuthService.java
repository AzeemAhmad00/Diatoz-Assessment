package com.Diatoz.java.Assesment.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.Diatoz.java.Assesment.dto.AuthResponse;
import com.Diatoz.java.Assesment.dto.LoginRequest;
import com.Diatoz.java.Assesment.entity.User;
import com.Diatoz.java.Assesment.repository.UserRepository;
import com.Diatoz.java.Assesment.security.JWTUtil;

@Service
public class AuthService {

    @Autowired private AuthenticationManager authenticationManager;
    @Autowired private JWTUtil jwtUtil;
    @Autowired private UserRepository userRepository;
    @Autowired private PasswordEncoder passwordEncoder;

    public AuthResponse login(LoginRequest request) {
        Authentication auth = authenticationManager.authenticate(
            new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword())
        );
        String token = jwtUtil.generateToken((org.springframework.security.core.userdetails.User) auth.getPrincipal());
        return new AuthResponse(token);
    }

    public void register(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userRepository.save(user);
    }
}
