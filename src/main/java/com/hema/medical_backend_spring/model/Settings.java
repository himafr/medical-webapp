package com.hema.medical_backend_spring.model;

import java.time.LocalDateTime;

import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.MapsId;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "settings")
@EntityListeners(AuditingEntityListener.class)
@Getter
@Setter
public class Settings {

    @Id
    private Long id;

    @OneToOne
    @MapsId
    @JoinColumn(name = "user_id")
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIgnore
    private ProjectUser user;

    @ColumnDefault ("'ar'")
    @Column(nullable = false)
    private String language = "ar";

    private boolean darkMode = false;

    @Enumerated(EnumType.STRING)
    @ColumnDefault("'MEDIUM'")
    @Column(name = "font_size", nullable = false)
    private FontSize fontSize = FontSize.MEDIUM;
    @CreatedDate
    @Column(updatable = false)
    private LocalDateTime createdAt;

    public enum FontSize {
        SMALL, MEDIUM, LARGE
    }

}