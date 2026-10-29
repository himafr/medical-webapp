package com.hema.medical_backend_spring.api;

import java.security.Principal;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hema.medical_backend_spring.dto.ApiResponse;
// TODO DO
import com.hema.medical_backend_spring.dto.UpdateBloodTypeDto;
import com.hema.medical_backend_spring.dto.UpdatePatientEmergencyDto;
import com.hema.medical_backend_spring.services.PatientService;

import lombok.AllArgsConstructor;

@RestController 
@RequestMapping("/api/patient")
@AllArgsConstructor
public class PatientApi {
    private final PatientService patientService;

    @PutMapping ("/update-emergency-contact")
    public ResponseEntity<ApiResponse> updateEmergencyContact(@ModelAttribute UpdatePatientEmergencyDto dto, Principal principal) {
        patientService.updatePatientEmergency(dto, principal.getName());
        return ResponseEntity.ok(ApiResponse.success("تم تحديث بيانات جهة الاتصال الطارئة : "+ dto.getEmergencyContactName() + " - " + dto.getEmergencyContactPhone()));
    }

    @PutMapping ("/update-blood-type")
    public ResponseEntity<ApiResponse> updateBloodType(@ModelAttribute UpdateBloodTypeDto dto, Principal principal) {
        patientService.updateBloodType(dto, principal.getName());
        return ResponseEntity.ok(ApiResponse.success("تم تحديث نوع الدم : " + dto.getBloodType()));
    }

}
