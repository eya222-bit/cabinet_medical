package com.example.demo.controller;

import com.example.demo.entity.Medecin;
import com.example.demo.service.MedecinService;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/medecins")
public class MedecinController {

    @Autowired
    private MedecinService medecinService;
    
    @GetMapping
    public List<Medecin> list(){
        return medecinService.getAllMedecins();
    }

    @GetMapping("/new")
    public String create(Model model) {
        model.addAttribute("medecin", new Medecin());
        return "medecin/form";
    }

    @PostMapping
    public String save(@ModelAttribute Medecin medecin) {
        medecinService.saveMedecin(medecin);
        return "redirect:/medecins";
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable Long id) {
        medecinService.deleteMedecin(id);
        return "redirect:/medecins";
    }
}