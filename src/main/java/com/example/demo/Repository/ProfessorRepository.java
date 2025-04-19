package com.example.demo.Repository;

import com.example.demo.Model.Professor;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProfessorRepository extends JpaRepository<Professor, Long> {
    // Consultas customizadas podem ser adicionadas aqui
    // Exemplo: List<Professor> findByEscola(String escola);
}