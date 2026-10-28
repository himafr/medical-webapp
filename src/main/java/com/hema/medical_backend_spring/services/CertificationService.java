package com.hema.medical_backend_spring.services;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import com.hema.medical_backend_spring.dto.CertificationDto;
import com.hema.medical_backend_spring.exception.PageNotFoundException;
import com.hema.medical_backend_spring.mapper.HelperDto;
import com.hema.medical_backend_spring.model.sub.Certification;
import com.hema.medical_backend_spring.repository.CertificationRepository;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class CertificationService {
    private final CertificationRepository certificationRepository;

    public Certification getCertificationById(Long id, Authentication authentication) {
        return certificationRepository.findByIdAndDoctorId(id, HelperDto.getProjectUser(authentication).getId().longValue())
                .orElseThrow(() -> new PageNotFoundException("could not find a certificate with this id "));
    }

    public void addCertification(CertificationDto dto, Authentication authentication) {
        // TODO MAKE SOME FIELD REQUIRED
        certificationRepository.insertCertification(HelperDto.getProjectUser(authentication).getId(), dto.getName(),
                dto.getPlace(), dto.getOrganization(), dto.getPhoto());
    }

    public void editCertification(Certification certification, Authentication authentication) {
        certificationRepository.updateCertification(certification.getId(),
                HelperDto.getProjectUser(authentication).getId(), certification.getName(), certification.getPlace(),
                certification.getOrganization(), certification.getPhoto());
    }

    public int deleteCertification(long id, Authentication authentication) {
     return  certificationRepository.deleteByIdAndDoctorId(id, HelperDto.getProjectUser(authentication).getId());
    }

}
