package com.hema.medical_backend_spring.controllers;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.hema.medical_backend_spring.config.CustomUserDetails;
import com.hema.medical_backend_spring.model.Admin;
import com.hema.medical_backend_spring.model.Doctor;
import com.hema.medical_backend_spring.model.Patient;
import com.hema.medical_backend_spring.services.AdminService;
import com.hema.medical_backend_spring.services.AppointmentService;
import com.hema.medical_backend_spring.services.DoctorService;
import com.hema.medical_backend_spring.services.PatientService;

import lombok.AllArgsConstructor;

@Controller
@AllArgsConstructor
public class HomeController {
    private final PatientService patientService;
    private final DoctorService doctorService;
    private final AdminService adminService;
    private final AppointmentService appointmentService;

    // TODO make notification work

    @GetMapping(path = { "/home", "/" })
    public String getHomePage(Authentication authentication, Model model) {

        if (authentication != null && authentication.isAuthenticated()) {
            CustomUserDetails userDetails = (CustomUserDetails) authentication.getPrincipal();
            if (authentication.getAuthorities().toArray()[0].toString().equals("PATIENT")) {
                Patient patient = patientService.getPatient(userDetails.getProjectUser().getId()).orElse(null);
                model.addAttribute("medicalRecord", patient.getMedicalRecord());
                model.addAttribute("user", patient);
                model.addAttribute("appointments", appointmentService.getAppointmentsForPatient(userDetails.getProjectUser().getEmail(), 0, 5).getContent());
                return "profile/patient";
            } else if (authentication.getAuthorities().toArray()[0].toString().equals("DOCTOR")) {
                Doctor doctor = doctorService.getDoctor(userDetails.getProjectUser().getId()).orElse(null);
                String[] aboutSentences = doctor.getAbout() != null
                        ? doctor.getAbout().split("\\.")
                        : new String[0];
                model.addAttribute("education", doctor.getCertifications());
                model.addAttribute("about", aboutSentences);
                model.addAttribute("user", doctor);
                return "profile/doctor";
            } else if (authentication.getAuthorities().toArray()[0].toString().equals("ADMIN")) {
                Admin admin = adminService.getAdmin(userDetails.getProjectUser().getId()).orElse(null);
                
                model.addAttribute("user", admin);
                return "profile/admin";
            }
            return "/home";
        }
        return new String("home");
    }

  
}
