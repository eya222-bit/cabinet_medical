package com.example.demo.service;

import com.example.demo.entity.RendezVous;
import com.example.demo.repository.RendezVousRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

@Service
public class RendezVousService {
    private final RendezVousRepository rdvRepository;

    public RendezVousService(RendezVousRepository rdvRepository) {
        this.rdvRepository = rdvRepository;
    }

    public List<RendezVous> getAll() {
        return rdvRepository.findAll();
    }

    public RendezVous getById(Long id) {
        return rdvRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Rendez-vous introuvable: " + id));
    }

    public List<RendezVous> getByMedecinAndDate(Long medecinId, LocalDate date) {
        LocalDateTime start = date.atStartOfDay();
        LocalDateTime end = date.atTime(LocalTime.MAX);
        return rdvRepository.findByMedecinIdAndDateHeureBetweenOrderByDateHeure(medecinId, start, end);
    }

    @Transactional
    public RendezVous save(RendezVous rdv) {
        validateRdv(rdv);

        LocalDateTime debut = rdv.getDateHeure();
        LocalDateTime fin = debut.plusMinutes(rdv.getDureeMinutes());

        List<RendezVous> conflits = rdvRepository.findConflicts(
                rdv.getMedecin().getId(),
                debut,
                fin,
                rdv.getId()
        );

        if (!conflits.isEmpty()) {
            throw new IllegalStateException("Le médecin a déjà un rendez-vous qui chevauche ce créneau.");
        }

        return rdvRepository.save(rdv);
    }

    @Transactional
    public void annuler(Long id) {
        RendezVous rdv = getById(id);
        rdv.setStatut(RendezVous.Statut.ANNULE);
        rdvRepository.save(rdv);
    }

    private void validateRdv(RendezVous rdv) {
        if (rdv.getPatient() == null || rdv.getPatient().getId() == null) {
            throw new IllegalArgumentException("Le patient est obligatoire.");
        }
        if (rdv.getMedecin() == null || rdv.getMedecin().getId() == null) {
            throw new IllegalArgumentException("Le médecin est obligatoire.");
        }
        if (rdv.getDateHeure() == null) {
            throw new IllegalArgumentException("La date/heure du rendez-vous est obligatoire.");
        }
        if (rdv.getDureeMinutes() == null || rdv.getDureeMinutes() <= 0 || rdv.getDureeMinutes() > 240) {
            throw new IllegalArgumentException("La durée doit être comprise entre 1 et 240 minutes.");
        }
        if (rdv.getStatut() == null) {
            rdv.setStatut(RendezVous.Statut.PLANIFIE);
        }
    }
}