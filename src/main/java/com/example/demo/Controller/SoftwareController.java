package com.example.demo.Controller;

import com.example.demo.Exception.ResourceNotFoundException;
import com.example.demo.Model.Software;
import com.example.demo.Services.SoftwareService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/software")
public class SoftwareController {

    private final SoftwareService service;

    public SoftwareController(SoftwareService service) {
        this.service = service;
    }

    @PostMapping
    public Software create(@RequestBody Software software) {
        return service.save(software);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Software> updateSoftware(@PathVariable Long id, @RequestBody Software software) {
        try {
            Software updatedSoftware = service.update(software);
            return ResponseEntity.ok(updatedSoftware);
        } catch (ResourceNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    @GetMapping
    public List<Software> getAll() {
        return service.findAll();
    }

    @GetMapping("/{id}")
    public Software getById(@PathVariable Long id) {
        return service.findById(id);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        service.delete(id);
    }

    @PutMapping("/{id}/marcar-nao-livre")
    public ResponseEntity<Software> marcarComoNaoLivre(@PathVariable Long id) {
        Software atualizado = service.marcarComoNaoLivre(id);
        return ResponseEntity.ok(atualizado);
    }



}

