package com.gl.gymtracker.controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.gl.gymtracker.model.WorkoutSession;
import com.gl.gymtracker.service.WorkoutSessionService;

import java.util.List;


@RequestMapping("/v1/workouts")
public class WorkoutSessionController {

   
    private WorkoutSessionService service;


    public ResponseEntity<> createWorkout( ) {

    }


    public ResponseEntity<> getAllWorkouts() {
      
    }

  
    public ResponseEntity<> getWorkoutById() {
        
    }

    
    public ResponseEntity<Void> deleteWorkout() {
      
    }

    
    public ResponseEntity<> getTop3Workouts() {
     
    }
}

