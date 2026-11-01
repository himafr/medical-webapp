package com.hema.medical_backend_spring.mapper;

import com.hema.medical_backend_spring.dto.PatientResponseDto;
import com.hema.medical_backend_spring.dto.UpdateBloodTypeDto;
import com.hema.medical_backend_spring.dto.UpdatePatientEmergencyDto;
import com.hema.medical_backend_spring.model.Patient;

public class PatientMapper {
    public static PatientResponseDto mapToPatientDto(Patient patient) {
        return PatientResponseDto.builder()
                .medicalRecord(patient.getMedicalRecord()).bloodType(patient.getBloodType())
                .diagnosis(patient.getDiagnosis()).emergencyContactName(patient.getEmergencyContactName())
                .emergencyContactPhone(patient.getEmergencyContactPhone()).build();
    }

    public static Patient mapFromEmergencyToPatient(UpdatePatientEmergencyDto dto, Patient user) {
        if (dto.getEmergencyContactName() != null && !dto.getEmergencyContactName().isBlank())
            user.setEmergencyContactName(dto.getEmergencyContactName());
        if (dto.getEmergencyContactPhone() != null && !dto.getEmergencyContactPhone().isBlank())
            user.setEmergencyContactPhone(dto.getEmergencyContactPhone());
        return user;
    }

    public static Patient mapFromBloodTypeToPatient(UpdateBloodTypeDto dto, Patient user) {
        if (dto.getBloodType() != null)
            user.setBloodType(dto.getBloodType());
        return user;
    }

    public static UpdatePatientEmergencyDto mapFromPatientToEmergency(Patient user,
            UpdatePatientEmergencyDto detailsDto) {
        detailsDto.setEmergencyContactName(user.getEmergencyContactName());
        detailsDto.setEmergencyContactPhone(user.getEmergencyContactPhone());
        return detailsDto;
    }

}
