package com.example.demo.Controller;

import com.example.demo.DTO.SolicitacaoDTO;
import com.example.demo.Exception.ResourceNotFoundException;
import com.example.demo.Model.*;
import com.example.demo.Repository.LabRepository;
import com.example.demo.Repository.ProfessorRepository;
import com.example.demo.Repository.SoftwareRepository;
import com.example.demo.Services.LabService;
import com.example.demo.Services.SoftwareService;
import com.example.demo.Services.SolicitacaoService;
import jakarta.websocket.server.PathParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.nio.file.Path;
import java.util.Collections;
import java.util.List;

@RestController
    @RequestMapping("/api/solicitacao")
public class SolicitacaoController {

    @Autowired
    private SolicitacaoService service;

    @Autowired
    private LabService labService;

    @Autowired
    private SoftwareRepository softwareRepository;

    @Autowired
    private ProfessorRepository professorRepository;

    @Autowired
    private LabRepository labRepository;

@PostMapping()
public ResponseEntity<Solicitacao> create(@RequestBody SolicitacaoDTO dto) {

        Solicitacao solicitacao = new Solicitacao();

        List<Software> softwares = softwareRepository.findAllById(dto.getSoftwaresIds());
        solicitacao.setSoftwaresSolicitados(softwares);

        Professor professor = professorRepository.findById(dto.getProfessorId()).orElseThrow(null);
        Lab lab = labRepository.findById(dto.getLabId()).orElseThrow(null);

        solicitacao.setProfessor(professor);
        solicitacao.setLab(lab);
        solicitacao.setAprovada(false);

        Solicitacao saved = service.save(solicitacao);
        return ResponseEntity.ok(saved);
    }


@GetMapping
public List<Solicitacao> getAll(){
return service.findAll();
}

@GetMapping("/{id}")
public Solicitacao getById(@PathVariable Long id){
    return service.findById(id);
}

@PutMapping("/{id}")
public ResponseEntity<Solicitacao> updateSolicitacao(@PathVariable Long id, @RequestBody Solicitacao solicitacao ){
    try {
        Solicitacao updatedSolicitacao = service.update(solicitacao);
        return ResponseEntity.ok(updatedSolicitacao);
    } catch (ResourceNotFoundException e) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
    } catch (Exception e) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
    }
}

@DeleteMapping("/{id}")
public void delete(@PathVariable Long id){
    service.delete(id);
}

@PostMapping("/aprovarsolicitacao/{id}")
public Solicitacao aprovarSolicitacao(@PathVariable Long id){
    Solicitacao sol = getById(id);
    sol.setAprovada(true);
    for (Software softwares : sol.getSoftwaresSolicitados()) {
       labService.addSoftwaresToLab(sol.getLab().getId(), Collections.singletonList(softwares.getId()));
    }
    return service.findById(id);
}
    @PutMapping("/{id}/status")
    public ResponseEntity<Solicitacao> atualizarStatus(
            @PathVariable Long id,
            @RequestParam StatusInstalacao status) {

        Solicitacao solicitacao = service.findById(id);
        solicitacao.setStatusInstalacao(status);

        // Exemplo de liberação do laboratório caso status seja FINALIZADA
        if (status == StatusInstalacao.FINALIZADA) {
            Lab lab = solicitacao.getLab();
            lab.setStatus(true); // status true = liberado (ou você pode criar outro campo mais descritivo)
            labService.saveLab(lab);

            // Aqui poderia entrar a lógica de notificar o professor
        }

        return ResponseEntity.ok(service.save(solicitacao));
    }

}
