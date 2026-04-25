package com.example.demo.repository;

import com.example.demo.entity.Ordonnance;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface OrdonnanceRepository extends JpaRepository<Ordonnance, Long> {
    Optional<Ordonnance> findByRendezVousId(Long rendezVousId);
}