package com.example.demo.Services;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.util.Date;
import java.util.function.Function;

@Service
public class JwtTokenService {

    private final Key SECRET_KEY = Keys.secretKeyFor(SignatureAlgorithm.HS256);  // Corrigido para Key, gerando chave de 256 bits
    private static final Logger logger = LoggerFactory.getLogger(JwtTokenService.class);  // Logger para monitoramento

    // Gera o token JWT
    public String generateToken(String username) {
        logger.info("Gerando token para o usuário: {}", username);  // Log de geração de token

        return Jwts.builder()
                .setSubject(username)  // Definindo o "subject" como o username
                .setIssuedAt(new Date())  // Data de criação do token
                .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60)) // Expira em 1 hora
                .signWith(SECRET_KEY)  // Assinatura com chave gerada automaticamente
                .compact();  // Gera o token compactado
    }

    // Extrai o nome de usuário do token
    public String extractUsername(String token) {
        logger.debug("Extraindo o nome de usuário do token: {}", token);  // Log de extração do username
        return extractClaim(token, Claims::getSubject);  // Extrai o "subject" do token (username)
    }

    // Extrai qualquer claim do token (como o nome de usuário ou a data de expiração)
    public <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
        logger.debug("Extraindo claim do token: {}", token);  // Log de extração de claim
        Claims claims = Jwts.parser()
                .setSigningKey(SECRET_KEY)  // Usa a chave secreta para validar o token
                .parseClaimsJws(token)  // Parseia o token JWT
                .getBody();  // Retorna o corpo do JWT
        return claimsResolver.apply(claims);  // Aplica a função para extrair o claim
    }

    // Verifica se o token expirou
    private boolean isTokenExpired(String token) {
        logger.debug("Verificando expiração do token: {}", token);  // Log de verificação de expiração
        final Date expiration = extractClaim(token, Claims::getExpiration);  // Extrai a data de expiração do token
        return expiration.before(new Date());  // Verifica se a data de expiração é anterior ao momento atual
    }

    // Valida se o token é válido
    public boolean validateToken(String token, UserDetails userDetails) {
        logger.info("Validando token para o usuário: {}", userDetails.getUsername());  // Log de validação
        final String username = extractUsername(token);  // Extrai o nome de usuário do token
        return (username.equals(userDetails.getUsername()) && !isTokenExpired(token));  // Verifica se o nome de usuário bate e se o token não expirou
    }

    public Key getSecretKey() {
        return SECRET_KEY;
    }
}
