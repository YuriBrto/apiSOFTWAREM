package com.example.demo.Model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.Set;

@Entity
@Getter
@Setter
public class Solicitacao {

@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
private Long id;


@ManyToMany
private List<Software> SoftwaresSolicitados;

@OneToOne
private Professor professor;

@OneToOne
private Lab lab;

@Column(nullable = false)
private Boolean aprovada;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private StatusInstalacao statusInstalacao = StatusInstalacao.INICIADA; // valor padrão

}
