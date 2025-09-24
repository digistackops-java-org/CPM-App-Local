package com.cpm.dto;

import lombok.*;
import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class StudentDto {
    private Long id;
    private String name;
    private String course;
    private String email;
    private String phone;
    private BigDecimal fee;
    private String status;
}
