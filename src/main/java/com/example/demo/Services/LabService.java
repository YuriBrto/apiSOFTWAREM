package com.example.demo.Services;

import com.example.demo.Model.Lab;
import com.example.demo.Model.Software;
import com.example.demo.Repository.LabRepository;
import com.example.demo.Repository.SoftwareRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@Service
public class LabService {

    @Autowired
    private LabRepository labRepository;

    @Autowired
    private SoftwareRepository softwareRepository;

    public Lab addSoftwaresToLab(Long labId, List<Long> softwareIds) {
        Lab lab = labRepository.findById(labId)
                .orElseThrow(() -> new RuntimeException("Laboratório não encontrado"));

        Set<Software> softwaresToAdd = softwareRepository.findAllByIdIn(softwareIds);
        lab.getSoftwares().addAll(softwaresToAdd);

        return labRepository.save(lab);
    }

    public Lab getLabWithSoftwares(Long labId) {
        return labRepository.findByIdWithSoftwares(labId)
                .orElseThrow(() -> new RuntimeException("Laboratório não encontrado"));
    }
}