package com.example.demo.auth.service;

import com.example.demo.auth.dto.AuthResponse;
import com.example.demo.auth.dto.LoginRequest;
import com.example.demo.auth.dto.RegisterRequest;
import com.example.demo.auth.util.JwtUtil;
import com.example.demo.auth.util.PasswordHasher;
import com.example.demo.user.model.User;
import com.example.demo.user.store.UserStore;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class AuthService {

    private final UserStore userStore;
    private final PasswordHasher passwordHasher;
    private final JwtUtil jwtUtil;

    public AuthService(UserStore userStore, PasswordHasher passwordHasher, JwtUtil jwtUtil) {
        this.userStore = userStore;
        this.passwordHasher = passwordHasher;
        this.jwtUtil = jwtUtil;
    }

    public AuthResponse register(RegisterRequest req) {
        if (userStore.existsByEmail(req.email())) {
            throw new IllegalArgumentException("Email already registered");
        }
        User user = new User(UUID.randomUUID().toString(), req.email(), passwordHasher.hash(req.password()));
        userStore.save(user);
        return new AuthResponse(jwtUtil.generate(user.email()));
    }

    public AuthResponse login(LoginRequest req) {
        User user = userStore.findByEmail(req.email())
                .orElseThrow(() -> new BadCredentialsException("Invalid credentials"));
        if (!passwordHasher.verify(user.passwordHash(), req.password())) {
            throw new BadCredentialsException("Invalid credentials");
        }
        return new AuthResponse(jwtUtil.generate(user.email()));
    }
}
