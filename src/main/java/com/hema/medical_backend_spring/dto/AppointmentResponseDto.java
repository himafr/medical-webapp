package com.hema.medical_backend_spring.dto;

import java.time.LocalDate;
import java.time.LocalTime;

import com.hema.medical_backend_spring.model.Appointment;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Getter
@Setter
public class AppointmentResponseDto {
    private Long id;
    private LocalDate appointmentDate;
    private LocalTime appointmentTime;
    private Appointment.AppointmentStatus status;
    private String notes;

    // بيانات المريض اللي إنت محتاجها بس
    private Long patientId;
    private String patientFullName;
    private String patientPhoneNumber;
    private String patientPhoto;

    // بيانات الدكتور (لو محتاجها)
    private String doctorFullName;
}
