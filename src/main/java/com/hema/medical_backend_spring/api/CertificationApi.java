package com.hema.medical_backend_spring.api;

import java.io.IOException;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.hema.medical_backend_spring.dto.ApiResponse;
// TODO DO
import com.hema.medical_backend_spring.dto.CertificationDto;
import com.hema.medical_backend_spring.mapper.CertificationMapper;
import com.hema.medical_backend_spring.model.sub.Certification;
import com.hema.medical_backend_spring.services.CertificationService;
import com.hema.medical_backend_spring.services.S3Service;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/api/doctors/certification")
@AllArgsConstructor
public class CertificationApi {
    private final CertificationService certificationService;
    private final S3Service s3Service;

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse> getCertificationById(@PathVariable Long id,
            Authentication authentication) {
        Certification certification = certificationService.getCertificationById(id, authentication);
        return ResponseEntity.ok(ApiResponse.success("تم جلب بيانات الشهادة بنجاح", certification));
    }

    @PostMapping("")
    public ResponseEntity<ApiResponse> addCertification(@Valid @RequestBody CertificationDto certification,
            Authentication authentication) {
        certificationService.addCertification(certification, authentication);
        return ResponseEntity.ok(ApiResponse.success("تمت إضافة الشهادة بنجاح"));
    }

    @PostMapping(value = "", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<ApiResponse> addCertificationWithPhoto(
            @Valid @RequestPart("data") CertificationDto certification,
            @RequestPart(value = "photo", required = false) MultipartFile photo,
            Authentication authentication) throws IOException {
        if (photo != null && !photo.isEmpty()) {
            certification.setPhoto(s3Service.getImageUrl(s3Service.uploadImage(photo)));
        }
        certificationService.addCertification(certification, authentication);
        return ResponseEntity.ok(ApiResponse.success("تمت إضافة الشهادة بنجاح"));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse> editCertification( @RequestBody CertificationDto certification,
            Authentication authentication, @PathVariable Long id) {
        certificationService.editCertification(CertificationMapper.toEntity(certification, id, null), authentication);
        return ResponseEntity.ok(ApiResponse.success("تم تعديل الشهادة بنجاح"));
    }

    @PutMapping(value = "/{id}", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<ApiResponse> editCertificationWithPhoto(
            @Valid @RequestPart("data") CertificationDto certification,
            @RequestPart(value = "photo", required = false) MultipartFile photo,
            Authentication authentication, @PathVariable Long id) throws IOException {
        if (photo != null && !photo.isEmpty()) {
            certification.setPhoto(s3Service.getImageUrl(s3Service.uploadImage(photo)));
        }
        certificationService.editCertification(CertificationMapper.toEntity(certification, id, null), authentication);
        return ResponseEntity.ok(ApiResponse.success("تم تعديل الشهادة بنجاح"));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse> deleteCertification(
            Authentication authentication, @PathVariable Long id) {
        int result = certificationService.deleteCertification(id, authentication);
        if (result == 1) {
            return ResponseEntity.ok(ApiResponse.success("تم حذف الشهادة بنجاح"));
        } else {
            return ResponseEntity.badRequest().body(ApiResponse.error(400, "تعذر حذف الشهادة. يرجى المحاولة مرة أخرى"));

        }
    }

}
