package com.hema.medical_backend_spring.model;

import java.time.LocalDate;
import java.time.LocalDateTime;

import org.hibernate.annotations.ColumnDefault;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "users")
@Inheritance(strategy = InheritanceType.JOINED)
@EntityListeners(AuditingEntityListener.class)
@Getter
@Setter
public class ProjectUser {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false)
    private String password;

    @ColumnDefault("'PATIENT'")
    @Enumerated(EnumType.STRING)
    private Role role = Role.PATIENT;
    @ColumnDefault("'default.png'")
    private String photo = "default.png";

    @Column(nullable = false)
    private String fullName;

    @OneToOne(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    private Settings settings;

    private boolean isActive = false;

    private String activationPin;
    private LocalDateTime pinExpiresAt;

    private LocalDate dateOfBirth;
    private String address = "غير محدد";
    private String phoneNumber = "غير محدد";
    private String gender = "غير محدد";

    @CreatedDate
    @Column(updatable = false)
    private LocalDateTime createdAt;

    static public enum Role {
        PATIENT,
        DOCTOR,
        ADMIN
    }
}
