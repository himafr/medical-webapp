package com.hema.medical_backend_spring.controllers;

import java.io.IOException;
import java.security.Principal;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.hema.medical_backend_spring.dto.UpdatePasswordDto;
import com.hema.medical_backend_spring.dto.UserPersonalDetailsDto;
import com.hema.medical_backend_spring.services.S3Service;
import com.hema.medical_backend_spring.services.UserService;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import lombok.AllArgsConstructor;


@Controller
@AllArgsConstructor
@RequestMapping("/users") 
public class UserController {
    private final UserService userService;
    private final S3Service s3Service;

    @PostMapping("/update-personal-details")
    public String updatePatient(@ModelAttribute UserPersonalDetailsDto dto, Principal principal,
            @RequestParam(value = "photoFile", required = false) MultipartFile photo,
            RedirectAttributes redirectAttributes, HttpServletResponse response) throws IOException {
        String photoUrl = photo != null && !photo.isEmpty()
                ? s3Service.getImageUrl(s3Service.uploadImage(photo))
                : null;
        userService.updateUser(dto, principal.getName(), photoUrl);
        if (photoUrl != null) {
            userService.refreshJwtToken(principal.getName(),photoUrl, response);
        }
        redirectAttributes.addFlashAttribute("successMessage", "تم تحديث بياناتك الشخصية بنجاح.");
        return "redirect:/home";
    }
 @PostMapping("/update-password")
public String updatePassword(@ModelAttribute UpdatePasswordDto dto, Principal principal,RedirectAttributes redirectAttributes) {
    userService.updatePassword(dto, principal.getName());
    redirectAttributes.addFlashAttribute("successMessage", "تم تغيير كلمة المرور بنجاح");
    return "redirect:/success";
}

@PostMapping("/delete-account")
public String postMethodName(Authentication authentication ,HttpServletResponse response,
        RedirectAttributes redirectAttributes) {
  userService.deleteAccount(authentication);
   Cookie jwtCookie = new Cookie("jwt", null);
    jwtCookie.setPath("/");
    jwtCookie.setHttpOnly(true);
    jwtCookie.setMaxAge(0); 
    response.addCookie(jwtCookie);
    redirectAttributes.addFlashAttribute("successMessage", "تم حذف الحساب بنجاح.");
    return "redirect:/login";
}


}
