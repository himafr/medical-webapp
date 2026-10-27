package com.hema.medical_backend_spring.services;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;

import com.hema.medical_backend_spring.dto.DoctorApiDto;
import com.hema.medical_backend_spring.dto.DoctorResponseDto;
import com.hema.medical_backend_spring.dto.UpdateDoctorDto;
import com.hema.medical_backend_spring.mapper.HelperDto;
import com.hema.medical_backend_spring.model.Doctor;
import com.hema.medical_backend_spring.model.ProjectUser;
import com.hema.medical_backend_spring.model.Doctor.Specialty;
import com.hema.medical_backend_spring.repository.DoctorRepository;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class DoctorService {
    private final DoctorRepository doctorRepository;

    public Optional<Doctor> getDoctor(long userId) {
        return doctorRepository.findById(userId);
    }
    

    public void updateDoctor(UpdateDoctorDto dto, Authentication authentication) {
        if (authentication.getAuthorities().toArray()[0].toString().equals("DOCTOR")) {
    
    doctorRepository.updateDoctorPartial(
        HelperDto.getProjectUser(authentication).getId(),
        dto.getSpecialization(),
        dto.getWork(), dto.getAbout(),
        dto.getSpecialty());
    }else{
        throw new AccessDeniedException("لا يمكن للمريض تغير بيانات الدكتور ");
    }
    }

    public List<DoctorApiDto> findBySpecialization(Specialty specialization) {
        return doctorRepository.findBySpecialty(specialization, Pageable.unpaged())
                .stream()
                .map(d -> {
                    DoctorApiDto dto = new DoctorApiDto();
                    dto.setId(d.getId());
                    dto.setFullName(d.getFullName());
                    return dto;
                })
                .collect(Collectors.toList());
    }
// for refrences by now
    // public Page<Doctor> getDoctorsBySpecialty(String specialty, int page, int size) {
    //     Pageable pageable = PageRequest.of(page, size);

    //     if (specialty != null) {
    //         switch (specialty.toUpperCase()) {
    //             case "GENERAL":
    //                 return doctorRepository.findBySpecialty(Specialty.GENERAL, pageable);
    //             case "DENTISTRY":
    //                 return doctorRepository.findBySpecialty(Specialty.DENTISTRY, pageable);
    //             case "DERMATOLOGY":
    //                 return doctorRepository.findBySpecialty(Specialty.DERMATOLOGY, pageable);
    //             case "OPHTHALMOLOGY":
    //                 return doctorRepository.findBySpecialty(Specialty.OPHTHALMOLOGY, pageable);
    //             case "CARDIOLOGY":
    //                 return doctorRepository.findBySpecialty(Specialty.CARDIOLOGY, pageable);
    //             case "COSMETIC_DERMATOLOGY":
    //                 return doctorRepository.findBySpecialty(Specialty.COSMETIC_DERMATOLOGY, pageable);
    //             case "FAMILY_MEDICINE":
    //                 return doctorRepository.findBySpecialty(Specialty.FAMILY_MEDICINE, pageable);
    //             case "PEDIATRICS":
    //                 return doctorRepository.findBySpecialty(Specialty.PEDIATRICS, pageable);
    //             default:
    //                 return doctorRepository.findAll(pageable);
    //         }
    //     }
    //     return doctorRepository.findAll(pageable);
    // }

 public Page<Doctor> getDoctorsBySpecialtyAsDto(String specialty, int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        Specialty specEnum = parseSpecialty(specialty); 

        if (specEnum != null) {
          return   doctorRepository.findBySpecialty(specEnum, pageable);
        }
        return doctorRepository.findAll(pageable); 
    }

    public Page<Doctor> getDoctorsBySpecialty(String specialty, int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        Specialty specEnum = parseSpecialty(specialty); 

        if (specEnum != null) {
            return doctorRepository.findBySpecialty(specEnum, pageable);
        }
        return doctorRepository.findAll(pageable);
    }

   
    public Page<Doctor> searchDoctors(String s, int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        
        if (s != null) {
            return doctorRepository
                    .findByFullNameContainingIgnoreCaseOrAboutContainingIgnoreCaseOrSpecializationContainingIgnoreCaseOrWorkContainingIgnoreCase(
                            s, s, s, s, pageable);
        }
        return doctorRepository.findAll(pageable);
    }

    // public void updateBloodType(UpdateBloodTypeDto dto, String email) {
    // int updatedRows = patientRepository.updateBloodType(email,
    // dto.getBloodType());
    
    // if (updatedRows == 0) {
        // throw new RuntimeException("User not found");
        // }
        // }
        private Specialty parseSpecialty(String specialty) {
            if (specialty == null || specialty.trim().isEmpty()) {
                return null;
            }
            try {
                return Specialty.valueOf(specialty.toUpperCase().trim());
            } catch (IllegalArgumentException e) {
                return null;
            }
        }
}
