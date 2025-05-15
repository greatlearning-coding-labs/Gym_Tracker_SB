package com.gl.gymtracker.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.gl.gymtracker.model.WorkoutSession;

import java.util.List;


public interface WorkoutSessionRepository extends JpaRepository<WorkoutSession, Long> {
  


}
