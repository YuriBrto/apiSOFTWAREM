package com.example.demo.Repository;

import com.example.demo.Model.Software;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Set;

public interface SoftwareRepository extends JpaRepository<Software, Long> {
    Set<Software> findAllByIdIn(List<Long> ids);
}