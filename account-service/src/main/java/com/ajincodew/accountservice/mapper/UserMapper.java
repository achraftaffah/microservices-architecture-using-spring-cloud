package com.ajincodew.accountservice.mapper;

import com.ajincodew.accountservice.dto.AuthResponse;
import com.ajincodew.accountservice.dto.SignupRequest;
import com.ajincodew.accountservice.model.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface UserMapper {

    @Mapping(target = "roles", ignore = true)
    @Mapping(target = "enabled", constant = "true")
    User signupRequestToUser(SignupRequest signupRequest);

    AuthResponse userToAuthResponse(User user);
}