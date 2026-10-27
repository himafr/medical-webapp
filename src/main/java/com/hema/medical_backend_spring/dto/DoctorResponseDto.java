package com.hema.medical_backend_spring.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder 
@NoArgsConstructor 
@AllArgsConstructor 
public class DoctorResponseDto {
    private Long id;
    private String fullName;
    private String email;
    private String photo;
    private String phoneNumber;
    private String gender;
    
    private String specialty;
    private String specialization;
    private String about;
    private String work;
}