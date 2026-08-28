package com.tileandgranite.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "granites")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Granite {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(nullable = false, unique = true)
    private String graniteCode;
    
    @Column(nullable = false)
    private String name;
    
    @Column(length = 500)
    private String description;
    
    @Column(nullable = false)
    private String origin;
    
    @Column(nullable = false)
    private String color;
    
    @Column(nullable = false)
    private String pattern;
    
    @Column(nullable = false)
    private Double price;
    
    @Column(nullable = false)
    private Integer quantity;
    
    @Column(name = "finish_type")
    private String finishType;
    
    @Column(name = "thickness_mm")
    private Double thickness;
    
    @Column(name = "created_at")
    private java.time.LocalDateTime createdAt;
    
    @Column(name = "updated_at")
    private java.time.LocalDateTime updatedAt;
    
    @PrePersist
    protected void onCreate() {
        createdAt = java.time.LocalDateTime.now();
        updatedAt = java.time.LocalDateTime.now();
    }
    
    @PreUpdate
    protected void onUpdate() {
        updatedAt = java.time.LocalDateTime.now();
    }
}
