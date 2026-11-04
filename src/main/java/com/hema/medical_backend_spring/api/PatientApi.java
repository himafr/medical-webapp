package com.hema.medical_backend_spring.api;

import java.security.Principal;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hema.medical_backend_spring.dto.ApiResponse;
import com.hema.medical_backend_spring.dto.PatientResponseDto;
import com.hema.medical_backend_spring.dto.UpdateBloodTypeDto;
import com.hema.medical_backend_spring.dto.UpdatePatientEmergencyDto;
import com.hema.medical_backend_spring.mapper.HelperDto;
import com.hema.medical_backend_spring.mapper.PatientMapper;
import com.hema.medical_backend_spring.services.PatientService;

import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/api/patient")
@AllArgsConstructor
public class PatientApi {
    private final PatientService patientService;

    @GetMapping("")
    public ResponseEntity<ApiResponse> getPatient(Authentication authentication) {
        Long patientId = HelperDto.getProjectUser(authentication).getId();
        return ResponseEntity.ok(ApiResponse.success("تم عرض بيانات المريض بنجاح",
                patientService.getPatient(patientId).map(PatientMapper::mapToPatientDto)));
    }

    @SuppressWarnings("null")
    @GetMapping("/medical-record")
    public ResponseEntity<ApiResponse> getMedicalRecord(Authentication authentication) {
        Long patientId = HelperDto.getProjectUser(authentication).getId();
        return ResponseEntity.ok(ApiResponse.success("تم عرض الملف الطبي بنجاح",
                patientService.getMedicalRecord(patientId)));
    }

    @PutMapping("/update-emergency-contact")
    public ResponseEntity<ApiResponse> updateEmergencyContact(@RequestBody UpdatePatientEmergencyDto dto,
            Principal principal) {
        patientService.updatePatientEmergency(dto, principal.getName());
        return ResponseEntity.ok(ApiResponse.success("تم تحديث بيانات جهة الاتصال للطوارئ بنجاح"));
    }

    @PutMapping("/update-blood-type")
    public ResponseEntity<ApiResponse> updateBloodType(@RequestBody UpdateBloodTypeDto dto, Principal principal) {
        patientService.updateBloodType(dto, principal.getName());
        return ResponseEntity.ok(ApiResponse.success("تم تحديث فصيلة الدم بنجاح"));
    }

}
