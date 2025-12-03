package com.hospital.assist.service;

import com.hospital.assist.model.Patient;
import com.hospital.assist.repository.PatientRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PatientService {

    private final PatientRepository repository;

    public PatientService(PatientRepository repository) {
        this.repository = repository;
    }

    public List<Patient> findAll() {
        return repository.findAll();
    }

    public Optional<Patient> findById(Long id) {
        return repository.findById(id);
    }

    public Patient save(Patient patient) {
        return repository.save(patient);
    }

    public Patient update(Long id, Patient patient) {
        Patient existing = repository.findById(id).orElseThrow(() -> new RuntimeException("Patient not found: " + id));
        patient.setId(id);
        return repository.save(patient);
    }

    public void delete(Long id) {
        repository.deleteById(id);
    }
}
