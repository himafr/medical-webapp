package com.hema.medical_backend_spring.api;

import java.io.IOException;
import java.security.Principal;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.hema.medical_backend_spring.dto.ApiResponse;
import com.hema.medical_backend_spring.dto.UpdatePasswordDto;
import com.hema.medical_backend_spring.dto.UserPersonalDetailsDto;
import com.hema.medical_backend_spring.mapper.UserMapper;
import com.hema.medical_backend_spring.services.S3Service;
import com.hema.medical_backend_spring.services.UserService;

import jakarta.servlet.http.HttpServletResponse;
import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/api/users")
@AllArgsConstructor
public class UserApi {
    private final UserService userService;
    private final S3Service s3Service;

    @GetMapping("")
    public ResponseEntity<ApiResponse> updateUser(Principal principal) {
        try {

            return ResponseEntity.ok(ApiResponse.success("تم جلب البيانات بنجاح ",
                    userService.findByEmail(principal.getName()).map(UserMapper::mapToUserPersonalDetailsDto)));
        } catch (Exception e) {
            return ResponseEntity.status(400).body(ApiResponse.error(400, "تعذر جلب البيانات. يرجى المحاولة مرة أخرى"));
        }
    }

    @PutMapping(value = "", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ApiResponse> updateUser(@RequestBody UserPersonalDetailsDto dto,
            Principal principal) {
        try {
            userService.updateUser(dto, principal.getName());
            return ResponseEntity.ok(ApiResponse.success("تم تحديث البيانات بنجاح "));
        } catch (Exception e) {
            return ResponseEntity.status(400).body(ApiResponse.error(400, "تعذر تحديث البيانات. يرجى التحقق من المدخلات والمحاولة مرة أخرى"));
        }
    }

    @PutMapping(value = "", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<ApiResponse> updateUserWithPhoto(
            @RequestPart("data") UserPersonalDetailsDto dto,
            @RequestPart(value = "photo", required = false) MultipartFile photo,
            Principal principal) throws IOException {
        String photoUrl = photo != null && !photo.isEmpty()
                ? s3Service.getImageUrl(s3Service.uploadImage(photo))
                : null;
        userService.updateUser(dto, principal.getName(), photoUrl);
        return ResponseEntity.ok(ApiResponse.success("تم تحديث البيانات بنجاح "));
    }

    @PutMapping("/update-password")
    public ResponseEntity<ApiResponse> updatePassword(@RequestBody UpdatePasswordDto dto, Principal principal) {
        userService.updatePassword(dto, principal.getName());
        return ResponseEntity.ok(ApiResponse.success("تم تغيير كلمة المرور بنجاح"));
    }

    @DeleteMapping("")
    public ResponseEntity<ApiResponse> postMethodName(Authentication authentication, HttpServletResponse response) {
        userService.deleteAccount(authentication);
        return ResponseEntity.ok(ApiResponse.success("تم حذف الحساب بنجاح"));
    }

}
