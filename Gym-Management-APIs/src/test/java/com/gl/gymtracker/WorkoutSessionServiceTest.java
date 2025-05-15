package com.gl.gymtracker;

import com.gl.gymtracker.model.WorkoutSession;
import com.gl.gymtracker.repository.WorkoutSessionRepository;
import com.gl.gymtracker.service.WorkoutSessionService;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import org.mockito.junit.jupiter.MockitoExtension;
import org.junit.jupiter.api.extension.ExtendWith;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
public class WorkoutSessionServiceTest {

    @Mock
    private WorkoutSessionRepository repository;

    @InjectMocks
    private WorkoutSessionService service;

    @Test
    void testSave() {
        WorkoutSession session = new WorkoutSession(null, "Test", 45, 400);
        WorkoutSession saved = new WorkoutSession(1L, "Test", 45, 400);

        when(repository.save(session)).thenReturn(saved);

        WorkoutSession result = service.save(session);
        assertEquals(saved.getId(), result.getId());
    }

    @Test
    void testFindAll() {
        when(repository.findAll()).thenReturn(Arrays.asList(
                new WorkoutSession(1L, "A", 30, 200),
                new WorkoutSession(2L, "B", 40, 250)
        ));

        List<WorkoutSession> list = service.findAll();
        assertEquals(2, list.size());
    }

    @Test
    void testFindById() {
        WorkoutSession session = new WorkoutSession(1L, "A", 30, 200);
        when(repository.findById(1L)).thenReturn(Optional.of(session));

        Optional<WorkoutSession> result = service.findById(1L);
        assertTrue(result.isPresent());
        assertEquals("A", result.get().getMemberName());
    }

    @Test
    void testDeleteById() {
        service.deleteById(1L);
        verify(repository, times(1)).deleteById(1L);
    }

    @Test
    void testGetTop3ByCaloriesBurned() {
        when(repository.findTop3ByOrderByCaloriesBurnedDesc()).thenReturn(Arrays.asList(
                new WorkoutSession(1L, "X", 60, 700),
                new WorkoutSession(2L, "Y", 50, 650),
                new WorkoutSession(3L, "Z", 40, 600)
        ));

        List<WorkoutSession> top3 = service.getTop3ByCaloriesBurned();
        assertEquals(3, top3.size());
    }
}
