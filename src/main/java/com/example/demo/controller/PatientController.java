package com.example.demo.controller;

import com.example.demo.entity.Patient;
import com.example.demo.service.PatientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/patients")

public class PatientController {
@Autowired
    private  PatientService patientService;

    @GetMapping
    public List<Patient> list(){
        return patientService.getAllPatients();
    }

    @PostMapping
    public Patient save(@RequestBody Patient patient){
        return patientService.savePatient(patient);
    }

    @GetMapping("/{id}")
    public Patient get(@PathVariable Long id){
        return patientService.getPatientById(id);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id){
        patientService.deletePatient(id);
    }
}