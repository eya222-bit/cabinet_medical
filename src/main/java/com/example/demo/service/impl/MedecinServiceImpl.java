package com.example.demo.service.impl;
import com.example.demo.entity.Medecin;
import com.example.demo.repository.MedecinRepository;
import com.example.demo.service.MedecinService;
import org.springframework.beans.factory.annotation.Autowired;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;


@Service
@RequiredArgsConstructor
public class MedecinServiceImpl implements MedecinService {
@Autowired
    private  MedecinRepository medecinRepository;

    @Override
    public List<Medecin> getAllMedecins() {
        return medecinRepository.findAll();
    }

    @Override
    public Medecin getMedecinById(Long id) {
        return medecinRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Médecin non trouvé"));
    }
    

    @Override
    public Medecin saveMedecin(Medecin medecin) {
        return medecinRepository.save(medecin);
    }

    @Override
    public void deleteMedecin(Long id) {
        medecinRepository.deleteById(id);
    }
}