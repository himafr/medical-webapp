package com.hema.medical_backend_spring.controllers;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.hema.medical_backend_spring.dto.UpdateDoctorDto;
import com.hema.medical_backend_spring.mapper.HelperDto;
import com.hema.medical_backend_spring.model.Doctor;
import com.hema.medical_backend_spring.services.DoctorService;

import lombok.AllArgsConstructor;

@Controller
@RequestMapping("/doctors")
@AllArgsConstructor
public class DoctorController {
    private final DoctorService doctorService;

    @GetMapping("")
    public String getDoctors(@RequestParam(required = false) String specialty,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "4") int size, Model model) {
        Page<Doctor> doctorsPage = doctorService.getDoctorsBySpecialty(specialty, page, size);
        List<Doctor> doctors = doctorsPage.getContent();
        model.addAttribute("page", doctorsPage.getNumber());
        model.addAttribute("number", doctorsPage.getTotalPages());
        model.addAttribute("doctors", doctors);
        return "doctors";
    }

    @GetMapping("/search")
    public String searchDoctors(@RequestParam(required = false) String s, @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "4") int size, Model model) {
        Page<Doctor> doctorsPage = doctorService.searchDoctors(s, page, size);
        List<Doctor> doctors = doctorsPage.getContent();
        model.addAttribute("doctors", doctors);
        model.addAttribute("page", doctorsPage.getNumber());
        model.addAttribute("number", doctorsPage.getTotalPages());
        return "doctors";
    }

    @GetMapping("/update-career-details")
    public String getCareerPage(Authentication authentication, Model model) {
        Doctor doctor = doctorService.getDoctor(
                HelperDto.getProjectUser(authentication).getId())
                .orElseThrow(() -> new RuntimeException("Doctor not found"));

        model.addAttribute("doctor", doctor);
        return "doctor/update-details";
    }

    @PostMapping("/update-career-details")
    public String updateCareerDetails(@ModelAttribute UpdateDoctorDto dto, Authentication authentication,
            RedirectAttributes redirectAttributes) {
        doctorService.updateDoctor(dto, authentication);
        redirectAttributes.addFlashAttribute("successMessage", "تم تعديل البيانات بنجاح");
        return "redirect:/success";
    }

    // TODO request to be a doctor and the admin will accept or refuse the request

}
