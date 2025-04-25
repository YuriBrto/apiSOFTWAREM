package com.example.demo.Config;

import com.example.demo.Model.Lab;
import com.example.demo.Model.Role;
import com.example.demo.Model.Type;
import com.example.demo.Model.Users;
import com.example.demo.Repository.LabRepository;
import com.example.demo.Repository.TypeRepository;
import com.example.demo.Repository.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.List;

@Configuration
public class DataInitializer {

    @Bean
    public CommandLineRunner loadData(UserRepository userRepository, PasswordEncoder passwordEncoder,
                                      TypeRepository typeRepository, LabRepository labRepository) {
        return args -> {
            // Tipos
            if (typeRepository.findById(1L).isEmpty()) {
                Type professorType = new Type();
                professorType.setId(1L);
                professorType.setNome("Professor");
                typeRepository.save(professorType);
                System.out.println("Tipo 'Professor' criado!");
            }

            if (typeRepository.findById(2L).isEmpty()) {
                Type adminType = new Type();
                adminType.setId(2L);
                adminType.setNome("Admin");
                typeRepository.save(adminType);
                System.out.println("Tipo 'Admin' criado!");
            }

            // Usuário admin
            if (userRepository.findByUsername("admin").isEmpty()) {
                Users admin = new Users();
                admin.setUsername("admin");
                admin.setPassword(passwordEncoder.encode("admin123"));
                admin.setRole(Role.ADMIN);
                admin.setName("Administrador do Sistema");
                admin.setSchoolName("N/A");
                userRepository.save(admin);
                System.out.println("Usuário admin criado!");
            }

            // Usuário professor
            if (userRepository.findByUsername("professor").isEmpty()) {
                Users professor = new Users();
                professor.setUsername("professor");
                professor.setPassword(passwordEncoder.encode("professor123"));
                professor.setRole(Role.PROFESSOR);
                professor.setName("Professor do Sistema");
                professor.setSchoolName("Escola Exemplo");
                userRepository.save(professor);
                System.out.println("Usuário professor criado!");
            }

            // Laboratórios
            if (labRepository.count() == 0) {
                Lab lab1 = new Lab();
                lab1.setNome("Laboratório 1");
                lab1.setStatus(true);

                Lab lab2 = new Lab();
                lab2.setNome("Laboratório 2");
                lab2.setStatus(true);

                Lab lab3 = new Lab();
                lab3.setNome("Laboratório 3");
                lab3.setStatus(false); // exemplo com status falso

                labRepository.saveAll(List.of(lab1, lab2, lab3));
                System.out.println("3 laboratórios criados com sucesso!");
            } else {
                System.out.println("Laboratórios já existentes.");
            }
        };
    }}
