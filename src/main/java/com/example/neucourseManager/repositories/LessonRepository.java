package com.example.neucourseManager.repositories;


import com.example.neucourseManager.models.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;


import com.example.neucourseManager.models.Lesson;

public interface LessonRepository
        extends CrudRepository<Lesson, Integer>{


    @Query("SELECT l FROM Lesson l WHERE l.module.id=:lid")
    Iterable<Lesson> findLessonsByCourseId(@Param("lid") int lid);
}
