package com.example.demo.service;

import com.example.demo.entity.RendezVous;
import com.example.demo.repository.RendezVousRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class RendezVousService {
    private final RendezVousRepository rdvRepository;

    public RendezVousService(RendezVousRepository rdvRepository) {
        this.rdvRepository = rdvRepository;
    }

    @Transactional
    public RendezVous save(RendezVous rdv) {
        LocalDateTime debut = rdv.getDateHeure();
        LocalDateTime fin = debut.plusMinutes(rdv.getDureeMinutes());

        List<RendezVous> conflits = rdvRepository.findByMedecinIdAndDateHeureBetweenOrderByDateHeure(
                rdv.getMedecin().getId(), debut, fin);

        if (rdv.getId() != null) {
            conflits.removeIf(r -> r.getId().equals(rdv.getId()));
        }
        if (!conflits.isEmpty()) {
            throw new IllegalStateException("Le médecin a déjà un rendez-vous sur ce créneau.");
        }
        if (rdvRepository.existsByMedecinIdAndDateHeure(rdv.getMedecin().getId(), rdv.getDateHeure())) {
            throw new IllegalStateException("Un rendez-vous existe déjà à cette heure précise.");
        }
        return rdvRepository.save(rdv);
    }

    public void annuler(Long id) {
        RendezVous rdv = rdvRepository.findById(id).orElseThrow();
        rdv.setStatut(RendezVous.Statut.ANNULE);
        rdvRepository.save(rdv);
    }
}