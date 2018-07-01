package com.example.neucourseManager.repositories.exam;

import com.example.neucourseManager.models.exam.Exam;
import org.springframework.data.repository.CrudRepository;

public interface ExamRepository
        extends CrudRepository<Exam, Integer> {

}
