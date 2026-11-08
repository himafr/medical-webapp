package com.hema.medical_backend_spring.config;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;

import com.hema.medical_backend_spring.services.JwtService;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;

@ControllerAdvice
@RequiredArgsConstructor
public class GlobalModelAdvice {

    private final JwtService jwtService;

    @ModelAttribute
    public void addUserToModel(HttpServletRequest request, Model model) {
        
        Cookie[] cookies = request.getCookies();
        if (cookies == null) return;

        for (Cookie cookie : cookies) {
            if (cookie.getName().equals("jwt")) {
                String token = cookie.getValue();
                try {
                    model.addAttribute("userId", jwtService.extractUserId(token));
                    model.addAttribute("username", jwtService.extractEmail(token));
                    model.addAttribute("role", jwtService.extractRole(token));
                    model.addAttribute("isActive", jwtService.extractIsActive(token));
                    model.addAttribute("photoUrl", jwtService.extractPhotoUrl(token));
                } catch (Exception ignored) {}
                break;
            }
        }
    }
}