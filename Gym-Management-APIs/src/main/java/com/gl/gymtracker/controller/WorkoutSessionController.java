package com.gl.gymtracker.controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.gl.gymtracker.model.WorkoutSession;
import com.gl.gymtracker.service.WorkoutSessionService;

import java.util.List;

@RestController
@RequestMapping("/v1/workouts")
public class WorkoutSessionController {

    @Autowired
    private WorkoutSessionService service;

    @PostMapping
    public ResponseEntity<WorkoutSession> createWorkout(@RequestBody WorkoutSession session) {
        WorkoutSession saved = service.save(session);
        return ResponseEntity.status(201).body(saved);
    }

    @GetMapping
    public ResponseEntity<List<WorkoutSession>> getAllWorkouts() {
        return ResponseEntity.ok(service.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<WorkoutSession> getWorkoutById(@PathVariable Long id) {
        return service.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteWorkout(@PathVariable Long id) {
        if (service.findById(id).isPresent()) {
            service.deleteById(id);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }

    @GetMapping("/top3")
    public ResponseEntity<List<WorkoutSession>> getTop3Workouts() {
        return ResponseEntity.ok(service.getTop3ByCaloriesBurned());
    }
}

