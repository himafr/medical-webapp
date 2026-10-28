package com.hema.medical_backend_spring.api;

import java.security.Principal;

import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
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

    @PostMapping("/update-emergency-contact")
    public String updateEmergencyContact(@ModelAttribute UpdatePatientEmergencyDto dto, Principal principal) {
        patientService.updatePatientEmergency(dto, principal.getName());
        return "redirect:/home";
    }

    @PostMapping("/update-blood-type")
    public String updateBloodType(@ModelAttribute UpdateBloodTypeDto dto, Principal principal) {
        patientService.updateBloodType(dto, principal.getName());
        return "redirect:/home";
    }

}
