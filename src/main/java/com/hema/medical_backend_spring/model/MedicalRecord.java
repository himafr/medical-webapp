package com.hema.medical_backend_spring.model;

import java.time.LocalDateTime;
import java.util.List;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.hema.medical_backend_spring.model.sub.Allergies;
import com.hema.medical_backend_spring.model.sub.ChronicDiseases;
import com.hema.medical_backend_spring.model.sub.Medications;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "medical_records")
@EntityListeners(AuditingEntityListener.class)
@Getter
@Setter
public class MedicalRecord {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn(name = "patient_id",unique = true)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIgnore 
    private Patient patient;
    
     @OneToMany(mappedBy = "medicalRecord", cascade = CascadeType.ALL, orphanRemoval = true)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private List<Allergies> allergies;
    
    @OneToMany(mappedBy = "medicalRecord", cascade = CascadeType.ALL, orphanRemoval = true)
   @OnDelete(action = OnDeleteAction.CASCADE)
   private List<ChronicDiseases> chronicDiseases;

    @OneToMany(mappedBy = "medicalRecord", cascade = CascadeType.ALL, orphanRemoval = true)
   @OnDelete(action = OnDeleteAction.CASCADE)
   private List<Medications> medications;

    
    @CreatedDate
    @Column(updatable = false)
    private LocalDateTime createdAt;
}