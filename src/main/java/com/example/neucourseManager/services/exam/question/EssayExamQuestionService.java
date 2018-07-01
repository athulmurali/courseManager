package com.example.neucourseManager.services.exam.question;

import com.example.neucourseManager.models.exam.EssayExamQuestion;
import com.example.neucourseManager.models.exam.Exam;
import com.example.neucourseManager.repositories.exam.EssayExamQuestionRepository;
import com.example.neucourseManager.repositories.exam.ExamRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
public class EssayExamQuestionService {
    @Autowired
    ExamRepository examRepository;
    @Autowired
    EssayExamQuestionRepository essayExamQuestionRepository;


//
//    POST
//    /api/exam/:eid/essay
//    Creates a new essay question for an exam whose id is eid

    @PostMapping("/api/exam/{eid}/essay")
    public EssayExamQuestion createEssayExamQuestion(
            @PathVariable("eid") int examId,
            @RequestBody EssayExamQuestion essayExamQuestion) {
        Optional<Exam> data = examRepository.findById(examId);
        if(data.isPresent()) {
            Exam exam = data.get();
            essayExamQuestion.setExam(exam);
            essayExamQuestion.setType("ESSAY");
            return essayExamQuestionRepository.save(essayExamQuestion);
        }
        return null;
    }





}
