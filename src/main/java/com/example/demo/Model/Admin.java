package com.example.demo.Model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.Setter;

@Entity
public class Admin {
    @Getter
    @Setter
    @Id
    private long id;

    @Getter
    @Setter
    private String adminName;

    @ManyToOne
    @JoinColumn(name = "type_id")
    @Getter
    @Setter
    private Type type; // Relacionamento com a tabela Type
}