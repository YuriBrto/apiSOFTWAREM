package com.example.demo.Services;

import com.example.demo.Model.Lab;
import com.example.demo.Model.Software;
import com.example.demo.Repository.LabRepository;
import com.example.demo.Repository.SoftwareRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class LabService {

    @Autowired
    private LabRepository labRepository;

    @Autowired
    private SoftwareRepository softwareRepository;

    public Lab addSoftwaresToLab(Long labId, List<Long> softwareIds) {
        Lab lab = labRepository.findById(labId)
                .orElseThrow(() -> new RuntimeException("Laboratorio nao encontrado"));

        Set<Software> softwaresExistentes = lab.getSoftwares(); // Softwares já instalados
        Set<Long> idsExistentes = softwaresExistentes.stream()
                .map(Software::getId)
                .collect(Collectors.toSet());

        for (Long id : softwareIds) {
            if (idsExistentes.contains(id)) {
                throw new RuntimeException("Software já instalado no laboratório: ID " + id);
            }
        }

        Set<Software> softwaresToAdd = softwareRepository.findAllByIdIn(softwareIds);
        lab.getSoftwares().addAll(softwaresToAdd);

        return labRepository.save(lab);
    }

    public Lab getLabWithSoftwares(Long labId) {
        return labRepository.findByIdWithSoftwares(labId)
                .orElseThrow(() -> new RuntimeException("Laboratorio nao encontrado"));
    }

    public Lab saveLab(Lab lab) {
        return labRepository.save(lab);
    }

    public List<Lab> getAllLabs() {
        return labRepository.findAll();
    }

    public Lab getLabById(Long id) {
        return labRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Laboratorio nao encontrado com id: " + id));
    }

    public Lab updateLab(Long id, Lab labDetails) {
        Lab lab = getLabById(id);
        lab.setNome(labDetails.getNome());
        lab.setStatus(labDetails.isStatus());
        return labRepository.save(lab);
    }

    public void deleteLab(Long id) {
        Lab lab = getLabById(id);
        labRepository.delete(lab);
    }



}