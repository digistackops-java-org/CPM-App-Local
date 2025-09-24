package com.cpm.entity;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;

@Entity
@Table(name = "student")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable=false)
    private String name;

    private String course;
    private String email;
    private String phone;

    @Column(precision = 10, scale = 2)
    private BigDecimal fee;

    @Column(length = 10)
    private String status; // PAID / UNPAID
}
