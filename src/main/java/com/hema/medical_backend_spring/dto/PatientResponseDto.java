package com.hema.medical_backend_spring.dto;

import com.hema.medical_backend_spring.model.Diagnosis;
import com.hema.medical_backend_spring.model.MedicalRecord;
import com.hema.medical_backend_spring.model.Patient.BloodType;

import lombok.Builder;
import lombok.Data;

@Data 
@Builder
public class PatientResponseDto {

    String emergencyContactName;
    String emergencyContactPhone;
    BloodType bloodType;
    Diagnosis diagnosis;
    MedicalRecord medicalRecord;

}
