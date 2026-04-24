package com.example.demo.service;

import com.example.demo.entity.Medecin;
import java.util.List;

import org.springframework.stereotype.Service;



public interface MedecinService {

    List<Medecin> getAllMedecins();

    Medecin getMedecinById(Long id);

    Medecin saveMedecin(Medecin medecin);

    void deleteMedecin(Long id);


	
	

}