package com.example.demo.Controller;

import com.example.demo.Model.Software;
import com.example.demo.Services.SoftwareService;
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
}

