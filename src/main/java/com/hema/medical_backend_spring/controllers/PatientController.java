package com.hema.medical_backend_spring.controllers;

import java.security.Principal;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.hema.medical_backend_spring.dto.UpdateBloodTypeDto;
import com.hema.medical_backend_spring.dto.UpdatePatientEmergencyDto;
import com.hema.medical_backend_spring.services.PatientService;

import lombok.AllArgsConstructor;

@Controller
@RequestMapping("/patient")
@AllArgsConstructor
public class PatientController {
    private final PatientService patientService;

    @PostMapping("/update-emergency-contact")
    public String updateEmergencyContact(@ModelAttribute UpdatePatientEmergencyDto dto, Principal principal,
            RedirectAttributes redirectAttributes) {
        patientService.updatePatientEmergency(dto, principal.getName());
        redirectAttributes.addFlashAttribute("successMessage", "تم تحديث جهة الاتصال للطوارئ بنجاح.");
        return "redirect:/home";
    }

    @PostMapping("/update-blood-type")
    public String updateBloodType(@ModelAttribute UpdateBloodTypeDto dto, Principal principal,
            RedirectAttributes redirectAttributes) {
        patientService.updateBloodType(dto, principal.getName());
        redirectAttributes.addFlashAttribute("successMessage", "تم تحديث فصيلة الدم بنجاح.");
        return "redirect:/home";
    }

}
