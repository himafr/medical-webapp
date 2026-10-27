package com.hema.medical_backend_spring.dto;

import com.hema.medical_backend_spring.model.Doctor.Specialty;

import lombok.Data;

@Data
public class UpdateDoctorDto {
  private Specialty specialty;
  private String specialization;
  private String work;
  private String about;

}
