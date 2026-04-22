package com.example.demo.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
@Table(name = "medecin")
public class Medecin {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String nom;
	private String prenom;
	private String specialite;

	@Column(name = "numero_ordre", unique = true)
	private String numeroOrdre;

	private String telephone;
	private String email;
	private boolean actif;
}