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
            // Criar usuário Admin
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

            // Criar usuário Professor
            if (userRepository.findByUsername("professor").isEmpty()) {
                Users professor = new Users();
                professor.setUsername("professor");
                professor.setPassword(passwordEncoder.encode("professor123")); // senha codificada
                professor.setRole(Role.PROFESSOR);
                professor.setName("Professor do Sistema");
                professor.setSchoolName("Escola Exemplo");
                userRepository.save(professor);
                System.out.println("Usuário professor criado!");
            } else {
                System.out.println("Usuário professor já existe.");
            }
        };
    }
}
