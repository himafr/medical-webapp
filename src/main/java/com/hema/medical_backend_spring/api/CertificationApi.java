package com.hema.medical_backend_spring.api;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.hema.medical_backend_spring.dto.ApiResponse;
// TODO DO
import com.hema.medical_backend_spring.dto.CertificationDto;
import com.hema.medical_backend_spring.mapper.CertificationMapper;
import com.hema.medical_backend_spring.model.sub.Certification;
import com.hema.medical_backend_spring.services.CertificationService;

import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/api/doctors/certification")
@AllArgsConstructor
public class CertificationApi {
    private final CertificationService certificationService;

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse> getCertificationById(@PathVariable Long id,
            Authentication authentication) {
        Certification certification = certificationService.getCertificationById(id, authentication);
        return ResponseEntity.ok(ApiResponse.success(" cert", certification));
    }

    @PostMapping("")
    public ResponseEntity<ApiResponse> addCertification(@RequestBody CertificationDto certification,
            Authentication authentication) {
        certificationService.addCertification(certification, authentication);
        return ResponseEntity.ok(ApiResponse.success("تم اضافة الشهاده بنجاح"));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse> editCertification(@RequestBody CertificationDto certification,
            Authentication authentication, @PathVariable Long id) {
        certificationService.editCertification(CertificationMapper.toEntity(certification, id, null), authentication);
        return ResponseEntity.ok(ApiResponse.success("تم تعديل الشهاده بنجاح"));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse> deleteCertification(@RequestBody CertificationDto certification,
            Authentication authentication, @PathVariable Long id) {
        int result = certificationService.deleteCertification(id, authentication);
        if (result == 1) {
            return ResponseEntity.ok(ApiResponse.success("تم حذف الشهاده بنجاح"));
        } else {
            return ResponseEntity.ok(ApiResponse.error(400, "عذرا حدث خطا اثناء مسح الشهاده "));

        }
    }

}
