package com.example.neucourseManager.repositories.exam;

import com.example.neucourseManager.models.exam.BaseQuestion;
import org.springframework.data.repository.CrudRepository;

public interface TrueOrFalseExamQuestionRepository extends CrudRepository<BaseQuestion,Integer>{
}
