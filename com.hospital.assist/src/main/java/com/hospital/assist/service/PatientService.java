package com.hospital.assist.service;

import com.hospital.assist.model.Patient;
import com.hospital.assist.repository.PatientRepository;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class PatientService {
    private final PatientRepository repo;
    public PatientService(PatientRepository repo){ this.repo = repo; }

    public Patient save(Patient p){ return repo.save(p); }
    public Patient update(Long id, Patient p){
        return repo.findById(id).map(existing -> {
            existing.setName(p.getName());
            existing.setYears(p.getYears());
            existing.setMonths(p.getMonths());
            existing.setEmail(p.getEmail());
            existing.setPhone(p.getPhone());
            existing.setDisease(p.getDisease());
            return repo.save(existing);
        }).orElseThrow(() -> new RuntimeException("Patient not found"));
    }
    public void delete(Long id){ repo.deleteById(id); }
    public List<Patient> findAll(){ return repo.findAll(); }
    public Patient findById(Long id){ return repo.findById(id).orElse(null); }
}
