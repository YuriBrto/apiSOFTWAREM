package com.example.demo.Controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.Model.LoginRequest;
import com.example.demo.Model.LoginResponse;

@RestController
public class AuthController {

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login(@RequestBody LoginRequest loginRequest) {
        // Aqui você pode validar as credenciais do usuário
        if ("admin".equals(loginRequest.getUsername()) && "password".equals(loginRequest.getPassword())) {
            // Gerar um token JWT
            String token = "fake-jwt-token";  // Substitua com a lógica de token JWT real
            return ResponseEntity.ok(new LoginResponse(token));
        }
        return ResponseEntity.status(401).build();  // Unauthorized
    }
}
