package com.hema.medical_backend_spring.repository;


import org.springframework.data.jpa.repository.JpaRepository;

import com.hema.medical_backend_spring.model.MedicalRecord;

public interface MedicalRecordRepository extends JpaRepository<MedicalRecord, Long> {
  

}
