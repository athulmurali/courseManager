package com.example.neucourseManager.services.exam.question;

import com.example.neucourseManager.models.exam.Exam;
import com.example.neucourseManager.models.exam.MultipleChoiceExamQuestion;
import com.example.neucourseManager.repositories.exam.ExamRepository;
import com.example.neucourseManager.repositories.exam.MultipleChoiceExamQuestionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;


@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
public class MultipleChoiceExamQuestionService {
    public static String MCQ = "MCQ";

    @Autowired
    ExamRepository examRepository;

    @Autowired
    MultipleChoiceExamQuestionRepository multipleChoiceExamQuestionRepository;


    @PostMapping("/api/exam/{examId}/choice")
    public ResponseEntity<?> createMultipleChoiceExamQuestion(
            @PathVariable("examId") int examId,
            @RequestBody MultipleChoiceExamQuestion newMultipleChoiceExamQuestion) {
        Optional<Exam> data = examRepository.findById(examId);
        if(data.isPresent()) {
            Exam exam = data.get();
            newMultipleChoiceExamQuestion.setExam(exam);
            newMultipleChoiceExamQuestion.setType(MCQ);
            try
            {
                MultipleChoiceExamQuestion saved = multipleChoiceExamQuestionRepository.save(newMultipleChoiceExamQuestion);

                return new ResponseEntity(saved,
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
