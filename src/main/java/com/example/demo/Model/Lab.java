package com.example.demo.Model;

import jakarta.persistence.*;
        import lombok.Getter;
import lombok.Setter;
import java.util.Set;

@Entity
@Getter
@Setter
public class Lab {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;
    private boolean status;

    @ManyToMany
    @JoinTable(
            name = "lab_software",
            joinColumns = @JoinColumn(name = "lab_id"),
            inverseJoinColumns = @JoinColumn(name = "software_id")
    )
    private Set<Software> softwares;
}