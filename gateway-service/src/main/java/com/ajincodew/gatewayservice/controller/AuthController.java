package com.ajincodew.gatewayservice.controller;

import com.ajincodew.gatewayservice.config.JwtUtil;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

import java.util.Map;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final JwtUtil jwtUtil;

    public AuthController(JwtUtil jwtUtil) {
        this.jwtUtil = jwtUtil;
    }

    /**
     * This endpoint is deprecated. Please use /auth/login or /auth/signup endpoints on account-service
     * through the gateway (they are routed automatically).
     * 
     * The gateway now routes /auth/login and /auth/signup to the account-service
     * where real user authentication with database is performed.
     */
    @PostMapping("/login")
    public Mono<ResponseEntity<Map<String, String>>> login(@RequestBody Map<String, String> credentials) {
        String username = credentials.get("username");
        String password = credentials.get("password");

        // Fallback for backward compatibility with hardcoded credentials
        if ("user".equals(username) && "password".equals(password)) {
            String token = jwtUtil.generateToken(username);
            return Mono.just(ResponseEntity.ok(Map.of("token", token)));
        } else {
            return Mono.just(ResponseEntity.status(401).body(Map.of("error", "Invalid credentials")));
        }
    }
}