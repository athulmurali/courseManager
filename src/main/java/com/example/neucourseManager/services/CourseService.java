package com.example.neucourseManager.services;

import com.example.neucourseManager.repositories.CourseRepository;
import org.springframework.beans.factory.annotation.Autowired;

public class CourseService {
    @Autowired
    CourseRepository courseRepository;
}
