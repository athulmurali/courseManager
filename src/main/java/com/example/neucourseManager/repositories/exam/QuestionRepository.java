package com.example.neucourseManager.repositories.exam;


import com.example.neucourseManager.models.exam.BaseExamQuestion;
import org.springframework.data.repository.CrudRepository;



    public interface QuestionRepository extends CrudRepository<BaseExamQuestion,Integer> {

    }

