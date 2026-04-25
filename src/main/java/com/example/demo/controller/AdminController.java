package com.example.demo.controller;

import com.example.demo.entity.Medecin;
import com.example.demo.repository.MedecinRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/medecins")
public class AdminController {

    @Autowired
    private MedecinRepository medecinRepository;

    @GetMapping("/index")
    public String index(Model model) {
        model.addAttribute("listMedecins", medecinRepository.findAll());
        return "medecins/index";
    }

    @GetMapping("/form")
    public String formMedecin(Model model) {
        model.addAttribute("medecin", new Medecin());
        return "medecins/form";
    }

    @PostMapping("/save")
    public String save(Medecin medecin) {
        medecinRepository.save(medecin);
        return "redirect:/medecins/index";
    }

    @GetMapping("/delete")
    public String delete(@RequestParam(name = "id") Long id) {
        medecinRepository.deleteById(id);
        return "redirect:/medecins/index";
    }

    @GetMapping("/edit")
    public String edit(@RequestParam(name = "id") Long id, Model model) {
        Medecin medecin = medecinRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Id invalide:" + id));
        model.addAttribute("medecin", medecin);
        return "medecins/form";
    }
}