package com.hema.medical_backend_spring.mapper;

import com.hema.medical_backend_spring.dto.DoctorResponseDto;
import com.hema.medical_backend_spring.model.Doctor;

public class DoctorMapper {
    private DoctorMapper() {
    }

    public static DoctorResponseDto DoctorToDto(Doctor doctor) {
        if (doctor == null) {
            return null;
        }

        return DoctorResponseDto.builder()
                .id(doctor.getId())
                .fullName(doctor.getFullName())
                .email(doctor.getEmail())
                .photo(doctor.getPhoto())
                .phoneNumber(doctor.getPhoneNumber())
                .gender(doctor.getGender())
                .specialty(doctor.getSpecialty() != null ? doctor.getSpecialty().name() : null)
                .specialization(doctor.getSpecialization())
                .about(doctor.getAbout())
                .work(doctor.getWork())
                .build();
    }
}
