package com.example.demo.repository;

import com.example.demo.entity.RendezVous;
import org.springframework.data.jpa.repository.JpaRepository;
import java.time.LocalDateTime;
import java.util.List;

public interface RendezVousRepository extends JpaRepository<RendezVous, Long> {
    List<RendezVous> findByMedecinIdAndDateHeureBetweenOrderByDateHeure(Long medecinId, LocalDateTime start, LocalDateTime end);
    List<RendezVous> findByPatientIdOrderByDateHeureDesc(Long patientId);
    boolean existsByMedecinIdAndDateHeure(Long medecinId, LocalDateTime dateHeure);
}