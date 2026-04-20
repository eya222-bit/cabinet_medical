package com.example.demo.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder

@Table(name = "patient", uniqueConstraints = {
        @UniqueConstraint(columnNames = "cin")
})
public class Patient {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String cin;

    private String nom;
    private String prenom;

    private LocalDate dateNaissance;

    private String telephone;
    private String email;

    @Column(columnDefinition = "TEXT")
    private String antecedents;

    private LocalDateTime dateCreation;
}