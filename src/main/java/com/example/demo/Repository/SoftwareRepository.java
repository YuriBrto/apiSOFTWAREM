package com.example.demo.Repository;

import com.example.demo.Model.Software;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SoftwareRepository extends JpaRepository<Software, Long> {
    // Você pode adicionar métodos customizados aqui, caso necessário
}