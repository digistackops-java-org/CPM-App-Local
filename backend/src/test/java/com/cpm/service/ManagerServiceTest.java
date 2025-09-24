package com.cpm.service;

import com.cpm.dto.ManagerCreateDto;
import com.cpm.entity.Manager;
import com.cpm.repository.ManagerRepository;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

class ManagerServiceTest {
    public ManagerServiceTest() { MockitoAnnotations.openMocks(this); }

    @Mock ManagerRepository repo;
    @InjectMocks ManagerService service;

    @Test
    void createsManager() {
        ManagerCreateDto dto = new ManagerCreateDto("Alice","Java","a@x.com");
        when(repo.save(any())).thenAnswer(i -> {
            Manager m = i.getArgument(0);
            m.setId(1L);
            return m;
        });

        var result = service.create(dto);
        assertNotNull(result);
        assertEquals(1L, result.getId());
        assertEquals("Alice", result.getName());
    }
}