package com.example.neucourseManager.services.exam.question;

import com.example.neucourseManager.models.exam.Exam;
import com.example.neucourseManager.repositories.exam.ExamRepository;
import com.example.neucourseManager.repositories.exam.QuestionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;


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

}
