package com.example.demo.Services;

import com.example.demo.Model.Professor;
import com.example.demo.Model.Role;
import com.example.demo.Model.Type;
import com.example.demo.Model.Users;
import com.example.demo.Repository.ProfessorRepository;
import com.example.demo.Repository.TypeRepository;
import com.example.demo.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.example.demo.Model.Role.PROFESSOR;

@Service
public class ProfessorService {

    @Autowired
    private ProfessorRepository professorRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private TypeRepository typeRepository;

    public List<Professor> findAll() {
        return professorRepository.findAll();
    }

    public Professor findById(Long id) {
        return professorRepository.findById(id)
                .orElseThrow(() -> null);
    }
    public Professor create(Professor professor) {
        // Buscar o tipo 'Professor' no banco de dados (ID fixo 1)
        Type tipoProfessor = typeRepository.findById(1L)
                .orElseThrow(() -> new IllegalArgumentException("Tipo 'Professor' não encontrado"));

        professor.setTipo(tipoProfessor);  // Atribuir o tipo carregado

        // Criar novo usuário
        Users user = new Users();
        user.setUsername(professor.getNome().toLowerCase().replace(" ", "") + Math.round(Math.random() * 1000)); // Nome gerado
        user.setPassword(passwordEncoder.encode(professor.getSenha())); // Senha criptografada
        user.setRole(Role.PROFESSOR); // Definindo o papel como Professor
        user.setName(professor.getNome());
        user.setSchoolName(professor.getEscola());

        // Salvar o usuário (opcional devido ao cascade)
        userRepository.save(user);

        // Associar o usuário ao professor
        professor.setUser(user);

        // Salvar o professor
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