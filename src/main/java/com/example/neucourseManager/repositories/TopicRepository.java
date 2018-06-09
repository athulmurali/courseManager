package com.example.neucourseManager.repositories;


import com.example.neucourseManager.models.Topic;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

public interface TopicRepository
        extends CrudRepository<Topic, Integer>{


    @Query("SELECT t FROM Topic t WHERE t.lesson.id=:lid")
    Iterable<Topic> findTopicsByLessonId(@Param("lid") int lid);
}
