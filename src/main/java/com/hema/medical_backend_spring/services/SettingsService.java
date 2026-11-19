package com.hema.medical_backend_spring.services;

import com.hema.medical_backend_spring.dto.SettingsDto;
import com.hema.medical_backend_spring.model.ProjectUser;
import com.hema.medical_backend_spring.model.Settings;
import com.hema.medical_backend_spring.repository.SettingsRepository;
import com.hema.medical_backend_spring.repository.UserRepo;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class SettingsService {

    private final SettingsRepository settingsRepo;
    private final UserRepo userRepo;

    public Settings getUserSettings(Long userId) {
        return settingsRepo.findById(userId)
                .orElseGet(() -> {
                    ProjectUser user = userRepo.findById(userId)
                            .orElseThrow(() -> new RuntimeException("User not found"));
                    
                    Settings newSettings = new Settings();
                    newSettings.setUser(user);
                    
                    return settingsRepo.save(newSettings);
                });
    }

    @Transactional
    public Settings updateSettings(Long userId, SettingsDto dto) {
        Settings settings = getUserSettings(userId);

        if (dto.getLanguage() != null && !dto.getLanguage().isBlank()) {
            settings.setLanguage(dto.getLanguage());
        }
        
        if (dto.getFontSize() != null) {
            settings.setFontSize(dto.getFontSize());
        }
        
        if (dto.getDarkMode() != null) {
            settings.setDarkMode(dto.getDarkMode());
        }
        

        return settingsRepo.save(settings);
    }
}