package com.example.neucourseManager.services.exam;


import com.example.neucourseManager.models.exam.Exam;
import com.example.neucourseManager.repositories.exam.ExamRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
public class ExamService {
    @Autowired
    ExamRepository examRepository;


//
//    GET
///api/exam
//    Returns an arrary of all exams

    @GetMapping("/api/exam")
    public List<Exam> findAllExams(@PathVariable("examId") int examId) {
        System.out.println("Getting all exams   :  ");

        List<Exam> examsList = new ArrayList<>();
        return examsList;
    }





//    GET
///api/exam/:eid
//    Returns an exam whose id is eid



//            GET
///api/lesson/:lid/exam
//    Returns an array of exams for a given lesson


//            POST
///api/lesson/:lid/exam
//    Creates a new exam for a given lesson

//            DELETE
///api/exam/:eid
//    Deletes an exam whose id is eid


//            POST
///api/exam/:eid/essay
//    Creates a new essay question for an exam whose id is eid


}
