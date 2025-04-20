package com.example.demo.Services;

import com.example.demo.Model.Professor;
import com.example.demo.Repository.ProfessorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProfessorService {

    @Autowired
    private ProfessorRepository professorRepository;

    public List<Professor> findAll() {
        return professorRepository.findAll();
    }

    public Professor findById(Long id) {
        return professorRepository.findById(id)
                .orElseThrow(() -> null);
    }

    public Professor create(Professor professor) {
        // Aqui você pode incluir validações antes de salvar
        return professorRepository.save(professor);
    }

    public Professor update(Long id, Professor updatedProfessor) {
        Professor existingProfessor = professorRepository.findById(id)
                .orElseThrow(() -> null);

        existingProfessor.setNome(updatedProfessor.getNome());
        existingProfessor.setEscola(updatedProfessor.getEscola());
        existingProfessor.setTipo(updatedProfessor.getTipo());

        return professorRepository.save(existingProfessor);
    }

    public void delete(Long id) {
        Professor existingProfessor = professorRepository.findById(id)
                .orElseThrow(() -> null);
        professorRepository.delete(existingProfessor);
    }
}