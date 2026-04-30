package com.example.demo.controller;

import java.util.Optional;
import java.util.Set;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.entity.AppRole;
import com.example.demo.entity.AppUser;
import com.example.demo.entity.Patient;
import com.example.demo.repository.*;
import com.example.demo.repository.AppUserRepository;


@Controller
public class RegisterController {

    private final PatientRepository patientRepository;
    private final AppUserRepository userRepository;
    private final AppRoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;

    public RegisterController(PatientRepository patientRepository,
                              AppUserRepository userRepository,
                              AppRoleRepository roleRepository,
                              PasswordEncoder passwordEncoder) {
        this.patientRepository = patientRepository;
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @GetMapping("/register")
    public String form(Model model) {
        model.addAttribute("patient", new Patient());
        return "register";
    }

    @PostMapping("/register")
    public String save(@ModelAttribute Patient patient,
                       @RequestParam String password) {

        // 1 — Créer app_user
        AppUser user = new AppUser();
        user.setUsername(patient.getUsername()); // username = email
        user.setPassword(passwordEncoder.encode(password));
        user.setActif(true); // ✅ setActif pas setEnabled

        // 2 — Sauvegarder app_user EN PREMIER
        userRepository.save(user);

        // 3 — Assigner rôle PATIENT
        AppRole role = roleRepository.findByName("PATIENT").orElseThrow();
        user.getRoles().add(role);
        userRepository.save(user);

        // 4 — Lier patient à user via username
        patient.setUsername(user.getUsername()); // ✅ FK username
        patientRepository.save(patient);

        return "redirect:/login";
    }
}