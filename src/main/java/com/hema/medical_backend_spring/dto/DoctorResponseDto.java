package com.hema.medical_backend_spring.dto;

import java.util.List;

import com.hema.medical_backend_spring.model.sub.Certification;

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
    private List<Certification> certifications;

}