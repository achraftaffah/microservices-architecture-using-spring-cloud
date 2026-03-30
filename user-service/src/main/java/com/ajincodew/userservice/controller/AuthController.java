package com.ajincodew.userservice.controller;

import com.ajincodew.userservice.dto.*;
import com.ajincodew.userservice.exception.BusinessException;
import com.ajincodew.userservice.service.AuthService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @PostMapping("/login")
    public ResponseEntity<ApiResponse> login(@Valid @RequestBody LoginRequest request) {
        try {
            String token = authService.login(request.username(), request.password());
            return ResponseEntity.ok(new AuthResponse(token));
        } catch (BusinessException ex) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body(new ErrorResponse(ex.getCode(), ex.getMessage()));
        }
    }

    @PostMapping("/signup")
    public ResponseEntity<ApiResponse> signup(@Valid @RequestBody SignupRequest request) {
        String token = authService.signup(request.username(), request.password());
        return ResponseEntity.ok(new AuthResponse(token));
    }
}