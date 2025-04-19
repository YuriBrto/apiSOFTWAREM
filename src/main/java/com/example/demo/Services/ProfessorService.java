package com.example.demo.Services;

import com.example.demo.Model.Professor;
import com.example.demo.Repository.ProfessorRepository;
import com.example.demo.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProfessorService {

    private final ProfessorRepository professorRepository;

    @Autowired
    public ProfessorService(ProfessorRepository professorRepository) {
        this.professorRepository = professorRepository;
    }

    public List<Professor> findAll() {
        return professorRepository.findAll();
    }

    public Professor findById(Long id) {
        return professorRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Professor not found with id: " + id));
    }

    public Professor create(Professor professor) {
        // Aqui você pode incluir validações antes de salvar
        return professorRepository.save(professor);
    }

    public Professor update(Long id, Professor updatedProfessor) {
        Professor existingProfessor = professorRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Professor not found with id: " + id));

        existingProfessor.setTeacherName(updatedProfessor.getTeacherName());
        existingProfessor.setSchoolName(updatedProfessor.getSchoolName());
        existingProfessor.setType(updatedProfessor.getType());

        return professorRepository.save(existingProfessor);
    }

    public void delete(Long id) {
        Professor existingProfessor = professorRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Professor not found with id: " + id));
        professorRepository.delete(existingProfessor);
    }
}
