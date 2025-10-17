package com.hoang.shopapp.impl;

import com.hoang.shopapp.dto.LoginRequest;
import com.hoang.shopapp.dto.LoginResponse;
import com.hoang.shopapp.dto.RegisterRequest;
import com.hoang.shopapp.dto.RegisterResponse;
import com.hoang.shopapp.entity.Role;
import com.hoang.shopapp.entity.User;
import com.hoang.shopapp.exception.customException;
import com.hoang.shopapp.repository.RoleRepository;
import com.hoang.shopapp.repository.UserRepository;
import com.hoang.shopapp.security.JwtUtil;
import com.hoang.shopapp.service.AuthService;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class AuthServiceImpl implements AuthService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtil jwtUtil;
    private final RoleRepository roleRepository;


    @Override
    public LoginResponse login(LoginRequest request) {
        User user = userRepository.findByUsername(request.getUsername())
                .orElseThrow(() -> new customException("username", "User not found!"));

        if (!passwordEncoder.matches(request.getPassword(), user.getPassword())) {
            throw new customException("password", "Invalid credentials");
        }

        String token = jwtUtil.generateToken(user.getUsername());
        return new LoginResponse(token);
    }


    @Override
    public RegisterResponse register(RegisterRequest request){
        if (userRepository.findByUsername(request.getUsername()).isPresent()) {
            throw new customException("username", "Username already exists");
        }

        if (userRepository.findByEmail(request.getEmail()).isPresent()) {
            throw new customException("email", "Email already exists");
        }

        User user = new User();
        user.setName(request.getName());
        user.setUsername(request.getUsername());
        user.setPassword(passwordEncoder.encode(request.getPassword()));    // Bcrypt
        user.setEmail(request.getEmail());
        user.setStatus(true);

        Role userRole = roleRepository.findByName("user")
                        .orElseThrow(() -> new RuntimeException("Default role not found!"));

        user.setRole(userRole);

        userRepository.save(user);

        return new RegisterResponse("User registered successfully");
    }
}
