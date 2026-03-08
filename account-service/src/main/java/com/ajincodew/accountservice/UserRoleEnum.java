package com.ajincodew.accountservice;

public enum UserRoleEnum {
    ADMIN,
    CLIENT;

    public String roleName() {
        return "ROLE_" + this.name();
    }
}