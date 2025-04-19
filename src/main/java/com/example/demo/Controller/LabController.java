package com.example.demo.Controller;

import com.example.demo.Model.Lab;
import com.example.demo.Model.Software;
import com.example.demo.Services.LabService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/api/labs")
public class LabController {

    @Autowired
    private LabService labService;

    // Adiciona múltiplos softwares a um laboratório
    @PostMapping("/{labId}/add-softwares")
    public Lab addSoftwaresToLab(@PathVariable Long labId, @RequestBody List<Long> softwareIds) {
        return labService.addSoftwaresToLab(labId, softwareIds);
    }

    // Lista todos os softwares de um laboratório
    @GetMapping("/{labId}/softwares")
    public Set<Software> getLabSoftwares(@PathVariable Long labId) {
        return labService.getLabWithSoftwares(labId).getSoftwares();
    }
}