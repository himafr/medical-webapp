package com.hema.medical_backend_spring.api;

import java.security.Principal;
import java.util.Map;

import org.springframework.boot.autoconfigure.couchbase.CouchbaseProperties.Authentication;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.hema.medical_backend_spring.dto.ApiResponse;
import com.hema.medical_backend_spring.dto.ServiceDto;
import com.hema.medical_backend_spring.dto.ServiceResponseDto;
import com.hema.medical_backend_spring.mapper.ServiceMapper;
import com.hema.medical_backend_spring.model.MedicalServiceEntity;
import com.hema.medical_backend_spring.services.MedicalServiceService;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;

@RestController
@RequestMapping("api/services")
@AllArgsConstructor
public class servicesApi {
    private final MedicalServiceService medicalServiceService;

    @GetMapping("")
    public ResponseEntity<ApiResponse> getServicesPage(Authentication authentication,
            @RequestParam(required = false) String specialty,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "4") int size) {
        Page<ServiceResponseDto> data = medicalServiceService.getServicesBySpecialty(specialty, page, size)
                .map(ServiceMapper::mapFromMedicalServiceToDto);

        ApiResponse res = ApiResponse.builder().status(200)
                .message("تم تحصيل الخدمات بنجاح ")
                .data(data).build();
        return ResponseEntity.ok(res);
    }

    // TODO ADD THE SEARCH METHOD

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse> getServiceById(Authentication authentication, @PathVariable long id) {
        MedicalServiceEntity service = medicalServiceService.getServiceById(id);
        Map<String, Object> data = Map.of("service", service, "features", service.getFeatures());
        ApiResponse res = ApiResponse.success("تم جلب بيانات الخدمه مع الرقم التعريفي " + id, data);
        return ResponseEntity.ok(res);
    }

    @PostMapping("")
    public ResponseEntity<ApiResponse> createService(@Valid @RequestBody  ServiceDto dto, Principal principal) {
        medicalServiceService.createService(dto, principal.getName());
        ApiResponse res = ApiResponse.builder().status(201)
                .message("تم انشاء الخدمة بنجاح بواسطة المستخدم " + principal.getName())
                .data(HttpStatus.CREATED).build();
        return ResponseEntity.status(HttpStatus.CREATED).body(res);
    }

    // TODO CHECK THESE SERVICES LATER
    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse> updateService(@RequestBody ServiceDto dto, Principal principal,
            RedirectAttributes redirectAttributes, @PathVariable long id) {
        medicalServiceService.updateService(id, dto);
        return ResponseEntity.status(HttpStatus.OK).body(ApiResponse.success("تم تحديث الخدمه بنجاح ."));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse> deleteService(RedirectAttributes redirectAttributes,
            @PathVariable long id) {
        medicalServiceService.deleteService(id);
        return ResponseEntity.status(HttpStatus.OK).body(ApiResponse.success("تم حذف الخدمه بنجاح ."));
    }

}
