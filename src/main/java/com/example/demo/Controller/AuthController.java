package com.example.demo.Controller;

import com.example.demo.Services.JwtTokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.Model.LoginRequest;
import com.example.demo.Model.LoginResponse;

@RestController
public class AuthController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtTokenService jwtTokenService;

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest loginRequest) {
        System.out.println("Recebendo requisição de login: " + loginRequest.getUsername());

        try {
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            loginRequest.getUsername(),
                            loginRequest.getPassword()
                    )
            );

            System.out.println("Autenticação realizada com sucesso!");


            String token = jwtTokenService.generateToken(loginRequest.getUsername());
            System.out.println("Token gerado: " + token);


            String role = authentication.getAuthorities().stream()
                    .findFirst()
                    .map(auth -> auth.getAuthority())
                    .orElse("UNKNOWN");

            return ResponseEntity.ok(new LoginResponse(token, role));

        } catch (Exception e) {
            System.out.println("Erro de autenticação: " + e.getMessage());
            return ResponseEntity.status(401).body("Credenciais inválidas!"); // Retorna status 401
        }
    }

}
