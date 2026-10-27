package com.hema.medical_backend_spring.api;

import java.util.List;
import java.util.Map;

import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.hema.medical_backend_spring.dto.DoctorResponseDto;
import com.hema.medical_backend_spring.dto.UpdateDoctorDto;
import com.hema.medical_backend_spring.mapper.DoctorMapper;
import com.hema.medical_backend_spring.mapper.HelperDto;
import com.hema.medical_backend_spring.model.Doctor;
import com.hema.medical_backend_spring.services.DoctorService;

import lombok.AllArgsConstructor;

@RestController 
@RequestMapping("/api/doctors")
@AllArgsConstructor
public class DoctorApi {

    private final DoctorService doctorService;
     @GetMapping("")
    public ResponseEntity<Map<String, Object>> getDoctors(
            @RequestParam(required = false) String specialty,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "4") int size) {

        Page<Doctor> doctorsPage = doctorService.getDoctorsBySpecialty(specialty, page, size);

        List<DoctorResponseDto> doctors = doctorsPage.getContent()
                .stream()
                .map(DoctorMapper::DoctorToDto)
                .toList();

        return ResponseEntity.ok().body(Map.of(
                "page", doctorsPage.getNumber(),
                "number", doctorsPage.getTotalPages(),
                "doctors", doctors));
    }

    @GetMapping("/search")
    public ResponseEntity<Map<String, Object>> searchDoctors(
            @RequestParam(required = false) String s,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "4") int size) {

        Page<Doctor> doctorsPage = doctorService.searchDoctors(s, page, size);

        List<DoctorResponseDto> doctors = doctorsPage.getContent()
                .stream()
                .map(DoctorMapper::DoctorToDto)
                .toList();

        return ResponseEntity.ok().body(Map.of(
                "doctors", doctors,
                "page", doctorsPage.getNumber(),
                "number", doctorsPage.getTotalPages()));
    }
    


    @PostMapping("/update-career-details")
    public ResponseEntity<Map<String, String>> updateCareerDetails(@ModelAttribute UpdateDoctorDto dto,
            Authentication authentication) {
        doctorService.updateDoctor(dto, authentication);
       return ResponseEntity.ok().body(Map.of("message", "تم تعديل البيانات بنجاح"));
    }

    // TODO request to be a doctor and the admin will accept or refuse the request

}
