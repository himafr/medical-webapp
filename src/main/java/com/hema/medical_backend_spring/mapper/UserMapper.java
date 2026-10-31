package com.hema.medical_backend_spring.mapper;

import org.springframework.boot.autoconfigure.security.SecurityProperties.User;

import com.hema.medical_backend_spring.dto.UserPersonalDetailsDto;
import com.hema.medical_backend_spring.model.ProjectUser;


public class UserMapper {

    public static UserPersonalDetailsDto mapToUserPersonalDetailsDto(ProjectUser user) {
        return UserPersonalDetailsDto.builder()
                .address(user.getAddress())
                .dateOfBirth(user.getDateOfBirth())
                .fullName(user.getFullName())
                .gender(user.getGender())
                .phoneNumber(user.getPhoneNumber())
                .build();
    }

    public static ProjectUser mapToProjectUser(UserPersonalDetailsDto dto, ProjectUser user) {
        if (dto.getAddress() != null && !dto.getAddress().isBlank())
            user.setAddress(dto.getAddress());
        if (dto.getDateOfBirth() != null)
            user.setDateOfBirth(dto.getDateOfBirth());
        if (dto.getFullName() != null && !dto.getFullName().isBlank())
            user.setFullName(dto.getFullName());
        if (dto.getGender() != null)
            user.setGender(dto.getGender());
        if (dto.getPhoneNumber() != null && !dto.getPhoneNumber().isBlank())
            user.setPhoneNumber(dto.getPhoneNumber());
        return user;
    }
}
