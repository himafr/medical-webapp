package com.hema.medical_backend_spring.controllers;

import java.security.Principal;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.hema.medical_backend_spring.dto.UpdatePasswordDto;
import com.hema.medical_backend_spring.dto.UserPersonalDetailsDto;
import com.hema.medical_backend_spring.services.UserService;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import lombok.AllArgsConstructor;


@Controller
@AllArgsConstructor
@RequestMapping("/users") 
public class UserController {
    private final UserService userService;

    @PostMapping("/update-personal-details")
    public String updatePatient(@ModelAttribute UserPersonalDetailsDto dto, Principal principal,
            RedirectAttributes redirectAttributes) {
        userService.updateUser(dto, principal.getName());
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
