package com.hospital.assist.controller;

import com.hospital.assist.model.Patient;
import com.hospital.assist.service.PatientService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/patients")
@CrossOrigin(origins = "*")
public class PatientController {
    private final PatientService service;
    public PatientController(PatientService service){ this.service = service; }

    @GetMapping
    public List<Patient> all(){ return service.findAll(); }

    @GetMapping("/{id}")
    public ResponseEntity<Patient> get(@PathVariable Long id){
        Patient p = service.findById(id);
        return p == null ? ResponseEntity.notFound().build() : ResponseEntity.ok(p);
    }

    @PostMapping
    public ResponseEntity<Patient> create(@Valid @RequestBody Patient p){
        Patient saved = service.save(p);
        return ResponseEntity.ok(saved);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Patient> update(@PathVariable Long id, @Valid @RequestBody Patient p){
        try {
            return ResponseEntity.ok(service.update(id, p));
        } catch (RuntimeException ex) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id){
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}
