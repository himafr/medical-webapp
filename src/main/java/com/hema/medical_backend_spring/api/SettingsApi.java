package com.hema.medical_backend_spring.api;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hema.medical_backend_spring.dto.ApiResponse;
import com.hema.medical_backend_spring.dto.SettingsDto;
import com.hema.medical_backend_spring.mapper.HelperDto;
import com.hema.medical_backend_spring.services.SettingsService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/settings")
@RequiredArgsConstructor
public class SettingsApi {

    private final SettingsService settingsService;

    @GetMapping
    public ResponseEntity<ApiResponse> getMySettings(Authentication authentication) {
        Long userId = HelperDto.getProjectUser(authentication).getId(); 
        return ResponseEntity.ok(ApiResponse.success("",settingsService.getUserSettings(userId)));
    }

    @PutMapping
    public ResponseEntity<ApiResponse> updateMySettings(
            Authentication authentication,
            @RequestBody SettingsDto dto) {
        
        Long userId = HelperDto.getProjectUser(authentication).getId();
        return ResponseEntity.ok(ApiResponse.success("",settingsService.updateSettings(userId, dto)));
    }

}