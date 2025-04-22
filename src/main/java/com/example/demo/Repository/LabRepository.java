package com.example.demo.Repository;

import com.example.demo.Model.Lab;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import java.util.Optional;

public interface LabRepository extends JpaRepository<Lab, Long> {

    @Query("SELECT l FROM Lab l LEFT JOIN FETCH l.softwares WHERE l.id = :labId")
    Optional<Lab> findByIdWithSoftwares(@Param("labId") Long labId);
}