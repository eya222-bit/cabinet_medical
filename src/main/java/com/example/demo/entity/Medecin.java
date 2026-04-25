package com.example.demo.entity;

import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "medecin")
public class Medecin {
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String nom;
	private String prenom;
	private String specialite;
	@Column(unique = true, nullable = false)
	private String numeroOrdre;
	private String telephone;
	private String email;
	private Boolean actif = true;

	@OneToMany(mappedBy = "medecin")
	private List<RendezVous> rendezVousList = new ArrayList<>();

	// Getters et setters
	public Long getId() { return id; }
	public void setId(Long id) { this.id = id; }
	public String getNom() { return nom; }
	public void setNom(String nom) { this.nom = nom; }
	public String getPrenom() { return prenom; }
	public void setPrenom(String prenom) { this.prenom = prenom; }
	public String getSpecialite() { return specialite; }
	public void setSpecialite(String specialite) { this.specialite = specialite; }
	public String getNumeroOrdre() { return numeroOrdre; }
	public void setNumeroOrdre(String numeroOrdre) { this.numeroOrdre = numeroOrdre; }
	public String getTelephone() { return telephone; }
	public void setTelephone(String telephone) { this.telephone = telephone; }
	public String getEmail() { return email; }
	public void setEmail(String email) { this.email = email; }
	public Boolean getActif() { return actif; }
	public void setActif(Boolean actif) { this.actif = actif; }
	public List<RendezVous> getRendezVousList() { return rendezVousList; }
	public void setRendezVousList(List<RendezVous> rendezVousList) { this.rendezVousList = rendezVousList; }
}