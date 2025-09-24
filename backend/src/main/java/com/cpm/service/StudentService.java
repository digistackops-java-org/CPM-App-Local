package com.cpm.service;

import com.cpm.dto.StudentCreateDto;
import com.cpm.dto.StudentDto;
import com.cpm.entity.Student;
import com.cpm.repository.StudentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class StudentService {
    private final StudentRepository repo;

    public List<StudentDto> listAll() {
        return repo.findAll().stream().map(this::toDto).collect(Collectors.toList());
    }

    public StudentDto create(StudentCreateDto dto) {
        Student s = Student.builder()
                .name(dto.getName())
                .course(dto.getCourse())
                .email(dto.getEmail())
                .phone(dto.getPhone())
                .fee(dto.getFee())
                .status(dto.getStatus() == null ? "UNPAID" : dto.getStatus())
                .build();
        s = repo.save(s);
        return toDto(s);
    }

    private StudentDto toDto(Student s) {
        return StudentDto.builder()
                .id(s.getId())
                .name(s.getName())
                .course(s.getCourse())
                .email(s.getEmail())
                .phone(s.getPhone())
                .fee(s.getFee())
                .status(s.getStatus())
                .build();
    }
}