package com.ajincodew.accountservice.service;

import com.ajincodew.accountservice.UserRoleEnum;
import com.ajincodew.accountservice.dto.LoginRequest;
import com.ajincodew.accountservice.dto.SignupRequest;
import com.ajincodew.accountservice.dto.AuthResponse;
import com.ajincodew.accountservice.mapper.UserMapper;
import com.ajincodew.accountservice.model.Role;
import com.ajincodew.accountservice.model.User;
import com.ajincodew.accountservice.repository.RoleRepository;
import com.ajincodew.accountservice.repository.UserRepository;
import com.ajincodew.accountservice.config.JwtUtil;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Service
@AllArgsConstructor
public class AuthService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtil jwtUtil;
    private final UserMapper userMapper;

    public AuthResponse login(LoginRequest request) throws Exception {
        User user = userRepository.findByUsername(request.getUsername())
                .orElseThrow(() -> new Exception("User not found"));

        if (!user.isEnabled()) {
            throw new Exception("User account is disabled");
        }

        if (!passwordEncoder.matches(request.getPassword(), user.getPassword())) {
            throw new Exception("Invalid credentials");
        }

        String token = jwtUtil.generateToken(user);

        // Manually create AuthResponse with token
        return new AuthResponse(token, user.getUsername(), user.getEmail());
    }

    public AuthResponse signup(SignupRequest request) throws Exception {
        if (userRepository.existsByUsername(request.getUsername())) {
            throw new Exception("Username already taken");
        }

        if (userRepository.existsByEmail(request.getEmail())) {
            throw new Exception("Email already registered");
        }

        // Get or create USER role
        Role userRole = roleRepository.findByName(UserRoleEnum.CLIENT.roleName())
                .orElseGet(() -> {
                    Role role = Role.builder().name(UserRoleEnum.CLIENT.roleName()).description("Client user").build();
                    return roleRepository.save(role);
                });

        Set<Role> roles = new HashSet<>();
        roles.add(userRole);

        User user = userMapper.signupRequestToUser(request);
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        user.setRoles(roles);

        user = userRepository.save(user);
        String token = jwtUtil.generateToken(user);
        return new AuthResponse(token, user.getUsername(), user.getEmail());
    }
}
