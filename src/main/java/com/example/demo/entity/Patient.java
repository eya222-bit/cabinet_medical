package com.example.demo.entity;

import jakarta.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "patient")
public class Patient {
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@Column(unique = true, nullable = false)
	private String cin;
	private String nom;
	private String prenom;
	private LocalDate dateNaissance;
	private String telephone;
	private String email;
	@Column(columnDefinition = "TEXT")
	private String antecedents;
	private LocalDateTime dateCreation = LocalDateTime.now();

	@OneToMany(mappedBy = "patient", cascade = CascadeType.ALL)
	private List<RendezVous> rendezVousList = new ArrayList<>();

	// Getters et setters
	public Long getId() { return id; }
	public void setId(Long id) { this.id = id; }
	public String getCin() { return cin; }
	public void setCin(String cin) { this.cin = cin; }
	public String getNom() { return nom; }
	public void setNom(String nom) { this.nom = nom; }
	public String getPrenom() { return prenom; }
	public void setPrenom(String prenom) { this.prenom = prenom; }
	public LocalDate getDateNaissance() { return dateNaissance; }
	public void setDateNaissance(LocalDate dateNaissance) { this.dateNaissance = dateNaissance; }
	public String getTelephone() { return telephone; }
	public void setTelephone(String telephone) { this.telephone = telephone; }
	public String getEmail() { return email; }
	public void setEmail(String email) { this.email = email; }
	public String getAntecedents() { return antecedents; }
	public void setAntecedents(String antecedents) { this.antecedents = antecedents; }
	public LocalDateTime getDateCreation() { return dateCreation; }
	public void setDateCreation(LocalDateTime dateCreation) { this.dateCreation = dateCreation; }
	public List<RendezVous> getRendezVousList() { return rendezVousList; }
	public void setRendezVousList(List<RendezVous> rendezVousList) { this.rendezVousList = rendezVousList; }
}