package com.example.neucourseManager.services.exam.question;

import com.example.neucourseManager.models.exam.Exam;
import com.example.neucourseManager.models.exam.Question;
import com.example.neucourseManager.repositories.exam.ExamRepository;
import com.example.neucourseManager.repositories.exam.QuestionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;


@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
public class Common {

    @Autowired
    QuestionRepository questionRepository;

    //    /api/exam/:eid
    @DeleteMapping("/api/question/{qid}")
    public ResponseEntity<?> deleteQuestion(@PathVariable("qid") int qid)
    {
        try{
            questionRepository.deleteById(qid);
            return new ResponseEntity(HttpStatus.OK);
        }
        catch(Exception e){
            return new ResponseEntity(HttpStatus.GONE);
        }
    }

    @GetMapping("/api/exam/{eid}/question")
    public ResponseEntity<?> getQuestionsByExam(@PathVariable("eid") int eid)
    {
        List<Question> questionList = new ArrayList();

        try{
            questionList = (List<Question>) questionRepository.findAll();
            questionList = questionList.stream().filter(question -> question.getExam().
                    getId() == eid).collect(Collectors.toList());
            return new ResponseEntity(questionList, HttpStatus.OK);
        }
        catch(Exception e){
            System.out.println("Exception : ");
            System.out.println(e);
            return new ResponseEntity(questionList, HttpStatus.OK);
        }
    }


}
