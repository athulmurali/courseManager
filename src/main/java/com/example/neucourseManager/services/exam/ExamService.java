package com.example.neucourseManager.services.exam;


import com.example.neucourseManager.models.Topic;
import com.example.neucourseManager.models.exam.Exam;
import com.example.neucourseManager.repositories.TopicRepository;
import com.example.neucourseManager.repositories.exam.ExamRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
public class ExamService {
    @Autowired
    ExamRepository examRepository;

    @Autowired
    TopicRepository topicRepository;

//
//    GET
///api/exam
//    Returns an arrary of all exams

    @GetMapping("/api/exam")
    public List<Exam> findAllExams() {
        System.out.println("Getting all exams   :  ");

        List<Exam> examsList = new ArrayList<>();
        examRepository.findAll();
        return examsList;
    }

//    GET
///api/exam/:eid
//    Returns an exam whose id is eid

    @GetMapping("/api/exam/{eid}")
        public Exam findExamById(@PathVariable("eid") int eid) {
        System.out.println("Getting  exams  by examId  :  " + eid);

        Optional<Exam> exam = examRepository.findById(eid);
        if (exam.isPresent())
            return  exam.get();
        return null;

    }

// GET
///api/topic/:tid/exam
// Returns an array of exams for a given topic
    @GetMapping("/api/topic/{tid}/exam")
    public  List<Exam> findExamByTopicId(@PathVariable("tid") int tid) {
        System.out.println("Getting exams by Topic id   :  " + tid);
        List<Exam>  examList= (List<Exam>) examRepository.findAll();
        examList =  examList.stream().
              filter(examObj -> examObj.getTopic().getId() ==tid).
              collect(Collectors.toList());
        return examList;
    }


//            POST
///api/topic/:tid/exam
//    Creates a new exam for a given topic
    @PostMapping("/api/topic/{tid}/exam")
    public Exam createExam(@PathVariable("tid") int tid,
                           @RequestBody Exam exam){
        System.out.println("Creating exam");
        System.out.println(exam);



        Optional<Topic> topic =  topicRepository.findById(tid);
        if (topic.isPresent())
            exam.setTopic(topic.get());
        else return null;

        return examRepository.save(exam);
    }



//            DELETE
///api/exam/:eid
//    Deletes an exam whose id is eid


    @DeleteMapping("/api/exam/{eid}")
    public ResponseEntity<?>  deleteExamById(@PathVariable("eid") int eid) {
        System.out.println("Deleting  exam  by examId  :  " + eid);
        List emptyList = new ArrayList();
        try{
            examRepository.deleteById(eid);
            return new ResponseEntity(emptyList,HttpStatus.OK);
        }
        catch(Exception e){
            return new ResponseEntity<>(HttpStatus.GONE);

        }
    }
}
