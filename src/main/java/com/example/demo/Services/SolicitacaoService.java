package com.example.demo.Services;

import com.example.demo.Exception.ResourceNotFoundException;
import com.example.demo.Model.Solicitacao;
import com.example.demo.Repository.SolicitacaoRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SolicitacaoService {

    @Autowired
    private SolicitacaoRepository repository;
    @Autowired
    private LabService labService;

    private Logger logger = LoggerFactory.getLogger(SolicitacaoService.class.getName());


public Solicitacao save(Solicitacao solicitacao){
    try {
        Solicitacao saved = repository.save(solicitacao);
        logger.info("Solicitacao feita com sucesso! ID: " + saved.getId());
        return saved;

    } catch (Exception e) {
        logger.error("Erro ao solicitar software: ");
        throw new RuntimeException("Erro ao criar solicitacao");
    }
}

public Solicitacao update(Solicitacao solicitacao) {
        try {
            Solicitacao entity = repository.findById(solicitacao.getId())
                    .orElseThrow(() -> new ResourceNotFoundException("Solicitacao não encontrada com ID: " + solicitacao.getId()));

            entity.setSoftwaresSolicitados(solicitacao.getSoftwaresSolicitados());
            entity.setProfessor(solicitacao.getProfessor());


            Solicitacao updated = repository.save(entity);
            logger.info("Solicitacao atualizado com sucesso! ID: " + updated.getId());
            return updated;

        } catch (ResourceNotFoundException e) {
            logger.error("Erro ao atualizar solicitacao: " + e.getMessage());
            throw e;

        } catch (Exception e) {
            logger.error("Erro inesperado ao atualizar solicitacao com ID: " + solicitacao.getId(), e);
            throw new RuntimeException("Erro ao atualizar solicitacao. Verifique os dados e tente novamente.");
        }


    }

public List<Solicitacao> findAll() {
        return repository.findAll();
}

public Solicitacao findById(Long id) {
return repository.findById(id).orElse(null);
}

public void delete(Long id) {
        repository.deleteById(id);
}

}
