package com.example.demo.controller;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.demo.repository.PatientRepository;
import com.example.demo.entity.Medecin;
import com.example.demo.repository.AppUserRepository;
import com.example.demo.repository.MedecinRepository;

@Controller
@RequestMapping("/admin")

public class PageAdminController {

    private final PatientRepository patientRepository;
    private final MedecinRepository medecinRepository;
    private final AppUserRepository userRepository;

    public PageAdminController(PatientRepository patientRepository,
                               MedecinRepository medecinRepository,
                               AppUserRepository userRepository) {
        this.patientRepository = patientRepository;
        this.medecinRepository = medecinRepository;
        this.userRepository = userRepository;
    }

    @GetMapping("/dashboard")
    public String dashboard() {
        return "pageadmin";
    }

    @GetMapping("/patients")
    public String patients(Model model) {
        model.addAttribute("patients", patientRepository.findAll());
        return "patient";
    }

    @GetMapping("/medecins")
    public String medecins(Model model) {
        model.addAttribute("medecins", medecinRepository.findAll());
        return "medecins";
    }

    @GetMapping("/users")
    public String users(Model model) {
        model.addAttribute("users", userRepository.findAll());
        return "users";
    }
    @GetMapping("/medecins/new")
    public String createForm(Model model) {
        model.addAttribute("medecin", new Medecin());
        return "formmedecin";
    }
    @PostMapping("/medecins/save")
    public String save(Medecin medecin) {
        medecinRepository.save(medecin);
        return "redirect:/admin/medecins";
    }
    @GetMapping("/medecins/edit/{id}")
    public String edit(@PathVariable Long id, Model model) {
        Medecin m = medecinRepository.findById(id).orElseThrow();
        model.addAttribute("medecin", m);
        return "formmedecin";
    }
    @GetMapping("/medecins/delete/{id}")
    public String delete(@PathVariable Long id) {
        medecinRepository.deleteById(id);
        return "redirect:/admin/medecins";
    }
    
}