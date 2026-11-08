package com.hema.medical_backend_spring.services;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Objects;
import java.util.Optional;
import java.util.regex.Pattern;

import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseCookie;

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
    private final S3Service s3Service;

    public void registerPatient(ProjectUser user) {

        // 1. إنشاء المريض
        Patient patient = new Patient();
        patient.setFullName(user.getFullName());
        patient.setEmail(user.getEmail());
        patient.setGender(user.getGender());
        patient.setPassword(passwordEncoder.encode(user.getPassword()));
        patient.setPhoneNumber(user.getPhoneNumber());
        patient.setPhoto(user.getPhoto());
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
        doctor.setPhoto(user.getPhoto());
        doctor.setRole(ProjectUser.Role.DOCTOR);
        doctorRepository.save(doctor);

    }

    public void updateUser(UserPersonalDetailsDto dto, String email) {
        updateUser(dto, email, null);
    }

    public void updateUser(UserPersonalDetailsDto dto, String email, String photo) {
        String name = (dto.getFullName() == null || dto.getFullName().isBlank()) ? null : dto.getFullName();
        String address = (dto.getAddress() == null || dto.getAddress().isBlank()) ? null : dto.getAddress();
        String gender = (dto.getGender() == null || dto.getGender().isBlank()) ? null : dto.getGender();
        String phone = (dto.getPhoneNumber() == null || dto.getPhoneNumber().isBlank()) ? null : dto.getPhoneNumber();
        if (dto.getDateOfBirth() != null && dto.getDateOfBirth().isAfter(LocalDate.now().minusYears(12))) {
            throw new IllegalArgumentException("يجب أن يكون العمر 12 عامًا على الأقل");
        }

        ProjectUser user = photo != null
                ? userRepo.findByEmail(email).orElseThrow(
                        () -> new com.hema.medical_backend_spring.exception.UserNotFoundException("لم يتم العثور على المستخدم"))
                : null;
        int updatedRows = userRepo.updateUserInfo(email, name, dto.getDateOfBirth(), address, gender, phone,
                photo);

        if (updatedRows == 0) {
            throw new com.hema.medical_backend_spring.exception.UserNotFoundException("لم يتم العثور على المستخدم");
        }
        if (user != null && !photo.equals(user.getPhoto())) {
            s3Service.deleteImageByUrl(user.getPhoto());
        }
    }

    public String refreshJwtToken(String email,String photoUrl, HttpServletResponse response) {
        CustomUserDetails userDetails = (CustomUserDetails) userDetailsService.loadUserByUsername(email);
        userDetails.getProjectUser().setPhoto(photoUrl);
        String token = jwtService.generateToken(userDetails);
         Cookie cookie = new Cookie("jwt", token);
        cookie.setHttpOnly(true);
        cookie.setPath("/");
        cookie.setMaxAge(86400);
        response.addCookie(cookie);
        return token;
    }

    @Transactional
    public void updatePassword(UpdatePasswordDto dto, String email) {
        if (dto.getNewPass().length() < 7) {
            throw new IncorrectPasswordException("يجب ألا تقل كلمة المرور الجديدة عن 7 أحرف أو أرقام");
        }

        ProjectUser user = userRepo.findByEmail(email)
                .orElseThrow(() -> new com.hema.medical_backend_spring.exception.UserNotFoundException("لم يتم العثور على المستخدم"));

        // check old password matches
        if (!passwordEncoder.matches(dto.getOldPass(), user.getPassword())) {
            throw new IncorrectPasswordException("كلمة المرور الحالية غير صحيحة");
        }

        if (dto.getNewPass().equals(dto.getOldPass())) {
            throw new IncorrectPasswordException("يجب أن تختلف كلمة المرور الجديدة عن الحالية");
        }

        // update to new password
        user.setPassword(passwordEncoder.encode(dto.getNewPass()));
        userRepo.save(user);
    }

    @SuppressWarnings("null")
    public void deleteAccount(Authentication authentication) {
        try {
            Long userId = HelperDto.getProjectUser(authentication).getId();
            ProjectUser user = userRepo.findById(userId)
                    .orElseThrow(() -> new com.hema.medical_backend_spring.exception.UserNotFoundException(
                            "لم يتم العثور على المستخدم"));
            userRepo.deleteById(userId);
            s3Service.deleteImageByUrl(user.getPhoto());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public void sendActivationPin(String email) {
        ProjectUser user = userRepo.findByEmail(email)
                .orElseThrow(() -> new com.hema.medical_backend_spring.exception.UserNotFoundException("لم يتم العثور على المستخدم"));

        String pin = generatePin();
        user.setActivationPin(pin);
        user.setPinExpiresAt(LocalDateTime.now().plusMinutes(15));
        userRepo.save(user);

        emailService.sendActivationPin(email, pin);
    }

    public boolean activateAccount(String email, String pin, HttpServletResponse response) {
        ProjectUser user = userRepo.findByEmail(email)
                .orElseThrow(() -> new com.hema.medical_backend_spring.exception.UserNotFoundException("لم يتم العثور على المستخدم"));

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
