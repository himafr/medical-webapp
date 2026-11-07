package com.hema.medical_backend_spring.repository;

import java.time.LocalDate;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import com.hema.medical_backend_spring.model.ProjectUser;

public interface UserRepo extends JpaRepository<ProjectUser, Long> {

    Optional<ProjectUser> findByEmail(String email);

    @Modifying
    @Transactional
    @Query("UPDATE ProjectUser p SET " +
            "p.fullName = COALESCE(:name, p.fullName), " +
            "p.dateOfBirth = COALESCE(:birth, p.dateOfBirth), " +
            "p.address = COALESCE(:address, p.address), " +
            "p.gender = COALESCE(:gender, p.gender), " +
            "p.phoneNumber = COALESCE(:phone, p.phoneNumber), " +
            "p.photo = COALESCE(:photo, p.photo) " +
            "WHERE p.email = :email")
    int updateUserInfo(
            @Param("email") String email,
            @Param("name") String fullName,
            @Param("birth") LocalDate dateOfBirth,
            @Param("address") String address,
            @Param("gender") String gender,
            @Param("phone") String phoneNumber,
            @Param("photo") String photo);

}
