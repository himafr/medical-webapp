package com.hema.medical_backend_spring.services;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Service;

import com.hema.medical_backend_spring.dto.ServiceDto;
import com.hema.medical_backend_spring.dto.ServiceResponseDto;
import com.hema.medical_backend_spring.model.Admin;
import com.hema.medical_backend_spring.model.Doctor.Specialty;
import com.hema.medical_backend_spring.model.MedicalServiceEntity;
import com.hema.medical_backend_spring.model.sub.ServiceFeatures;
import com.hema.medical_backend_spring.repository.AdminRepository;
import com.hema.medical_backend_spring.repository.MedicalServiceEntityRepository;

import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class MedicalServiceService {
    private final MedicalServiceEntityRepository medicalServiceRepository;
    private final AdminRepository adminRepository;

    
    public Page<MedicalServiceEntity> getServicesBySpecialty(String specialty,int page,int size ) {
        Pageable pageable = PageRequest.of(page, size);
        if (specialty != null) {
            switch (specialty.toUpperCase()) {
                case "GENERAL":
                    return medicalServiceRepository.findBySpecialization(Specialty.GENERAL,pageable);
                case "DENTISTRY":
                    return medicalServiceRepository.findBySpecialization(Specialty.DENTISTRY,pageable);
                case "DERMATOLOGY":
                    return medicalServiceRepository.findBySpecialization(Specialty.DERMATOLOGY,pageable);
                case "OPHTHALMOLOGY":
                    return medicalServiceRepository.findBySpecialization(Specialty.OPHTHALMOLOGY,pageable);
                case "CARDIOLOGY":
                    return medicalServiceRepository.findBySpecialization(Specialty.CARDIOLOGY,pageable);
                case "COSMETIC_DERMATOLOGY":
                    return medicalServiceRepository.findBySpecialization(Specialty.COSMETIC_DERMATOLOGY,pageable);
                case "FAMILY_MEDICINE":
                    return medicalServiceRepository.findBySpecialization(Specialty.FAMILY_MEDICINE,pageable);
                case "PEDIATRICS":
                    return medicalServiceRepository.findBySpecialization(Specialty.PEDIATRICS,pageable);
                default:
                    return medicalServiceRepository.findAll(pageable);
            }
        }
        return medicalServiceRepository.findAll(pageable);
    }
    public List<ServiceResponseDto> findBySpecialization(Specialty specialization) {
    return medicalServiceRepository.findBySpecialization(specialization ,Pageable.unpaged())
            .stream()
            .map(s -> {
                ServiceResponseDto dto = new ServiceResponseDto();
                dto.setId(s.getId());
                dto.setName(s.getName());
                dto.setPrice(s.getPrice());
                dto.setStartFrom(s.getStartFrom());
                dto.setConsulating(s.getConsulating());
                return dto;
            })
            .collect(Collectors.toList());
}

    public MedicalServiceEntity getServiceById(@NonNull Long id) {
        return medicalServiceRepository.findById(id).orElseThrow(() -> new RuntimeException("الخدمة التي تبحث عنها برقم "+id +"غير متوفره حاليا"));
    }

    public void createService(ServiceDto dto, String adminEmail) {
        Admin admin = adminRepository.findByEmail(adminEmail)
                .orElseThrow(() -> new RuntimeException("ليس لديك صلاحيه لتعديل بيانات الخدمة"));

        MedicalServiceEntity service = new MedicalServiceEntity();
        service.setAdmin(admin);
        service.setName(dto.getName());
        service.setDescription(dto.getDescription());
        service.setPrice(dto.getPrice());
        service.setStartFrom(dto.getStartFrom());
        service.setConsulating(dto.getConsulating());
        service.setSpecialization(dto.getSpecialization());

        if (dto.getFeatures() != null) {
            List<ServiceFeatures> features = dto.getFeatures()
                    .stream()
                    .filter(f -> f != null && !f.isBlank())
                    .map(f -> {
                        ServiceFeatures feature = new ServiceFeatures();
                        feature.setFeature(f);
                        feature.setMedicalService(service);
                        return feature;
                    })
                    .collect(Collectors.toList());
            service.setFeatures(features);
        }

        medicalServiceRepository.save(service);
    }

    @Transactional
    public void updateService(@NonNull Long id, ServiceDto dto) {
        medicalServiceRepository.updateServicePartial(
                id,
                dto.getName(),
                dto.getDescription(),
                dto.getPrice(),
                dto.getStartFrom(),
                dto.getConsulating(),
                dto.getSpecialization());

        if (dto.getFeatures() != null) {
            MedicalServiceEntity service = medicalServiceRepository.findById(id)
                    .orElseThrow(() -> new RuntimeException("Service not found"));

            service.getFeatures().clear();

            dto.getFeatures().stream()
                    .filter(f -> f != null && !f.isBlank())
                    .map(f -> {
                        ServiceFeatures feature = new ServiceFeatures();
                        feature.setFeature(f);
                        feature.setMedicalService(service);
                        return feature;
                    })
                    .forEach(service.getFeatures()::add);

            medicalServiceRepository.save(service);
        }
    }

    public void deleteService(long id) {
        medicalServiceRepository.deleteById(id);
    }
    
}