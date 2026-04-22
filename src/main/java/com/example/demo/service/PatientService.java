package com.example.demo.service;

import com.example.demo.entity.Patient;
import java.util.List;

public interface PatientService {

    List<Patient> getAllPatients();

    Patient getPatientById(Long id);

    Patient savePatient(Patient patient);

    void deletePatient(Long id);
}