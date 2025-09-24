package com.cpm.controller;

import com.cpm.dto.ManagerCreateDto;
import com.cpm.dto.ManagerDto;
import com.cpm.service.ManagerService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;
import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/managers")
@RequiredArgsConstructor
public class ManagerController {
    private final ManagerService service;

    @GetMapping
    public List<ManagerDto> list() {
        return service.listAll();
    }

    @PostMapping
    public ResponseEntity<ManagerDto> create(@Valid @RequestBody ManagerCreateDto dto) {
        var created = service.create(dto);
        return ResponseEntity.created(URI.create("/api/managers/" + created.getId())).body(created);
    }
}