package com.hema.medical_backend_spring.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import com.hema.medical_backend_spring.model.Doctor;
import com.hema.medical_backend_spring.model.Doctor.Specialty;

public interface DoctorRepository extends JpaRepository<Doctor, Long> {

    Page<Doctor> findBySpecialty(Specialty specialty, Pageable pageable);

    
    Page<Doctor> findByFullNameContainingIgnoreCaseOrAboutContainingIgnoreCaseOrSpecializationContainingIgnoreCaseOrWorkContainingIgnoreCase(
            String fullName, String about, String specialization, String work, Pageable pageable);

    @Modifying
    @Transactional
    @Query("UPDATE Doctor d SET d.specialization = COALESCE(:specialization, d.specialization), d.work = COALESCE(:work, d.work), d.about = COALESCE(:about, d.about) , d.specialty = COALESCE(:specialty, d.specialty) WHERE d.id = :id")
    void updateDoctorPartial(@Param("id") Long id,
            @Param("specialization") String specialization,
            @Param("work") String work,
            @Param("about") String about,
            @Param("specialty") Specialty specialty);

}
