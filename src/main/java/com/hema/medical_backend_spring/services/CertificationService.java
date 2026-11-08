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
    private final S3Service s3Service;

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
        Long doctorId = HelperDto.getProjectUser(authentication).getId();
        Certification existing = getCertificationById(certification.getId(), authentication);
        int updatedRows = certificationRepository.updateCertification(certification.getId(),
                doctorId, certification.getName(), certification.getPlace(),
                certification.getOrganization(), certification.getPhoto());

        if (updatedRows > 0 && certification.getPhoto() != null
                && !certification.getPhoto().equals(existing.getPhoto())) {
            s3Service.deleteImageByUrl(existing.getPhoto());
        }
    }

    public int deleteCertification(long id, Authentication authentication) {
        Certification certification = getCertificationById(id, authentication);
        int deletedRows = certificationRepository.deleteByIdAndDoctorId(id,
                HelperDto.getProjectUser(authentication).getId());
        if (deletedRows > 0) {
            s3Service.deleteImageByUrl(certification.getPhoto());
        }
        return deletedRows;
    }

}
