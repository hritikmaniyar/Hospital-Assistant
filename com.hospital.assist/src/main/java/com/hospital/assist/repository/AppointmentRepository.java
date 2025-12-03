package com.hospital.assist.repository;

import com.hospital.assist.model.Appointment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AppointmentRepository extends JpaRepository<Appointment, Long> {
    List<Appointment> findByPatientId(Long patientId);

    List<Appointment> findByDoctorId(Long doctorId);

    List<Appointment> findByStatus(String status);

    @Query("SELECT a FROM Appointment a WHERE CURRENT_DATE = CAST(a.createdAt AS date)")
    List<Appointment> findTodaysAppointments();

    @Query("SELECT a FROM Appointment a WHERE a.status = 'Booked' AND a.datetime > :now ORDER BY a.datetime ASC")
    List<Appointment> findUpcomingAppointments(@Param("now") String now);
}
