package com.example.demo.repository;

import com.example.demo.entity.LigneMedicament;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LigneMedicamentRepository extends JpaRepository<LigneMedicament, Long> {
}