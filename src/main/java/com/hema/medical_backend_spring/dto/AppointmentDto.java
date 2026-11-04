package com.hema.medical_backend_spring.dto;

import java.time.LocalDate;
import java.time.LocalTime;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class AppointmentDto {
    @NotNull(message = "معرّف الطبيب مطلوب")
    private Long doctorId;
    @NotNull(message = "معرّف الخدمة مطلوب")
    private Long serviceId;
    @NotNull(message = "تاريخ الموعد مطلوب") 
    private LocalDate appointmentDate;
    @NotNull(message = "وقت الموعد مطلوب") 
    private LocalTime appointmentTime;
    private String notes;
}