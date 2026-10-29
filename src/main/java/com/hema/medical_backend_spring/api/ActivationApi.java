package com.hema.medical_backend_spring.api;

import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hema.medical_backend_spring.config.CustomUserDetails;
import com.hema.medical_backend_spring.dto.ApiResponse;
import com.hema.medical_backend_spring.dto.LoginResponse;
import com.hema.medical_backend_spring.services.JwtService;
import com.hema.medical_backend_spring.services.UserService;

import jakarta.servlet.http.HttpServletResponse;
import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/api/activation")
@AllArgsConstructor
public class ActivationApi {
    private final UserService userService;
    private final UserDetailsService userDetailsService;
    private final JwtService jwtService;

    @PostMapping("")
    public ResponseEntity<ApiResponse> sendPin(Authentication authentication) {
        String email = authentication.getName();
        userService.sendActivationPin(email);
        return ResponseEntity.ok(ApiResponse.success("activation pin has been sent to :" + email));
    }

    @PutMapping("")
    public ResponseEntity<ApiResponse> verifyPin(Authentication authentication,
            @RequestBody Map<String, String> b, HttpServletResponse response) {
        String pin = b.get("pin");
        String email = authentication.getName();
        boolean success = userService.activateAccount(email, pin, response);
        if (success) {

            CustomUserDetails userDetails = (CustomUserDetails) userDetailsService.loadUserByUsername(authentication.getName());
            String token = jwtService.generateToken(userDetails);

            return ResponseEntity.accepted()
                    .body(ApiResponse.builder().status(202).message("your account has been verified").data(token).build());
        }return ResponseEntity.badRequest().body(ApiResponse.error(400,"الكود غلط أو انتهت صلاحيته"));
}}
