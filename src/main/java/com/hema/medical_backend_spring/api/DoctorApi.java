package com.hema.medical_backend_spring.api;

import java.util.Map;

import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.hema.medical_backend_spring.dto.ApiResponse;
import com.hema.medical_backend_spring.dto.DoctorResponseDto;
import com.hema.medical_backend_spring.dto.UpdateDoctorDto;
import com.hema.medical_backend_spring.mapper.DoctorMapper;
import com.hema.medical_backend_spring.services.DoctorService;

import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/api/doctors")
@AllArgsConstructor
public class DoctorApi {

        private final DoctorService doctorService;

        @GetMapping("")
        public ResponseEntity<ApiResponse> getDoctors(
                        @RequestParam(required = false) String specialty,
                        @RequestParam(defaultValue = "0") int page,
                        @RequestParam(defaultValue = "4") int size) {

                Page<DoctorResponseDto> doctorsPage = doctorService.getDoctorsBySpecialty(specialty, page, size)
                                .map(DoctorMapper::DoctorToDto);

                ApiResponse res = ApiResponse.builder().status(200).message("جميع الدكاتره").data(doctorsPage)
                                .build();

                return ResponseEntity.ok(res);
        }

        @GetMapping("/search")
        public ResponseEntity<ApiResponse> searchDoctors(
                        @RequestParam(required = false) String s,
                        @RequestParam(defaultValue = "0") int page,
                        @RequestParam(defaultValue = "4") int size) {

                Page<DoctorResponseDto> doctorsPage = doctorService.searchDoctors(s, page, size)
                                .map(DoctorMapper::DoctorToDto);

                return ResponseEntity.ok()
                                .body(ApiResponse.builder().status(200).message("").data(doctorsPage).build());
        }

        @PutMapping("")
        public ResponseEntity<Map<String, String>> updateCareerDetails(@RequestBody  UpdateDoctorDto dto,
                        Authentication authentication) {
                doctorService.updateDoctor(dto, authentication);
                return ResponseEntity.ok().body(Map.of("message", "تم تعديل البيانات بنجاح"));
        }

}
