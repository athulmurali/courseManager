package com.example.neucourseManager.services;

import com.example.neucourseManager.models.Course;
import com.example.neucourseManager.repositories.CourseRepository;
import com.example.neucourseManager.repositories.ModuleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController

public class CourseService {
    @Autowired
    CourseRepository courseRepository;

    @Autowired
    ModuleRepository moduleRepository;

//
//    POST /api/course - createCourse creates a course
//
//    DELETE /api/course/{id} - deleteCourse deletes a course by id
//
//    GET /api/course - findAllCourses retrieves all the courses
//
//    GET /api/course/{id} - findCourseById retrieves a course by id
//
//    PUT /api/course - updateCourse updates a course by id


    @PostMapping("/api/course")
    public ResponseEntity<Course> createCourse(
            @RequestBody Course course)
    {
        return new ResponseEntity<Course>(courseRepository.save(course), HttpStatus.OK) ;
    }


    @DeleteMapping("/api/course/{id}")
    public  ResponseEntity<?> deleteCourse
            (@PathVariable("id") int id)
    {
        courseRepository.deleteById(id);
        return new ResponseEntity<>(null, HttpStatus.OK) ;

    }

    //    GET /api/course - findAllCourses retrieves all the courses
    @GetMapping("/api/course")
    public ResponseEntity<?>  findAllCourses()
    {
        Iterable<Course> courses = courseRepository.findAll();

        return courses.iterator().hasNext()?
                new ResponseEntity<>(courses, HttpStatus.OK) :
                new ResponseEntity<>(null, HttpStatus.NOT_FOUND) ;

    }


    //    GET /api/course/{id} - findCourseById retrieves a course by id
    @GetMapping("/api/course/{id}")
    public ResponseEntity<?>  findCourseById
            (@PathVariable  ("id") int id)
    {
        Optional<Course> course = courseRepository.findById(id);

        return course.isPresent()?
                new ResponseEntity<>(course, HttpStatus.OK) :
                new ResponseEntity<>(null, HttpStatus.NOT_FOUND) ;

    }


    //    PUT /api/course - updateCourse updates a course by id

    @PutMapping("/api/course/{id}")
    public ResponseEntity<?> createCourse
  (@PathVariable  ("id") int id,
   @RequestBody Course course)    {


         Optional<Course> currentCourse = courseRepository.findById(id);
        if (currentCourse.isPresent())
                {
                    Course newcourse = currentCourse.get();
                    newcourse.setTitle(course.getTitle());

                    return new ResponseEntity<>(courseRepository.save(newcourse), HttpStatus.OK);
                }

        else
        {
            return new ResponseEntity<>( null, HttpStatus.NOT_FOUND);
        }


    }


}
