package com.gl.gymtracker;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.gl.gymtracker.controller.WorkoutSessionController;
import com.gl.gymtracker.model.WorkoutSession;
import com.gl.gymtracker.service.WorkoutSessionService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;

import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;

import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;
import java.util.Optional;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(WorkoutSessionController.class)
public class WorkoutSessionControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private WorkoutSessionService service;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void testCreateWorkout() throws Exception {
        WorkoutSession session = new WorkoutSession(null, "John", 60, 500);
        WorkoutSession saved = new WorkoutSession(1L, "John", 60, 500);

        Mockito.when(service.save(Mockito.any())).thenReturn(saved);

        mockMvc.perform(post("/v1/workouts")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(session)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.memberName").value("John"));
    }

    @Test
    void testGetAllWorkouts() throws Exception {
        Mockito.when(service.findAll()).thenReturn(Arrays.asList(
                new WorkoutSession(1L, "A", 30, 200),
                new WorkoutSession(2L, "B", 45, 300)
        ));

        mockMvc.perform(get("/v1/workouts"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.size()").value(2));
    }

    @Test
    void testGetWorkoutById_Found() throws Exception {
        Mockito.when(service.findById(1L)).thenReturn(Optional.of(
                new WorkoutSession(1L, "A", 30, 200)
        ));

        mockMvc.perform(get("/v1/workouts/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.memberName").value("A"));
    }

    @Test
    void testGetWorkoutById_NotFound() throws Exception {
        Mockito.when(service.findById(1L)).thenReturn(Optional.empty());

        mockMvc.perform(get("/v1/workouts/1"))
                .andExpect(status().isNotFound());
    }

    @Test
    void testDeleteWorkout_Found() throws Exception {
        Mockito.when(service.findById(1L)).thenReturn(Optional.of(new WorkoutSession()));
        mockMvc.perform(delete("/v1/workouts/1"))
                .andExpect(status().isNoContent());
    }

    @Test
    void testDeleteWorkout_NotFound() throws Exception {
        Mockito.when(service.findById(1L)).thenReturn(Optional.empty());
        mockMvc.perform(delete("/v1/workouts/1"))
                .andExpect(status().isNotFound());
    }

    @Test
    void testGetTop3Workouts() throws Exception {
        Mockito.when(service.getTop3ByCaloriesBurned()).thenReturn(Arrays.asList(
                new WorkoutSession(1L, "A", 50, 600),
                new WorkoutSession(2L, "B", 60, 550),
                new WorkoutSession(3L, "C", 45, 500)
        ));

        mockMvc.perform(get("/v1/workouts/top3"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.size()").value(3));
    }
}
