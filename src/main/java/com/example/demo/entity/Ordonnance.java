package com.example.demo.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder

@Table(name = "ordonnance")
public class Ordonnance {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn(name = "rendez_vous_id")
    private RendezVous rendezVous;

    private LocalDate dateEmission;

    @OneToMany(mappedBy = "ordonnance", cascade = CascadeType.ALL)
    private List<LigneMedicament> medicaments;

    @Column(columnDefinition = "TEXT")
    private String observations;
}