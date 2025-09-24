package com.cpm.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.*;
import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class StudentCreateDto {
    @NotBlank
    private String name;
    private String course;
    private String email;
    private String phone;
    private BigDecimal fee;
    private String status; // PAID/UNPAID
}