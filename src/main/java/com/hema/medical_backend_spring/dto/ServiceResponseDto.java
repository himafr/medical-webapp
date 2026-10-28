package com.hema.medical_backend_spring.dto;

import com.hema.medical_backend_spring.model.Doctor.Specialty;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ServiceResponseDto {
  private Long id;
  private String name;
  private Double price;
  private Double startFrom;
  private Double consulating;
  private Specialty specialization;
}
