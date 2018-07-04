package com.example.neucourseManager.services.exam.question;


import com.example.neucourseManager.models.exam.Exam;
import com.example.neucourseManager.models.exam.FillInTheBlanksExamQuestion;
import com.example.neucourseManager.repositories.exam.ExamRepository;
import com.example.neucourseManager.repositories.exam.FillInTheBlankQuestionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;


@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
public class FillInTheBlanksExamQuestionService {

    public static String BLANKS = "BLANKS";

    @Autowired
    ExamRepository examRepository;

    @Autowired
    FillInTheBlankQuestionRepository fillInTheBlankQuestionRepository;


    @PostMapping("/api/exam/{examId}/blanks")
    public ResponseEntity<?> createMultipleChoiceExamQuestion(
            @PathVariable("examId") int examId,
            @RequestBody FillInTheBlanksExamQuestion  newFillInTheBlanksExamQuestion) {
        Optional<Exam> data = examRepository.findById(examId);
        if(data.isPresent()) {
            Exam exam = data.get();
            newFillInTheBlanksExamQuestion.setExam(exam);
            newFillInTheBlanksExamQuestion.setType(BLANKS);
            try
            {
                return new ResponseEntity(fillInTheBlankQuestionRepository.save(newFillInTheBlanksExamQuestion),
                        HttpStatus.OK);
            }
            catch (Exception e)
            {
                return new ResponseEntity(HttpStatus.FORBIDDEN);
            }
        }
        return new ResponseEntity(HttpStatus.NOT_FOUND);
    }



}
