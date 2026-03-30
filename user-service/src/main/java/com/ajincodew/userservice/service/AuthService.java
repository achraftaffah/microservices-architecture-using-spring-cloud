package com.ajincodew.userservice.service;

import com.ajincodew.userservice.config.JwtUtil;
import com.ajincodew.userservice.entity.UserEntity;
import com.ajincodew.userservice.exception.BusinessException;
import com.ajincodew.userservice.exception.ErrorCode;
import com.ajincodew.userservice.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final JwtUtil jwtUtil;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public String login(String username, String password) {
        // Find the user by username
        Optional<UserEntity> optionalUser = userRepository.findByUsername(username);
        if (optionalUser.isEmpty()) {
            throw new BusinessException(
                    ErrorCode.USER_NOT_FOUND.getCode(),
                    ErrorCode.USER_NOT_FOUND.getMessage()
            );
        }

        UserEntity user = optionalUser.get();

        // Validate password
        if (!passwordEncoder.matches(password, user.getHashedPassword())) {
            throw new BusinessException(
                    ErrorCode.INVALID_CREDENTIALS.getCode(),
                    ErrorCode.INVALID_CREDENTIALS.getMessage()
            );
        }

        // Generate JWT token
        return jwtUtil.generateToken(user.getUsername());
    }

    public String signup(String username, String password) {
        // Check if user already exists
        Optional<UserEntity> optionalUser = userRepository.findByUsername(username);
        if (optionalUser.isPresent()) {
            throw new BusinessException(
                    ErrorCode.USER_ALREADY_EXISTS.getCode(),
                    ErrorCode.USER_ALREADY_EXISTS.getMessage()
            );
        }

        // Hash the password
        String hashedPassword = passwordEncoder.encode(password);

        // Create and save the user
        UserEntity user = UserEntity.builder()
                .username(username)
                .hashedPassword(hashedPassword)
                .build();
        UserEntity savedUser = userRepository.save(user);

        // Generate JWT token
        return jwtUtil.generateToken(savedUser.getUsername());
    }
}