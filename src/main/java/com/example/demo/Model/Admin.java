package com.example.demo.Model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.Setter;

@Entity
<<<<<<<< HEAD:src/main/java/com/example/demo/Model/Professor.java
public class Professor {
========
public class Admin {
>>>>>>>> 3a622db251401730f3ff7fc4078f4e5522b616ab:src/main/java/com/example/demo/Model/Admin.java
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
