package com.hema.medical_backend_spring.dto;

import com.hema.medical_backend_spring.model.Settings.FontSize;

import lombok.Data;

/**
 * SettingsDto
 */
@Data 
public class SettingsDto {
private String language;
private Boolean darkMode; 
private FontSize fontSize; 
}
