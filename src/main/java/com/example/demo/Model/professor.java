package com.example.demo.Model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
@Entity
@Getter
@Setter
public class Professor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;
    private String escola;

    @ManyToOne
    @JoinColumn(name = "type_id", nullable = false)  // Defina como não nulo
    private Type tipo;

    @OneToOne(cascade = CascadeType.ALL)  // Relacionamento com a entidade 'User'
    @JoinColumn(name = "user_id", nullable = false)  // Certifique-se de que este campo não será nulo
    private Users user;

    @Transient
    private String senha;  // Mantenha a senha apenas como campo não persistido, pois é tratada pela tabela 'Users'
}
