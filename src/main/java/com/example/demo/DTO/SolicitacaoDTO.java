package com.example.demo.DTO;

import lombok.Data;

import java.util.List;

@Data
public class SolicitacaoDTO {
    private List<Long> softwaresIds;
    private Long professorId;
    private Long labId;
}

