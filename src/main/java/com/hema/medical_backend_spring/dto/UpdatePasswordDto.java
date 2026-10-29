package com.hema.medical_backend_spring.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class UpdatePasswordDto {
    @NotNull (message = "الرقم السري القديم مطلوب")
    private String oldPass;
    @NotNull (message = "الرقم السري الجديد مطلوب")
    private String newPass;
}

