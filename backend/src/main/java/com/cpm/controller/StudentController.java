package com.cpm.controller;

import com.cpm.dto.StudentCreateDto;
import com.cpm.dto.StudentDto;
import com.cpm.service.StudentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;
import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/students")
@RequiredArgsConstructor
public class StudentController {
    private final StudentService service;

    @GetMapping
    public List<StudentDto> list() {
        return service.listAll();
    }

    @PostMapping
    public ResponseEntity<StudentDto> create(@Valid @RequestBody StudentCreateDto dto) {
        var created = service.create(dto);
        return ResponseEntity.created(URI.create("/api/students/" + created.getId())).body(created);
    }
}