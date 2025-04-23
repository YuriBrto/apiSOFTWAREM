package com.example.demo.Controller;

import com.example.demo.Model.Lab;
import com.example.demo.Services.LabService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/labs")
public class LabController {

    @Autowired
    private LabService labService;

    // CREATE
    @PostMapping
    public ResponseEntity<Lab> createLab(@RequestBody Lab lab) {
        Lab savedLab = labService.saveLab(lab);
        return ResponseEntity.ok(savedLab);
    }

    // READ (all)
    @GetMapping
    public ResponseEntity<List<Lab>> getAllLabs() {
        List<Lab> labs = labService.getAllLabs();
        return ResponseEntity.ok(labs);
    }

    // READ (by id)
    @GetMapping("/{id}")
    public ResponseEntity<Lab> getLabById(@PathVariable Long id) {
        Lab lab = labService.getLabById(id);
        return ResponseEntity.ok(lab);
    }

    // UPDATE
    @PutMapping("/{id}")
    public ResponseEntity<Lab> updateLab(@PathVariable Long id, @RequestBody Lab labDetails) {
        Lab updatedLab = labService.updateLab(id, labDetails);
        return ResponseEntity.ok(updatedLab);
    }

    // DELETE
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteLab(@PathVariable Long id) {
        labService.deleteLab(id);
        return ResponseEntity.noContent().build();
    }

    // Adicionar softwares (já existente)
    @PostMapping("/{labId}/add-softwares")
    public ResponseEntity<Lab> addSoftwaresToLab(@PathVariable Long labId, @RequestBody List<Long> softwareIds) {
        Lab lab = labService.addSoftwaresToLab(labId, softwareIds);
        return ResponseEntity.ok(lab);
    }
}