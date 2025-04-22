package com.example.demo.Config;

import com.example.demo.Model.Role;
import com.example.demo.Model.Users;
import com.example.demo.Repository.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class DataInitializer {

    @Bean
    public CommandLineRunner loadData(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        return args -> {
            if (userRepository.findByUsername("admin").isEmpty()) {
                Users admin = new Users();
                admin.setUsername("admin");
                admin.setPassword(passwordEncoder.encode("admin123")); // senha codificada
                admin.setRole(Role.ADMIN);
                admin.setName("Administrador do Sistema");
                admin.setSchoolName("N/A");
                userRepository.save(admin);
                System.out.println("Usuário admin criado!");
            } else {
                System.out.println("Usuário admin já existe.");
            }
        };
    }
}
