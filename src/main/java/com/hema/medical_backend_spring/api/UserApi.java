package com.hema.medical_backend_spring.api;

import java.security.Principal;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hema.medical_backend_spring.dto.ApiResponse;
import com.hema.medical_backend_spring.dto.UpdatePasswordDto;
import com.hema.medical_backend_spring.dto.UpdateUserPersonalDetailsDto;
import com.hema.medical_backend_spring.services.UserService;

import jakarta.servlet.http.HttpServletResponse;
import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/api/users")
@AllArgsConstructor
public class UserApi {
    private final UserService userService;

    @PutMapping("")
    public ResponseEntity<ApiResponse> updatePatient(@RequestBody UpdateUserPersonalDetailsDto dto,
            Principal principal) {
        try {
            userService.updateUser(dto, principal.getName());
            return ResponseEntity.ok(ApiResponse.success("تم تحديث البيانات بنجاح "));
        } catch (Exception e) {
            return ResponseEntity.status(400).body(ApiResponse.error(400, "خطا اثناء تحديث البيانات "));
        }
    }

    @PutMapping("/update-password")
    public ResponseEntity<ApiResponse> updatePassword(@RequestBody UpdatePasswordDto dto, Principal principal) {
        userService.updatePassword(dto, principal.getName());
       return ResponseEntity.ok(ApiResponse.success( "تم تغير الرقم السري بنجاح "));
    }

    @DeleteMapping("")
    public ResponseEntity<ApiResponse> postMethodName(Authentication authentication, HttpServletResponse response) {
        userService.deleteAccount(authentication);
        return ResponseEntity.ok(ApiResponse.success("تم حذف الحساب بنجاح "));
    }

}
