package com.example.demo;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;

import static org.springframework.security.config.Customizer.withDefaults;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    // Definindo as permissões de segurança
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf(csrf -> csrf.disable())  // Desabilita a proteção CSRF
                .authorizeHttpRequests(authorizeRequests ->
                        authorizeRequests
                                .requestMatchers("/login").permitAll()  // Permite acesso sem autenticação
                                .anyRequest().authenticated()  // Exige autenticação para as demais rotas
                )
                .formLogin(withDefaults())  // Configuração do login padrão
                .logout(logout -> logout.permitAll());  // Permite logout

        return http.build();  // Retorna a configuração completa do filtro de segurança
    }

    // Define o PasswordEncoder para o BCrypt
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    // Definindo o UserDetailsService para fornecer usuários para a autenticação
    @Bean
    public UserDetailsService userDetailsService() {
        return username -> User
                .withUsername("admin")  // Definindo um usuário "admin" para testes
                .password(passwordEncoder().encode("password"))  // Senha do usuário
                .roles("USER")  // Papel do usuário
                .build();
    }
}