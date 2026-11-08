package com.hema.medical_backend_spring.api;

import java.security.Principal;
import java.util.Map;

import org.springframework.data.domain.Page;
import org.springframework.http.MediaType;
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

                ApiResponse res = ApiResponse.builder().status(200).message("تم عرض الأطباء بنجاح").data(doctorsPage)
                                .build();

                return ResponseEntity.ok(res);
        }

        @GetMapping("/me")
        public ResponseEntity<ApiResponse> getDoctor(
                        Principal principal) {
               DoctorResponseDto doctor= doctorService.getDoctor(principal.getName());
                ApiResponse res = ApiResponse.builder().status(200).message("تم عرض الطبيب بنجاح").data(doctor)
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
                                .body(ApiResponse.builder().status(200).message("تم عرض نتائج البحث بنجاح")
                                                .data(doctorsPage).build());
        }

        @PutMapping(value = "", consumes = MediaType.APPLICATION_JSON_VALUE)
        public ResponseEntity<Map<String, String>> updateCareerDetails(@RequestBody UpdateDoctorDto dto,
                        Authentication authentication) {
                doctorService.updateDoctor(dto, authentication);
                return ResponseEntity.ok().body(Map.of("message", "تم تعديل البيانات بنجاح"));
        }


        // @PutMapping(value = "", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
        // public ResponseEntity<Map<String, String>> updateCareerDetailsWithPhoto(
        //                 @RequestPart("data") UpdateDoctorDto dto,
        //                 @RequestPart(value = "photo", required = false) MultipartFile photo,
        //                 Authentication authentication) throws IOException {
        //         String photoUrl = photo != null && !photo.isEmpty()
        //                         ? s3Service.getImageUrl(s3Service.uploadImage(photo))
        //                         : null;
        //         doctorService.updateDoctor(dto, authentication, photoUrl);
        //         return ResponseEntity.ok().body(Map.of("message", "تم تعديل البيانات بنجاح"));
        // }

}
