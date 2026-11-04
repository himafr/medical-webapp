package com.hema.medical_backend_spring.services;

import java.util.Optional;

import org.springframework.lang.NonNull;
import org.springframework.stereotype.Service;

import com.hema.medical_backend_spring.dto.UpdateBloodTypeDto;
import com.hema.medical_backend_spring.dto.UpdatePatientEmergencyDto;
import com.hema.medical_backend_spring.model.MedicalRecord;
import com.hema.medical_backend_spring.model.Patient;
import com.hema.medical_backend_spring.repository.MedicalRecordRepository;
import com.hema.medical_backend_spring.repository.PatientRepository;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class PatientService {
    private final PatientRepository patientRepository;

    private final MedicalRecordRepository medicalRecordRepository;

    public Optional<Patient> getPatient(long userId) {
        return patientRepository.findById(userId);
    }

    public MedicalRecord getMedicalRecord(@NonNull Long patientId) {
        return medicalRecordRepository.findById(patientId)
                .orElseThrow(() -> new com.hema.medical_backend_spring.exception.UserNotFoundException("لم يتم العثور على الملف الطبي"));
    }

    public void updatePatientEmergency(UpdatePatientEmergencyDto dto, String email) {
        String name = (dto.getEmergencyContactName() == null || dto.getEmergencyContactName().isBlank()) ? null
                : dto.getEmergencyContactName();
        String address = (dto.getEmergencyContactPhone() == null || dto.getEmergencyContactPhone().isBlank()) ? null
                : dto.getEmergencyContactPhone();

        int updatedRows = patientRepository.updateEmergencyContact(email, name, address);

        if (updatedRows == 0) {
            throw new com.hema.medical_backend_spring.exception.UserNotFoundException("لم يتم العثور على المستخدم");
        }
    }

    public void updateBloodType(UpdateBloodTypeDto dto, String email) {
        int updatedRows = patientRepository.updateBloodType(email, dto.getBloodType());

        if (updatedRows == 0) {
            throw new com.hema.medical_backend_spring.exception.UserNotFoundException("لم يتم العثور على المستخدم");
        }
    }

}
