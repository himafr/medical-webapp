package com.hema.medical_backend_spring.controllers;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.hema.medical_backend_spring.config.CustomUserDetails;
import com.hema.medical_backend_spring.model.ProjectUser;
import com.hema.medical_backend_spring.services.JwtService;
import com.hema.medical_backend_spring.services.UserService;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.AllArgsConstructor;

@Controller
@AllArgsConstructor
public class AuthController {

    private final UserService userService;
    private final UserDetailsService userDetailsService;
    private final JwtService jwtService;

    @GetMapping("/login")
    public String login(Authentication authentication,HttpServletRequest request) {
        if (authentication != null && authentication.isAuthenticated()) {
            return "redirect:/home";
        }
        return "auth/login";
    }

    @PostMapping("/perform_login")
    public String login(@RequestParam String username,
            @RequestParam String password,
            HttpServletResponse response) {

        CustomUserDetails userDetails = (CustomUserDetails) userDetailsService.loadUserByUsername(username);

        String token = jwtService.generateToken(userDetails);

        Cookie cookie = new Cookie("jwt", token);
        cookie.setHttpOnly(true);
        cookie.setPath("/");
        cookie.setMaxAge(86400);
        response.addCookie(cookie);

        // TODO return to the page thr user was in

        String role = userDetails.getProjectUser().getRole().toString();
        switch (role) {
            case "PATIENT":
                return "redirect:/profile/patient";
            case "DOCTOR":
                return "redirect:/profile/doctor";
            case "ADMIN":
                return "redirect:/profile/admin";
            default:
                return "redirect:/home";
        }
    }

    @GetMapping("/signup")
    public String getSignupPage(Authentication authentication) {
        if (authentication != null && authentication.isAuthenticated()) {
            return "redirect:/home";
        }
        return "auth/signup";
    }

    @GetMapping("/doctor-signup")
    public String getDoctorSignupPage(Authentication authentication) {
        if (authentication != null && authentication.isAuthenticated()
                && authentication.getAuthorities().toArray()[0].toString().equals("ADMIN")) {
            return "auth/signup-doctor";
        }
        return "redirect:/home";
    }

    @PostMapping("/signup")
    public String registerUser(
            @ModelAttribute ProjectUser user,
            @RequestParam(name = "passwordCheck", required = false) String passwordCheck,
            Model model) {

        // chek fields
        if (user.getEmail() == null || user.getPassword() == "" || user.getFullName() == "") {
            model.addAttribute("error", "جميع الحقول مطلوبة");
            return "auth/signup";
        }
        // check matching password
        if (user.getPassword() == null || !user.getPassword().equals(passwordCheck)) {
            model.addAttribute("error", "كلمات المرور غير متطابقة");
            return "auth/signup";
        }
        // check if its valid name
        if (!userService.isValidEmail(user.getEmail())) {
            System.out.println(userService.isValidEmail(user.getEmail()));
            model.addAttribute("error", "صيغة البريد الإلكتروني غير صحيحة");
            return "auth/signup";
        }

        // check if user exist
        if (userService.findByEmail(user.getEmail()).isPresent()) {
            model.addAttribute("error", "هذا البريد الإلكتروني مسجل بالفعل");
            return "auth/signup";
        }

        System.out.println(user.toString());
        userService.registerPatient(user);
        return "redirect:/login";
    }

    @PostMapping("/doctor-signup")
    public String registerDoctor(
            @ModelAttribute ProjectUser user,
            @RequestParam(name = "passwordCheck", required = false) String passwordCheck,
            Model model, Authentication authentication) {
        if (!authentication.getAuthorities().toArray()[0].toString().equals("ADMIN")) {
            model.addAttribute("error", "ليس لديك صلاحية للوصول إلى هذه الصفحة");
            return "auth/doctor-signup";
        }

        // chek fields
        if (user.getEmail() == null || user.getPassword() == "" || user.getFullName() == "") {
            model.addAttribute("error", "جميع الحقول مطلوبة");
            return "auth/doctor-signup";
        }
        // check matching password
        if (user.getPassword() == null || !user.getPassword().equals(passwordCheck)) {
            model.addAttribute("error", "كلمات المرور غير متطابقة");
            return "auth/doctor-signup";
        }
        // check if its valid name
        if (!userService.isValidEmail(user.getEmail())) {
            System.out.println(userService.isValidEmail(user.getEmail()));
            model.addAttribute("error", "صيغة البريد الإلكتروني غير صحيحة");
            return "auth/doctor-signup";
        }

        // check if user exist
        if (userService.findByEmail(user.getEmail()).isPresent()) {
            model.addAttribute("error", "هذا البريد الإلكتروني مسجل بالفعل");
            return "auth/doctor-signup";
        }
        userService.registerDoctor(user);
        return "redirect:/login";
    }

}
