package com.hema.medical_backend_spring.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class CertificationDto {
     @NotNull (message = "اسم الشهاده مطلوب")
     private String name;
     @NotNull (message = "مكان الحصول على الشهاده مطلوب")
     private String place;
     @NotNull (message = "اسم المؤسسة مطلوب")
     private String organization;
     private String photo;
}
