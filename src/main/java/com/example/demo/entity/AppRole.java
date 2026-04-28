package com.example.demo.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "app_role")
public class AppRole {
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
    
    private String name;

    public AppRole() {}
    public AppRole(String name) { this.name = name; }
    public String getRoleName() { return name; }
    public void setRoleName(String name) { this.name = name; }
}