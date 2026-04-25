package com.example.demo.repository;

import com.example.demo.entity.RendezVous;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;
import java.util.List;

public interface RendezVousRepository extends JpaRepository<RendezVous, Long> {
    List<RendezVous> findByMedecinIdAndDateHeureBetweenOrderByDateHeure(Long medecinId, LocalDateTime start, LocalDateTime end);
    List<RendezVous> findByPatientIdOrderByDateHeureDesc(Long patientId);
    boolean existsByMedecinIdAndDateHeure(Long medecinId, LocalDateTime dateHeure);

    @Query(value = """
            select *
            from rendez_vous r
            where r.medecin_id = :medecinId
              and r.statut <> 'ANNULE'
              and (:currentId is null or r.id <> :currentId)
              and r.date_heure < :endDateTime
              and date_add(r.date_heure, interval r.duree_minutes minute) > :startDateTime
            order by r.date_heure asc
            """, nativeQuery = true)
    List<RendezVous> findConflicts(@Param("medecinId") Long medecinId,
                                   @Param("startDateTime") LocalDateTime startDateTime,
                                   @Param("endDateTime") LocalDateTime endDateTime,
                                   @Param("currentId") Long currentId);

    @Query("""
            select r.id as id,
                   r.dateHeure as dateHeure,
                   r.dureeMinutes as dureeMinutes,
                   r.statut as statut,
                   r.motif as motif,
                   p.nom as patientNom,
                   p.prenom as patientPrenom,
                   m.nom as medecinNom,
                   m.prenom as medecinPrenom
            from RendezVous r
            join r.patient p
            join r.medecin m
            where r.dateHeure between :start and :end
            order by r.dateHeure asc
            """)
    List<RendezVousDashboardProjection> findDashboardRendezVousBetween(@Param("start") LocalDateTime start,
                                                                       @Param("end") LocalDateTime end);

    @Query("""
            select count(distinct r.patient.id)
            from RendezVous r
            where r.dateHeure between :start and :end
              and r.dateHeure <= :now
              and r.statut in ('PLANIFIE', 'CONFIRME')
            """)
    long countPatientsEnAttente(@Param("start") LocalDateTime start,
                                @Param("end") LocalDateTime end,
                                @Param("now") LocalDateTime now);
}