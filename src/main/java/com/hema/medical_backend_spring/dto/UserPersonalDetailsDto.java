package com.hema.medical_backend_spring.dto;

import java.time.LocalDate;

import lombok.Builder;
import lombok.Data;

@Data
@Builder 
public class UserPersonalDetailsDto {
    private String fullName;
    private String phoneNumber;
    private LocalDate dateOfBirth;
    private String gender;
    private String address;
}
