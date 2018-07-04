package com.example.neucourseManager.services.exam.question;

import com.example.neucourseManager.models.exam.Exam;
import com.example.neucourseManager.models.exam.MultipleChoiceExamQuestion;
import com.example.neucourseManager.models.exam.TrueOrFalseExamQuestion;
import com.example.neucourseManager.repositories.exam.ExamRepository;
import com.example.neucourseManager.repositories.exam.MultipleChoiceExamQuestionRepository;
import com.example.neucourseManager.repositories.exam.TrueOrFalseExamQuestionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;


@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
public class TrueOrFalseExamQuestionService {
    public static final  String TRUEFALSE = "TF";

    @Autowired
    ExamRepository examRepository;

    @Autowired
    TrueOrFalseExamQuestionRepository trueOrFalseExamQuestionRepository;


    @PostMapping("/api/exam/{examId}/truefalse")
    public ResponseEntity<?> createTrueOrFalseExamQuestion(
            @PathVariable("examId") int examId,
            @RequestBody TrueOrFalseExamQuestion trueOrFalseExamQuestion) {
        Optional<Exam> data = examRepository.findById(examId);
        if(data.isPresent()) {
            Exam exam = data.get();
            trueOrFalseExamQuestion.setExam(exam);
            trueOrFalseExamQuestion.setType(TRUEFALSE);
            try
            {
                return new ResponseEntity( trueOrFalseExamQuestionRepository.save(trueOrFalseExamQuestion),
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
