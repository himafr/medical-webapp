package com.hema.medical_backend_spring.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.hema.medical_backend_spring.dto.AppointmentResponseDto;
import com.hema.medical_backend_spring.model.Appointment;
import com.hema.medical_backend_spring.model.Patient;

public interface AppointmentRepository extends JpaRepository<Appointment, Long> {
    List<Appointment> findByDoctorId(Long doctorId);

    List<AppointmentResponseDto> findByDoctorIdAndAppointmentDate(Long doctorId, LocalDate date);

    Page<Appointment> findByPatient(Patient patient, Pageable pageable);

    @Query("SELECT COUNT(a) FROM Appointment a WHERE a.doctor.id = :doctorId AND a.appointmentDate = :date")
    Long countByDoctorIdAndDate(@Param("doctorId") Long doctorId, @Param("date") LocalDate date);

}