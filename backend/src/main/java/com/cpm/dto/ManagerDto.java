package com.cpm.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ManagerDto {
    private Long id;
    private String name;
    private String course;
    private String email;
}
