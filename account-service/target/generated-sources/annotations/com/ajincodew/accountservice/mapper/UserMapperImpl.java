package com.ajincodew.accountservice.mapper;

import com.ajincodew.accountservice.dto.AuthResponse;
import com.ajincodew.accountservice.dto.SignupRequest;
import com.ajincodew.accountservice.model.User;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2026-03-29T20:31:20+0100",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 21.0.10 (Microsoft)"
)
@Component
public class UserMapperImpl implements UserMapper {

    @Override
    public User signupRequestToUser(SignupRequest signupRequest) {
        if ( signupRequest == null ) {
            return null;
        }

        User.UserBuilder user = User.builder();

        user.username( signupRequest.getUsername() );
        user.password( signupRequest.getPassword() );
        user.email( signupRequest.getEmail() );

        user.enabled( true );

        return user.build();
    }

    @Override
    public AuthResponse userToAuthResponse(User user) {
        if ( user == null ) {
            return null;
        }

        AuthResponse.AuthResponseBuilder authResponse = AuthResponse.builder();

        authResponse.username( user.getUsername() );
        authResponse.email( user.getEmail() );

        return authResponse.build();
    }
}
