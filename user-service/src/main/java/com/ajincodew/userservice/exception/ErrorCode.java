package com.ajincodew.userservice.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum ErrorCode {

    INVALID_CREDENTIALS("USER0001", "Invalid username or password"),
    USER_NOT_FOUND("USER0002", "User not found"),
    USER_ALREADY_EXISTS("USER0003", "User already exists");

    private final String code;
    private final String message;
}