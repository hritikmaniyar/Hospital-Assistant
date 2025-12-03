package com.hospital.assist.service;

import com.hospital.assist.model.Doctor;
import com.hospital.assist.repository.DoctorRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DoctorService {

    private final DoctorRepository repository;

    public DoctorService(DoctorRepository repository) {
        this.repository = repository;
    }

    public List<Doctor> findAll() {
        return repository.findAll();
    }

    public Optional<Doctor> findById(Long id) {
        return repository.findById(id);
    }

    public Doctor save(Doctor doctor) {
        return repository.save(doctor);
    }

    public Doctor update(Long id, Doctor doctor) {
        Doctor existing = repository.findById(id).orElseThrow(() -> new RuntimeException("Doctor not found: " + id));
        doctor.setId(id);
        return repository.save(doctor);
    }

    public void delete(Long id) {
        repository.deleteById(id);
    }
}
