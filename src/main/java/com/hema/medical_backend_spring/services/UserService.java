package com.hema.medical_backend_spring.services;

import java.time.LocalDateTime;
import java.util.Objects;
import java.util.Optional;
import java.util.regex.Pattern;

import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.hema.medical_backend_spring.config.CustomUserDetails;
import com.hema.medical_backend_spring.dto.UpdatePasswordDto;
import com.hema.medical_backend_spring.dto.UpdatePatientEmergencyDto;
import com.hema.medical_backend_spring.dto.UserPersonalDetailsDto;
import com.hema.medical_backend_spring.exception.IncorrectPasswordException;
import com.hema.medical_backend_spring.mapper.HelperDto;
import com.hema.medical_backend_spring.mapper.UserMapper;
import com.hema.medical_backend_spring.model.Doctor;
import com.hema.medical_backend_spring.model.MedicalRecord;
import com.hema.medical_backend_spring.model.Patient;
import com.hema.medical_backend_spring.model.ProjectUser;
import com.hema.medical_backend_spring.repository.DoctorRepository;
import com.hema.medical_backend_spring.repository.MedicalRecordRepository;
import com.hema.medical_backend_spring.repository.PatientRepository;
import com.hema.medical_backend_spring.repository.UserRepo;
import org.springframework.security.core.userdetails.UserDetailsService;


import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepo userRepo;
    private final PasswordEncoder passwordEncoder;
    private final PatientRepository patientRepository;
    private final DoctorRepository doctorRepository;
    private final MedicalRecordRepository medicalRecordRepository;
    private final EmailService emailService;
    private final JwtService jwtService;
    private final UserDetailsService userDetailsService;

    public void registerPatient(ProjectUser user) {

        // 1. إنشاء المريض
        Patient patient = new Patient();
        patient.setFullName(user.getFullName());
        patient.setEmail(user.getEmail());
        patient.setGender(user.getGender());
        patient.setPassword(passwordEncoder.encode(user.getPassword()));
        patient.setPhoneNumber(user.getPhoneNumber());
        patient.setRole(ProjectUser.Role.PATIENT);

        Patient savedPatient = patientRepository.save(patient);

        // 2. إنشاء ملف طبي فاضي تلقائياً
        MedicalRecord record = new MedicalRecord();
        record.setPatient(savedPatient);
        medicalRecordRepository.save(record);
    }

    public void registerDoctor(ProjectUser user) {

        // 1. إنشاء الدكتور
        Doctor doctor = new Doctor();
        doctor.setFullName(user.getFullName());
        doctor.setEmail(user.getEmail());
        doctor.setGender(user.getGender());
        doctor.setPassword(passwordEncoder.encode(user.getPassword()));
        doctor.setPhoneNumber(user.getPhoneNumber());
        doctor.setRole(ProjectUser.Role.DOCTOR);
        doctorRepository.save(doctor);

    }

    public void updateUser(UserPersonalDetailsDto dto, String email) {
        String name = (dto.getFullName() == null || dto.getFullName().isBlank()) ? null : dto.getFullName();
        String address = (dto.getAddress() == null || dto.getAddress().isBlank()) ? null : dto.getAddress();
        String gender = (dto.getGender() == null || dto.getGender().isBlank()) ? null : dto.getGender();
        String phone = (dto.getPhoneNumber() == null || dto.getPhoneNumber().isBlank()) ? null : dto.getPhoneNumber();

        int updatedRows = userRepo.updateUserInfo(email, name, dto.getDateOfBirth(), address, gender, phone);

        if (updatedRows == 0) {
            throw new RuntimeException("User not found");
        }
    }

    @Transactional
    public void updatePassword(UpdatePasswordDto dto, String email) {
        if (dto.getNewPass().length() < 7) {
            throw new IncorrectPasswordException("الرقم السري يجب ان يكون اكثر من 8 حروف او ارقام");
        }

        ProjectUser user = userRepo.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User not found"));

        // check old password matches
        if (!passwordEncoder.matches(dto.getOldPass(), user.getPassword())) {
            throw new IncorrectPasswordException("الرقم السري غير صحيح ");
        }

        if (dto.getNewPass().equals(dto.getOldPass())) {
            throw new IncorrectPasswordException("الرقم السري الجديد مطابق للرقم السري القديم ");
        }

        // update to new password
        user.setPassword(passwordEncoder.encode(dto.getNewPass()));
        userRepo.save(user);
    }

    @SuppressWarnings("null")
    public void deleteAccount(Authentication authentication) {
        try {
            userRepo.deleteById(HelperDto.getProjectUser(authentication).getId());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public void sendActivationPin(String email) {
        ProjectUser user = userRepo.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User not found"));

        String pin = generatePin();
        user.setActivationPin(pin);
        user.setPinExpiresAt(LocalDateTime.now().plusMinutes(15));
        userRepo.save(user);

        emailService.sendActivationPin(email, pin);
    }

    public boolean activateAccount(String email, String pin ,HttpServletResponse response) {
        ProjectUser user = userRepo.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User not found"));

        if (user.getActivationPin() == null ||
                !user.getActivationPin().equals(pin) ||
                LocalDateTime.now().isAfter(user.getPinExpiresAt())) {
            return false;
        }

        user.setActive(true);
        user.setActivationPin(null);
        user.setPinExpiresAt(null);
        userRepo.save(user);

        CustomUserDetails userDetails = (CustomUserDetails) userDetailsService.loadUserByUsername(user.getEmail());
         // جدد الـ token
    String newToken = jwtService.generateToken(userDetails);
    Cookie cookie = new Cookie("jwt", newToken);
    cookie.setHttpOnly(true);
    cookie.setPath("/");
    response.addCookie(cookie);

        return true;
    }

    public boolean isValidEmail(String email) {
        return EMAIL_PATTERN.matcher(email).matches();
    }

    private String generatePin() {
        return String.valueOf((int) (Math.random() * 900000) + 100000); // 6 أرقام
    }

    private static final Pattern EMAIL_PATTERN = Pattern
            .compile("^[A-Za-z0-9][A-Za-z0-9._%+-]*@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$");

    public Optional<ProjectUser> findByEmail(String email) {
        return userRepo.findByEmail(email);
    }

    public Optional<ProjectUser> loadUserByEmail(String email) {
        return userRepo.findByEmail(email);
    }
}
