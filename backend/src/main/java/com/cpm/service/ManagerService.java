package com.cpm.service;

import com.cpm.dto.ManagerCreateDto;
import com.cpm.dto.ManagerDto;
import com.cpm.entity.Manager;
import com.cpm.repository.ManagerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ManagerService {
    private final ManagerRepository repo;

    public List<ManagerDto> listAll() {
        return repo.findAll().stream().map(this::toDto).collect(Collectors.toList());
    }

    public ManagerDto create(ManagerCreateDto dto) {
        Manager m = Manager.builder()
                .name(dto.getName())
                .course(dto.getCourse())
                .email(dto.getEmail())
                .build();
        m = repo.save(m);
        return toDto(m);
    }

    private ManagerDto toDto(Manager m) {
        return ManagerDto.builder()
                .id(m.getId())
                .name(m.getName())
                .course(m.getCourse())
                .email(m.getEmail())
                .build();
    }
}
