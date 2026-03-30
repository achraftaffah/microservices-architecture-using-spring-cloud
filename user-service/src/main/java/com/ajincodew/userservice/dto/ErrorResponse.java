package com.ajincodew.userservice.dto;

public record ErrorResponse(String code, String message) implements ApiResponse {

}