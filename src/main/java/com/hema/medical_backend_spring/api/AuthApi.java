package com.hema.medical_backend_spring.api;

import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.hema.medical_backend_spring.config.CustomUserDetails;
import com.hema.medical_backend_spring.dto.LoginResponse;
import com.hema.medical_backend_spring.dto.RegisterRequest;
import com.hema.medical_backend_spring.exception.IncorrectPasswordException;
import com.hema.medical_backend_spring.services.JwtService;
import com.hema.medical_backend_spring.services.UserService;

import lombok.AllArgsConstructor;

@Controller
@RequestMapping("/api/auth")
@AllArgsConstructor
public class AuthApi {

    private final UserDetailsService userDetailsService;
    private final JwtService jwtService;
    private final PasswordEncoder passwordEncoder;
    private final UserService userService;

    @PostMapping("/login")
    @ResponseBody
    public ResponseEntity<LoginResponse> login(@RequestBody Map<String, String> request) {
        String username = request.get("email");
        String password = request.get("password");

        CustomUserDetails userDetails = (CustomUserDetails) userDetailsService.loadUserByUsername(username);

        if (passwordEncoder.matches(password, userDetails.getPassword())) {
            String token = jwtService.generateToken(userDetails);

            String role = userDetails.getProjectUser().getRole().toString();
            return ResponseEntity.ok(new LoginResponse(token, userDetails.getProjectUser().getId().toString(),
                    userDetails.getProjectUser().getFullName(),
                    userDetails.getProjectUser().getEmail(), role));
        }
        throw new IncorrectPasswordException("كلمة المرور او اسم المستخدم غير صحيح");

    }

    // /** تسجيل مستخدم جديد */
    @PostMapping("/register")
    @ResponseBody
    public ResponseEntity<LoginResponse> register(@RequestBody RegisterRequest user) {
        // chek fields
        if (user.getEmail() == null || !StringUtils.hasText(user.getPassword())
                || !StringUtils.hasText(user.getFullName())) {
            throw new IllegalArgumentException("جميع الحقول مطلوبة");
        }
        String passwordCheck = user.getPasswordCheck();
        // check matching password
        if (user.getPassword() == null || !user.getPassword().equals(passwordCheck)) {
            throw new IllegalArgumentException("كلمات المرور غير متطابقة");
        }
        // check if its valid name
        if (!userService.isValidEmail(user.getEmail())) {
            System.out.println(userService.isValidEmail(user.getEmail()));
            throw new IllegalArgumentException("البريد الالكتروني غير صالح");
        }

        // check if user exist
        if (userService.findByEmail(user.getEmail()).isPresent()) {
            throw new IllegalArgumentException("البريد الالكتروني مستخدم بالفعل");
        }

        System.out.println(user.toString());
        userService.registerPatient(user);
        CustomUserDetails userDetails = (CustomUserDetails) userDetailsService.loadUserByUsername(user.getEmail());

        String token = jwtService.generateToken(userDetails);

        String role = userDetails.getProjectUser().getRole().toString();
        return ResponseEntity.ok(new LoginResponse(token, userDetails.getProjectUser().getId().toString(),
                userDetails.getProjectUser().getFullName(),
                userDetails.getProjectUser().getEmail(), role));
    }

}