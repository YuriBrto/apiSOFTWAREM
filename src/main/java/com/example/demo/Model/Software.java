package com.example.demo.Model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Entity
public class Software {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Getter
    @Setter
    private Long id;

    @Getter
    @Setter
    private String nome;

    @Getter
    @Setter
    private String link;

    @Getter
    @Setter
    private String versao;

    @Getter
    @Setter
    private boolean softwareLivre;

    @Getter
    @Setter
    private LocalDate dataSolicitacao;

    @ManyToOne
    @JoinColumn(name = "solicitado_por")
    @Getter
    @Setter
    private professor solicitadoPor;// Quem solicitou o software

    @ManyToMany
    @JoinColumn(name = "LabName.id")
    @Getter
    @Setter
    private Lab LabName;

}