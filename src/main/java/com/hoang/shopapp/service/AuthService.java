package com.hoang.shopapp.service;

import com.hoang.shopapp.dto.LoginRequest;
import com.hoang.shopapp.dto.LoginResponse;
import com.hoang.shopapp.dto.RegisterRequest;
import com.hoang.shopapp.dto.RegisterResponse;

public interface AuthService {
    LoginResponse login(LoginRequest request);
    RegisterResponse register(RegisterRequest request);
}
