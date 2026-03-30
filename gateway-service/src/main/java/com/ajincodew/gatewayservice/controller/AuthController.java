package com.ajincodew.gatewayservice.controller;

import com.ajincodew.gatewayservice.config.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

import java.util.Map;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    private final JwtUtil jwtUtil;

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