package com.gl.gymtracker.service;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gl.gymtracker.model.WorkoutSession;
import com.gl.gymtracker.repository.WorkoutSessionRepository;

import java.util.List;
import java.util.Optional;

@Service
public class WorkoutSessionService {

    @Autowired
    private WorkoutSessionRepository repository;

    public WorkoutSession save(WorkoutSession session) {
        return repository.save(session);
    }

    public List<WorkoutSession> findAll() {
        return repository.findAll();
    }

    public Optional<WorkoutSession> findById(Long id) {
        return repository.findById(id);
    }

    public void deleteById(Long id) {
        repository.deleteById(id);
    }

    public List<WorkoutSession> getTop3ByCaloriesBurned() {
        return repository.findTop3ByOrderByCaloriesBurnedDesc();
    }
}
