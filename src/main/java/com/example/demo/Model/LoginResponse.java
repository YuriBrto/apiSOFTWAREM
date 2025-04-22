package com.example.demo.Model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LoginResponse {
    private String token;
    private String role;

    public LoginResponse() {
    }

    public LoginResponse(String token, String role) {
        this.token = token;
        this.role = role;
    }
}
