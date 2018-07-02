package com.example.neucourseManager.repositories.exam;

import com.example.neucourseManager.models.exam.BaseQuestion;
import com.example.neucourseManager.models.exam.Question;
import org.springframework.data.repository.CrudRepository;

public interface FillInTheBlankQuestionRepository extends CrudRepository<Question,Integer>{

}
