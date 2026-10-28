package com.hema.medical_backend_spring.dto;

import com.hema.medical_backend_spring.model.ProjectUser;

import lombok.Getter;
import lombok.Setter;

@Getter 
@Setter 
public class RegisterRequest extends ProjectUser {
    private String passwordCheck;
}
