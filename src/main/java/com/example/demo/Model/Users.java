package com.example.demo.Model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Users {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String username;

    private String password;

    @Enumerated(EnumType.STRING)
    private Role role;  // Ex: PROFESSOR, ADMIN

    private String name;
    private String schoolName;  // Usado só para 'Professor'

    @OneToOne(mappedBy = "user")  // Relaciona com a entidade 'Professor'
    private Professor professor;
}
