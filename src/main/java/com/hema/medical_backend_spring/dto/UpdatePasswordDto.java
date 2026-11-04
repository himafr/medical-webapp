package com.hema.medical_backend_spring.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class UpdatePasswordDto {
    @NotNull (message = "كلمة المرور الحالية مطلوبة")
    private String oldPass;
    @NotNull (message = "كلمة المرور الجديدة مطلوبة")
    private String newPass;
}

