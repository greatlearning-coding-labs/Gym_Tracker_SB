package com.gl.gymtracker.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;


public class WorkoutSession {

  
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String memberName;
    private Integer durationMinutes;
    private Integer caloriesBurned;

  
}
