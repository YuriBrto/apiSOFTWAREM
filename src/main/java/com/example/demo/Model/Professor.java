package com.example.demo.Model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "professor")
@Getter
@Setter
public class Professor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "teacher_name")
    private String nome;

    @Column(name = "school_name")
    private String escola;

    @ManyToOne
    @JoinColumn(name = "type_id")
    private Type tipo;

    // Construtor padrão necessário para JPA
    public Professor() {}

    // Construtor com campos
    public Professor(String nome, String escola, Type tipo) {
        this.nome = nome;
        this.escola = escola;
        this.tipo = tipo;
    }
}