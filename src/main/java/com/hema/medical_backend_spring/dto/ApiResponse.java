package com.hema.medical_backend_spring.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL) 
public class ApiResponse {
    private int status;
    private String message;
    private Object data; 

    public static ApiResponse success(String message, Object data) {
        return ApiResponse.builder()
                .status(200)
                .message(message)
                .data(data)
                .build();
    }

    public static ApiResponse success(String message) {
        return success(message, null);
    }

    public static ApiResponse error(int status, String message) {
        return ApiResponse.builder()
                .status(status)
                .message(message)
                .build();
    }
}