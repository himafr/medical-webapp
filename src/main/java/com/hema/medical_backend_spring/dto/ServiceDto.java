package com.hema.medical_backend_spring.dto;

import java.util.List;

import com.hema.medical_backend_spring.model.Doctor.Specialty;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class ServiceDto {
    @NotNull (message = "اسم الخدمة مطلوب")
    private String name;
    @NotNull (message = "وصف الخدمة مطلوب")
    private String description;
    @NotNull (message = "سعر الخدمة مطلوب")
    private Double price;
    @NotNull (message = "تاريخ بدء الخدمة مطلوب")
    private Double startFrom;
    private Double consulating;
    @NotNull (message = "التخصص للخدمة مطلوب")
    private Specialty specialization;
    @NotNull (message = "مزايا الخدمة مطلوبة")
    private List<String> features; // غيرها لـ String بسيط
}
