package com.example.demo.Services;

import com.example.demo.Model.Software;
import com.example.demo.Repository.SoftwareRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SoftwareService {

    private final SoftwareRepository repository;

    public SoftwareService(SoftwareRepository repository) {
        this.repository = repository;
    }

    public Software save(Software software) {
        return (Software) repository.save(software);
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
}
