package com.hema.medical_backend_spring.controllers;

import java.io.IOException;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.hema.medical_backend_spring.dto.CertificationDto;
import com.hema.medical_backend_spring.mapper.CertificationMapper;
import com.hema.medical_backend_spring.model.sub.Certification;
import com.hema.medical_backend_spring.services.CertificationService;
import com.hema.medical_backend_spring.services.S3Service;

import lombok.AllArgsConstructor;

@Controller
@RequestMapping("doctors")
@AllArgsConstructor
public class CertificationController {
    private final CertificationService certificationService;
    private final S3Service s3Service;

    @GetMapping("/certification/{id}")
    public String getCertificationById(@PathVariable Long id, Model model, Authentication authentication) {
        Certification certification = certificationService.getCertificationById(id, authentication);
        model.addAttribute("certification", certification);
        return "certification/edit-certification";
    }

    @GetMapping("/add-certification")
    public String getAddCertificationPage() {
        return new String("certification/add-certification");
    }

    @PostMapping("/add-certification")
    public String addCertification(@ModelAttribute CertificationDto certification, Authentication authentication,
            @RequestParam(value = "photoFile", required = false) MultipartFile photo,
            RedirectAttributes redirectAttributes) throws IOException {
        if (photo != null && !photo.isEmpty()) {
            certification.setPhoto(s3Service.getImageUrl(s3Service.uploadImage(photo)));
        }
        certificationService.addCertification(certification, authentication);
        redirectAttributes.addFlashAttribute("successMessage", "تمت إضافة الشهادة بنجاح");
        return "redirect:/success";
    }

    @PostMapping("/edit-certification/{id}")
    public String editCertification(@ModelAttribute CertificationDto certification, Authentication authentication,
            @RequestParam(value = "photoFile", required = false) MultipartFile photo,
            RedirectAttributes redirectAttributes, @PathVariable Long id) throws IOException {
        if (photo != null && !photo.isEmpty()) {
            certification.setPhoto(s3Service.getImageUrl(s3Service.uploadImage(photo)));
        }
        certificationService.editCertification(CertificationMapper.toEntity(certification, id, null), authentication);
        redirectAttributes.addFlashAttribute("successMessage", "تم تعديل الشهادة بنجاح");
        return "redirect:/success";
    }

    @PostMapping("/delete-certification/{id}")
    public String deleteCertification(@ModelAttribute CertificationDto certification, Authentication authentication,
            RedirectAttributes redirectAttributes, @PathVariable Long id) {
       int result=certificationService.deleteCertification(id, authentication);
       if (result==1) {
           redirectAttributes.addFlashAttribute("successMessage", "تم حذف الشهادة بنجاح");
           return "redirect:/success";
        }else{
           redirectAttributes.addFlashAttribute("failMessage", "تعذر حذف الشهادة. يرجى المحاولة مرة أخرى");
           return "redirect:/fail";

       }
    }

}
