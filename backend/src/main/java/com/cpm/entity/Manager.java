package com.cpm.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "manager")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Manager {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable=false)
    private String name;

    private String course;

    @Column(unique=true)
    private String email;
}
