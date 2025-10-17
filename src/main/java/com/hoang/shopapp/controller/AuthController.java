package com.hoang.shopapp.controller;

import com.hoang.shopapp.dto.LoginRequest;
import com.hoang.shopapp.dto.RegisterRequest;
import com.hoang.shopapp.dto.RegisterResponse;
import com.hoang.shopapp.exception.customException;
import com.hoang.shopapp.service.AuthService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@Controller
public class AuthController {
    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/login")
    public String login(LoginRequest request) {
//        try {
//            return ResponseEntity.ok(authService.login(request));
//        } catch (customException e) {
//            return ResponseEntity.badRequest().body(Map.of(
//                    "field", e.getField(),
//                    "message", e.getMessage()
//            ));
//        }
        return "login";
    }


    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody RegisterRequest request) {
        try {
            RegisterResponse response = authService.register(request);
            return ResponseEntity.status(HttpStatus.CREATED).body(response);
        } catch (customException e) {
            // Trả lỗi cụ thể cho FE
            return ResponseEntity.badRequest()
                    .body(Map.of(
                            "field", e.getField(),
                            "message", e.getMessage()
                    ));
        }
    }
}
