package com.example.demo.Model;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

@Entity
public class Type {
    @Getter
    @Setter
    @Id
    private long id;

    @Getter
    @Setter
    @Column(name = "type_name")
    private String nome;



}
