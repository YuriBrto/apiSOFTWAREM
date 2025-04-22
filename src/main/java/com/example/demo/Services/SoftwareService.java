package com.example.demo.Services;

import com.example.demo.Exception.ResourceNotFoundException;
import com.example.demo.Model.Software;
import com.example.demo.Repository.SoftwareRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SoftwareService {

    private final SoftwareRepository repository;
    private Logger logger = LoggerFactory.getLogger(SoftwareService.class.getName());



    public SoftwareService(SoftwareRepository repository) {
        this.repository = repository;
    }

    public Software save(Software software) {
        try {
            Software saved = repository.save(software);
            logger.info("Software instalado com sucesso! ID: " + saved.getId());
            return saved;

        } catch (Exception e) {
            logger.error("Erro ao instalar software: " + software.getNome(), e);
            throw new RuntimeException("Erro ao instalar o software. Verifique os dados e tente novamente.");
        }
    }


    public Software update(Software software) {
        try {
            Software entity = repository.findById(software.getId())
                    .orElseThrow(() -> new ResourceNotFoundException("Software não encontrado com ID: " + software.getId()));

            entity.setNome(software.getNome());
            entity.setLink(software.getLink());
            entity.setSoftwareLivre(software.isSoftwareLivre());
            entity.setVersao(software.getVersao());
            entity.setDataSolicitacao(software.getDataSolicitacao());
            entity.setSolicitadoPor(software.getSolicitadoPor());

            Software updated = repository.save(entity);
            logger.info("Software atualizado com sucesso! ID: " + updated.getId());
            return updated;

        } catch (ResourceNotFoundException e) {
            logger.error("Erro ao atualizar software: " + e.getMessage());
            throw e;

        } catch (Exception e) {
            logger.error("Erro inesperado ao atualizar software com ID: " + software.getId(), e);
            throw new RuntimeException("Erro ao atualizar o software. Verifique os dados e tente novamente.");
        }
    }

    public List<Software> findAll() {
        return repository.findAll();
    }

    public Software findById(Long id) {
        return (Software) repository.findById(id).orElse(null);
    }

    public void delete(Long id) {
        repository.deleteById(id);
    }

    public Software marcarComoNaoLivre(Long id) {
        try {
            Software software = repository.findById(id)
                    .orElseThrow(() -> new ResourceNotFoundException("Software não encontrado com ID: " + id));

            software.setSoftwareLivre(false);
            Software atualizado = repository.save(software);

            logger.info("Software com ID " + id + " marcado como NÃO LIVRE com sucesso.");
            return atualizado;

        } catch (ResourceNotFoundException e) {
            logger.error("Erro: " + e.getMessage());
            throw e;

        } catch (Exception e) {
            logger.error("Erro inesperado ao marcar software como não livre. ID: " + id, e);
            throw new RuntimeException("Erro ao atualizar a condição de software livre. Tente novamente.");
        }
    }


}
