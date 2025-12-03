package com.hospital.assist.service;

import com.hospital.assist.model.Appointment;
import com.hospital.assist.repository.AppointmentRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AppointmentService {

    private final AppointmentRepository repository;

    public AppointmentService(AppointmentRepository repository) {
        this.repository = repository;
    }

    public List<Appointment> findAll() {
        return repository.findAll();
    }

    public List<Appointment> findByPatient(Long patientId) {
        return repository.findByPatientId(patientId);
    }

    public Appointment book(Appointment appointment) {
        appointment.setStatus("Booked");
        return repository.save(appointment);
    }

    public Appointment reschedule(Long id, String newDatetime) {
        Appointment existing = repository.findById(id).orElseThrow(() -> new RuntimeException("Appointment not found: " + id));
        existing.setDatetime(newDatetime);
        existing.setStatus("Booked");
        return repository.save(existing);
    }

    public Appointment cancel(Long id) {
        Appointment existing = repository.findById(id).orElseThrow(() -> new RuntimeException("Appointment not found: " + id));
        existing.setStatus("Cancelled");
        return repository.save(existing);
    }

    public Appointment assignDoctor(Long id, Long doctorId, String doctorName) {
        Appointment existing = repository.findById(id).orElseThrow(() -> new RuntimeException("Appointment not found: " + id));
        existing.setDoctorId(doctorId);
        existing.setDoctorName(doctorName);
        return repository.save(existing);
    }
}
