package com.hema.medical_backend_spring.mapper;

import com.hema.medical_backend_spring.dto.ServiceResponseDto;
import com.hema.medical_backend_spring.model.MedicalServiceEntity;

public class ServiceMapper {
    public static ServiceResponseDto mapFromMedicalServiceToDto(MedicalServiceEntity service) {
        return ServiceResponseDto.builder().id(service.getId())
                .name(service.getName())
                .price(service.getPrice())
                .specialization(service.getSpecialization())
                .consulating(service.getConsulating())
                .startFrom(service.getStartFrom()).build();
    }

}
