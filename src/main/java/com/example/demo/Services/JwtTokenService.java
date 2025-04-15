package com.example.demo.Services;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.function.Function;

@Service
public class JwtTokenService {

    private final String SECRET_KEY = "my_secret_key";  // Altere para um segredo mais seguro em produção

    // Gera o token JWT
    public String generateToken(String username) {
        return Jwts.builder()
                .setSubject(username)
                .setIssuedAt(new Date())  // Data de criação do token
                .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60)) // Expira em 1 hora
                .signWith(SignatureAlgorithm.HS256, SECRET_KEY)  // Assinatura com algoritmo HMAC
                .compact();  // Gera o token compactado
    }

    // Extrai o nome de usuário do token
    public String extractUsername(String token) {
        return extractClaim(token, Claims::getSubject);  // Extrai o "subject" do token (username)
    }

    // Extrai qualquer claim do token (como o nome de usuário ou a data de expiração)
    public <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
        Claims claims = Jwts.parser()
                .setSigningKey(SECRET_KEY)  // Usa a chave secreta para validar o token
                .parseClaimsJws(token)  // Parseia o token JWT
                .getBody();  // Retorna o corpo do JWT
        return claimsResolver.apply(claims);  // Aplica a função para extrair o claim
    }

    // Valida se o token é válido
    public boolean validateToken(String token, UserDetails userDetails) {
        final String username = extractUsername(token);  // Extrai o nome de usuário do token
        return username.equals(userDetails.getUsername()) && !isTokenExpired(token);  // Verifica se o nome de usuário bate e se o token não expirou
    }

    // Verifica se o token expirou
    private boolean isTokenExpired(String token) {
        final Date expiration = extractClaim(token, Claims::getExpiration);  // Extrai a data de expiração do token
        return expiration.before(new Date());  // Verifica se a data de expiração é anterior ao momento atual
    }
}
